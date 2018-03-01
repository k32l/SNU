var Observable = require('FuseJS/Observable');
var signup = require('/SignUp.js');

//Syncano course class
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

var courses = Observable({name: "No class today"});

function getStudentId(){
  var storage = require('FuseJS/Storage');
  var userData = storage.readSync("filedb.txt");
  var json = JSON.parse(userData);
  return json["student_id"];
}

//TODO: integrating with attendance info
function getTodaysCourse(){
  day = (new Date()).toString().substring(0,3); //get today's day like Mon, Tue, ...

  CourseObject.please().filter({"students_list":{"_contains":[getStudentId()]}}).then(function(res, raw){
    arr = [];
    if (res.length) {
      res.forEach(function(course) {
        if(course.day.includes(day)){
          arr.push({ //if the student has this class today
            time: course.start_time,
            name: course.course_name,
            color: "#1e852f",
            enter: "NA",
            exit: "NA",
            absence: course.num_absent,
            late: "NA"
          });
        }
      });
    }
    if(arr.length != 0){
      courses.replaceAll(arr);
    }})
    .catch(function (reason) {
        console.log("data load error: " + reason);
        courses.value = {name: "Couldn't load courses"};
      });
}
if (signup.isloggedin()){
  getTodaysCourse();
}
// color : green "#1e852f", "#ec0707", "#ff0"

//Syncano course class
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

var courses = Observable({name: "No class today"});

function getStudentId(){
  var storage = require('FuseJS/Storage');
  var userData = storage.readSync("filedb.txt");
  var json = JSON.parse(userData);
  return json["student_id"];
}

//TODO: integrating with attendance info
function getTodaysCourse(){
  console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
  console.log("getTodayCourse function called");
  day = (new Date()).toString().substring(0,3); //get today's day like Mon, Tue, ...

  CourseObject.please().filter({"students_list":{"_contains":[getStudentId()]}}).then(function(res, raw){
    arr = [];
    if (res.length) {
      res.forEach(function(course) {
        if(course.day.includes(day)){
          arr.push({ //if the student has this class today
            time: course.start_time,
            name: course.course_name,
            color: "#1e852f",
            enter: "NA",
            exit: "NA",
            absence: course.num_absent,
            late: "NA"
          });
        }
      });
      courses.replaceAll(arr);
    }})
    .catch(function (reason) {
        console.log("data load error: " + reason);
        courses.value = {name: "Couldn't load courses"};
      });
}

getTodaysCourse();
// color : green "#1e852f", "#ec0707", "#ff0"

/////////////////////////////////////////////////////////////////////////
var BeaconDetectorModule = require("BeaconDetectorModule");

var courseList = [{
    name: "Programming Principles",
    beaconId: "2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6/1/1",
    start: "01:20",
    end: "02:00",
    exit: "10:52",
    absence: 23,
    late: 2
  },
  {
    name: "Operating System",
    beaconId: "2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6/1/2",
    start: "00:00",
    end: "00:01",
    absence: 3,
    late: 22
  },
  {
    name: "Database",
    beaconId: "DF7E1C79-43E9-44FF-886F-1D1F7DA6997A/1/2",
    start: "01:13",
    end: "01:14",
    absence: 3,
    late: 22
  }];

var makeCourseList = function() {  
  for(var i = 0; i < courseList.length; i++) {
    var course = courseList[i];
    BeaconDetectorModule.pushCourse(course.name, course.beaconId, 
                                    course.start, course.end);
  }
}
 
var startActivity = function() {
  BeaconDetectorModule.start();
}

makeCourseList();
startActivity();

var monitoredCourse = {
  name: "",
  start: "",
  end: "",
  enter: "",
  exit: ""
};

BeaconDetectorModule.checkAttendance = function(enterOrExit, name, start, end, time) {
  console.log("check attendance called " + enterOrExit + " " + name + " " + start + 
              " " + end + " " + time);
};
/////////////////////////////////////////////////////////////////////////

var isLoading = Observable(false);

function endLoading(){
  isLoading.value = false;
}

function reloadHandler(){
  //TODO : update data
  isLoading.value = true;
  setTimeout(endLoading, 3000); //time should be modified
}
module.exports = {
  courses : courses,
  isLoading: isLoading,
  reloadHandler: reloadHandler
};
