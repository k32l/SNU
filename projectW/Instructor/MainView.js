//TODO
var Context = require("Context");

var student = this.Parameter;

var id = student.map(function(x) { return x.id; });
var name = student.map(function(x) { return x.name; });
var color = student.map(function(x) { return x.color; });

//TODO: save updated student's attendance status
function save() {
    Context.updateAttendance(student.value.id, name.value, color.value);
}	

//go back to course page
function goBack() {
	router.goBack();
}

module.exports = {
	students : Context.students,
	id : id,
	name : name,
	color : color,
	save : save,		
	goBack:goBack
};