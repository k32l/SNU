var Observable = require('FuseJS/Observable');
var BeaconDetectorModule = require("BeaconDetectorModule");
var signup = require("SignUp.js");
var courses = Observable({name: "No classes"});

var courseList = [{
    name: "Computer Graphics",
    beaconId: "2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6/1/1",
    start: "01:20",
    end: "02:00",
    color: "#ec0707",
    enter: "NA",
    exit: "NA"
  },
  {
    name: "Automata Theory",
    beaconId: "2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6/1/2",
    start: "00:00",
    end: "00:01",
    color: "#ec0707",
    enter: "NA",
    exit: "NA"
  },
  {
    name: "Algorithms",
    beaconId: "DF7E1C79-43E9-44FF-886F-1D1F7DA6997A/1/2",
    start: "01:13",
    end: "01:14",
    color: "#ec0707",
    enter: "NA",
    exit: "NA"
  }];

var makeCourseList = function(courseList) {  
  debug_log("makeCourseList called ");
  for(var i = 0; i < courseList.length; i++) {
    var course = courseList[i];
    BeaconDetectorModule.pushCourse(course.name, course.beaconId, 
                                    course.start, course.end);
  }
}
 
var startActivity = function() {
  BeaconDetectorModule.start();
}

if(signup.isLoggedIn) {
  courses.replaceAll(courseList);
  makeCourseList(courseList);
  startActivity();
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

module.exports = {
  courses : courses
};
