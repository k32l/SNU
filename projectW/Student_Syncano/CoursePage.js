var Observable = require('FuseJS/Observable');
var signup = require('/SignUp.js');
var history = require('/HistoryPage.js');
var BeaconDetectorModule = require("BeaconDetectorModule");
 
// Course class
var Syncano = require('syncano-js/dist/syncano.fuse.js');
var ApiKeys = require("api-keys.js");
var classname = "course";
var connection = Syncano({
    accountKey: ApiKeys.accountKey,
    defaults: {
      instanceName: ApiKeys.instanceName,
      className: classname
    }
});
var CourseObject = connection.DataObject;
 
// Attendance class
var connection2 = Syncano({
    accountKey: ApiKeys.accountKey,
    defaults: {
      instanceName: ApiKeys.instanceName,
      className: "attendance"
    }
});
var AttendanceObject = connection2.DataObject;

var courses = Observable({name: "No class today"});

function getStudentId(){
  var storage = require('FuseJS/Storage');
  var userData = storage.readSync("filedb.txt");
  var json = JSON.parse(userData);
  return json["student_id"];
}
  
var makeCourseList = function(courseList) {  
  debug_log("makeCourseList called " + courseList[0].name 
    + courseList[0].start + courseList[0].end);
  for(var i = 0; i < courseList.length; i++) {
    var course = courseList[i];
    BeaconDetectorModule.pushCourse(course.name, course.beaconId, 
                                    course.start, course.end);
  }
}
  
var startActivity = function() {
  BeaconDetectorModule.start();
} 

function getTodaysCourse(){
  day = (new Date()).toString().substring(0,3); //get today's day like Mon, Tue, ...
  
  CourseObject.please().filter({"students_list":{"_contains":[getStudentId()]}}).then(function(res, raw){
    debug_log(getStudentId());
    arr = [];
    if (res.length) {
      //debug_log("res: " + res);
      res.forEach(function(course) {
        if(course.day.includes(day)){
          //debug_log("course: " + course.course_name);
          arr.push({ //if the student has this class today
            start: course.start_time,
            name: course.course_name,
            beaconId: course.beacon_id,
            end: course.end_time,
            color: "#ec0707",
            enter: "NA",
            exit: "NA"
          });
        }
      });
    }
    
    if(arr.length != 0){
      courses.replaceAll(arr);
      makeCourseList(arr);
      startActivity();

    }})
    .catch(function (reason) {
        console.log("data load error: " + reason);
        courses.value = {name: "Couldn't load courses"};
    }); 
}

if (signup.isloggedin()){
  getTodaysCourse();
}

function reloadCourse(){
  debug_log("reloadCourse called");
  var arr = [];
    
  AttendanceObject.please()
  .filter({"student_id":{"_eq":getStudentId()}})
  .then(function(response, raw) {
    response.forEach(function(attendance) {
      courses.forEach(function(course) {
        if(attendance.course == course.name) {
          arr.push({ //if the student has this class today
            start: course.start,
            name: course.name,
            beaconId: course.beaconId,
            end: course.end,
            color: attendance.color,
            enter: course.enter,
            exit: course.exit
          });
          debug_log(course.name + " pushed");
        }
      });
    });
    debug_log("arr[0].name: " + arr[0].name);
    if(arr.length != 0){
      courses.replaceAll(arr);
      makeCourseList(arr);
      startActivity();
      BeaconDetectorModule.checkAttendance("enter", "Computer Architecture", "11:00", "12:15", "10:57");
      BeaconDetectorModule.checkAttendance("exit", "Computer Architecture", "11:00", "12:15", "12:20");
      BeaconDetectorModule.checkAttendance("enter", "Database", "09:30", "10:45", "09:50");
      BeaconDetectorModule.checkAttendance("exit", "Database", "09:30", "10:45", "10:20");
    } 
  }) 
  .catch(function (reason) {
    console.log("attendance object load error: " + reason);
  }); 
}

// color : green "#1e852f", "#ec0707", "#ff0"

// Update attendance status(color)
function updateObj(courseName, color) {  
  AttendanceObject.please().filter({"student_id":{"_eq":getStudentId()}})
      .then(function(response, raw) {
        //debug_log("filtered: " + response);
        if(response.length) {
          response.forEach(function(attendance) {            
            if(attendance.course == courseName) {
              var updatedObject = {
                  color: color
                };
            
              var params = {
                id: attendance.id
              };

              AttendanceObject.please().update(params, updatedObject)
              .then(function (response, raw) {
                debug_log("updated color");
              })
              .catch(function (reason) {
                debug_log("update color error: " + reason);
              });

              return attendance.id;
            
            }
          });
        }
    })
    .catch(function (reason) {
      debug_log("update color error: " + reason);
    });
}

// Change a string to a date
function toDate(str, format) {
  var date = new Date();
  if (format == "h:m") {
    date.setHours(str.substr(0, str.indexOf(":")));
    date.setMinutes(str.substr(str.indexOf(":") + 1));
    date.setSeconds(0);
    return date;
  }
  else 
    return "Invalid Format";
}

// Get infos from beacon detection, show the result on display and call 'updateObj'
BeaconDetectorModule.checkAttendance = function(enterOrExit, name, start, end, time) {
  console.log("check attendance called " + enterOrExit + " " + name + " " + start + 
                " " + end + " " + time);
  var startTime = toDate(start, "h:m");
  var endTime = toDate(end, "h:m");
  var monitoredTime = toDate(time, "h:m");
  var thisColor = "NA";
  var enterTime = "NA";
  var exitTime = "NA";
 
  debug_log("time: " + monitoredTime);
  
  if(enterOrExit == "enter") {
    var timeDiff = (startTime.getTime() - monitoredTime.getTime()) / 60000;
    debug_log("timeDiff: " + timeDiff);
    enterTime = time;

    if(timeDiff >= -10 && timeDiff < 20) {
      // attended
      thisColor = "#1e852f";
    }
    else if(timeDiff < -10 && timeDiff > -30) {
      // late
      thisColor = "#ff0";
    }
    else {
      // absent
      thisColor = "#ec0707";
    }
  }
  else {
    // "exit"
    var timeDiff = (endTime.getTime() - monitoredTime.getTime()) / 60000;
    debug_log("timeDiff: " + timeDiff);
    exitTime = time;

    if(timeDiff > 20) {
      // go out earlier
      thisColor = "#ec0707";
    }
    else {
      thisColor = "NA";
    }
  }

  // Get the index of this course in the list
  var courseIndex;
  courses.forEach(function(course, index) {
      if(course.name == name) {    
        courseIndex = index;
        debug_log("course.enter: " + course.enter);
        debug_log("course.color: " + course.color);
        if(enterTime == "NA") {
          enterTime = course.enter;
        }
        if(thisColor == "NA") {
          thisColor = course.color;
        }
      }
  }); 

  updateObj(name, thisColor);
 
  // Replace the course object with new infos
  courses.replaceAt(courseIndex, 
    {
      start: start,
      name: name,
      color: thisColor,
      enter: enterTime,
      exit: exitTime
    });
};

var isLoading = Observable(false);

function endLoading(){
  isLoading.value = false;
}

function reloadHandler(){
  isLoading.value = true;
  reloadCourse();  
  setTimeout(endLoading, 2000); 
}

module.exports = {
  courses : courses,
  isLoading: isLoading,
  reloadHandler: reloadHandler,
  getTodaysCourse: getTodaysCourse
};
