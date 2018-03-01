//TODO

//TODO: student list for each course
var students = [		
	{
		id: "2012-00000",
		name: "Sooram Kang",
		color: "#1e852f",
		absence: "3",
		late: "2"			
	},
	{
		id: "2012-00000",
		name: "Sooram Kang",
		color: "#1e852f",
		absence: "3",
		late: "2"			
	},
	{
		id: "2012-00000",
		name: "Sooram Kang",
		color: "#ec0707",
		absence: "3",
		late: "2"						
	},	
	{
		id: "2012-00000",
		name: "Sooram Kang",
		color: "#1e852f",
		absence: "3",
		late: "2"						
	},
	{
		id: "2012-00000",
		name: "Sooram Kang",
		color: "#1e852f",
		absence: "3",
		late: "2"						
	}				
];

//TODO: get student list for each course
function getStudents() {
    return new Promise(function(resolve, reject) {
        setTimeout(function() {
            resolve(students);
        }, 0);
    });
}

//update student's attendance status
function updateAttendance(id, name, color) {
    return new Promise(function(resolve, reject) {
        setTimeout(function() {
            for (var i = 0; i < students.length; i++) {
                var student = students[i];
                if (student.id == id) {
                    student.color = color;
                    break;
                }
            }
            resolve();
        }, 0);
    });
}

module.exports = {
	getStudents : getStudents,
	updateAttendance : updateAttendance
};