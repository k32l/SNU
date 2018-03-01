var Observable = require('FuseJS/Observable');
var signup = require('/SignUp.js');
var coursePage = require('/CoursePage.js');
// var Timer = require("FuseJS/Timer");

var Syncano = require('syncano-js/dist/syncano.fuse.js');
var ApiKeys = require("api-keys.js");

// Course class
var connection = Syncano({
    accountKey: ApiKeys.accountKey,
    defaults: {
      instanceName: ApiKeys.instanceName,
      className: "course"
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

// Student class
var connection3 = Syncano({
  accountKey: ApiKeys.accountKey,
  defaults: {
    instanceName: ApiKeys.instanceName,
    className: "student"
  }
});
var StudentObject = connection3.DataObject;

var courses = Observable({name: "No classes"});

function getStudentId(){
  var storage = require('FuseJS/Storage');
  var userData = storage.readSync("filedb.txt");
  var json = JSON.parse(userData);
  return json["student_id"];
}

var called = 0;
// Check a student's attendance object for each class has been created
function checkObjCreated() {
	debug_log("checkObjCreated called");
	
	StudentObject.please()
	.filter({"student_id":{"_eq":getStudentId()}})
	.then(function(res, raw) {
		debug_log("obj_created value: " + res[0].obj_created);

		if(res[0].obj_created != true) {
			CourseObject.please()
			.filter({"students_list":{"_contains":[getStudentId()]}})
			.then(function(res, raw){
			    if (res.length) {
			    	// Create attendance object for each class
			    	res.forEach(function(course) {
			    		var attendanceObject = {
				          course: course.course_name,
				          color: "#ec0707",
				          num_absent: 0,
				          num_late: 0,
				          student_id: getStudentId()
				        };
				        AttendanceObject.please().create(attendanceObject)
				        .then(function (response, raw) {
				          debug_log("attendance object create success");
				        })
				        .catch(function (reason) {
				          debug_log("attendance object create error: " + reason);
				        });
			      	});
			    }
			});  

		    var updatedObject = {
              	obj_created: true
            };
        
          	var params = {
            	id: res[0].id
          	}; 

          	// Update obj_created field
          	StudentObject.please().update(params, updatedObject)
          	.then(function (response, raw) {
            	debug_log("updated obj_created");
          	})
          	.catch(function (reason) {
            	debug_log("update obj_creted error: " + reason);
          	});
		}
	})
	.catch(function (reason) {
		debug_log("get student obj error: " + reason);
	})
	
	setTimeout(getCourse, 500);	
}

if (signup.isloggedin()) {
	checkObjCreated();
}

// Get courses(retrieve already created attendance objects)
function getCourse(){
	AttendanceObject.please()
	  .filter({"student_id":{"_eq":getStudentId()}})
	  .then(function(res, raw){
	    arr = [];
	    if (res.length) {
	      res.forEach(function(attendance) {      
	          arr.push({ 
	            name: attendance.course,
	            absence: attendance.num_absent,
	            late: attendance.num_late
	          });
	        
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

var isLoading = Observable(false);

function endLoading(){
  isLoading.value = false;
}

function reloadHandler(){
	isLoading.value = true;
	checkObjCreated();	
  	setTimeout(endLoading, 3500); 
}

// Timer.create(function() {
//     console.log("This will run everyday until forever");

// 	AttendanceObject.please().list()
// 	    .then(function(items) {
// 	        items.forEach(function(item) {
// 	            if (item.color == "#ff0") { // if late, increment num_late 
// 	                var itemToUpdate = {
// 	                    id: item.id,
// 	                    className: className
// 	                };
	                
// 	                var num = item.num_late + 1;
// 	                var updatedNum = {
// 	                    num_late: num
// 	                };
	                
// 	                AttendanceObject.please().update(itemToUpdate, updatedNum).then(function(){
// 	                    console.log("Item successfully updated"); 
// 	                })
// 	                .catch(function (reason) {
// 	                    debug_log("update color error: " + reason);
// 	                });
// 	            }
// 	            else if (item.color == "#ec0707") {
// 	                var itemInfo = {
// 	                    id: item.id,
// 	                    className: className
// 	                };
	                
// 	                var num2 = item.num_absent + 1;
// 	                var updated = {
// 	                    num_absent: num2
// 	                };
	                
// 	                connection.DataObject.please().update(itemInfo, updated).then(function(){
// 	                    console.log("Item successfully updated"); 
// 	                })
// 	                .catch(function (reason) {
// 	                    debug_log("update color error: " + reason);
// 	                });
// 	            }
// 	        });
// 	    });
// }, 86400000, true);

module.exports = {
		courses : courses,
		isLoading: isLoading,
		reloadHandler: reloadHandler
};