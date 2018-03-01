//TODO
var Observable = require("FuseJS/Observable");
var Backend = require("students");

var students = Observable();

Backend.getStudents()
    .then(function(newStudents) {
        students.replaceAll(newStudents);
    })
    .catch(function(error) {
        console.log("Couldn't get students: " + error);
    });

function updateAttendance(id, name, color) {
    for (var i = 0; i < students.length; i++) {
        var student = students.getAt(i);
        if (student.id == id) {
            student.color = color;
            students.replaceAt(i, student);
            break;
        }
    }
    Backend.updateAttendance(id, name, color)
        .catch(function(error) {
            console.log("Couldn't update attendance: " + id);
        });
}

module.exports = {
    students : students,
    updateAttendance : updateAttendance
};