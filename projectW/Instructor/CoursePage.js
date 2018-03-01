//TODO
var Observable = require('FuseJS/Observable');

// course list
courses = Observable();

// get an array of course info and update
function loadCourses() {
	setTimeout(function() {
		courses.replaceAll([
	{
		time: "09:00",
		name: "Programming Principles",
		total: "92",
		absence: "3"
	},
	{
		time: "11:00",
		name: "Operating System",
		total: "45",
		absence: "10"
	}
	]);
	}, 0);
}

loadCourses();

var students = require("students");

//go to MainView with different student list
//TODO
function goToMain(arg) {
   	var student = arg.data;
 	router.push("mainView", student);
}

var isLoading = Observable(false);	

//finish updating
function endLoading(){
	isLoading.value = false;
}

//update data
function reloadHandler(){
	//TODO 
	isLoading.value = true;
	setTimeout(endLoading, 3000);
}	

module.exports = {
	courses : courses,
	isLoading: isLoading,
	students: students,
	goToMain: goToMain,	
	reloadHandler: reloadHandler
};