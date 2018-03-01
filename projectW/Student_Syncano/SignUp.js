var Observable = require("FuseJS/Observable");
var PermissionModule = require("PermissionModule");

var getPermission = function() {
  PermissionModule.getInternet();
}

getPermission();

//Syncano student class
var Syncano = require('syncano-js/dist/syncano.fuse.js');
var ApiKeys = require("api-keys.js");
var classname = "student";
var connection = Syncano({
  accountKey: ApiKeys.accountKey,
  defaults: {
    instanceName: ApiKeys.instanceName,
    className: classname
  }
});
var StudentObject = connection.DataObject;

//for token
var storage = require('FuseJS/Storage');

//check if user has been logged in
var loggedin = Observable();
//NOTE - need a starting page before the page with signup button
function isloggedin(){
  if (storage.readSync("filedb.txt").length){
    loggedin.value = true;
    return true;
  } else {
    loggedin.value = false;
    return false;
  }
}
isloggedin();

//triggered when user pressed singup done button
var loginfail = Observable();
var name = Observable("");
var student_id = Observable("");
var email = Observable("");
var constraintErrMsg = Observable("");
// When authenticated, success will be true and fail will be false. When not authenticated, vice versa.
function authenticate() {
  var studentObject = {
    "name": name.value,
    "student_id": student_id.value,
    "email": email.value,
  };
  
  // Checking constraint
  if (name.value == ""){
    constraintErrMsg.value = "Please enter your name";
    loginfail.value = true;
    return false;
  } 
  else if (!(/20\d{2}-\d{5}$/.test(student_id.value))){
    constraintErrMsg.value = "Please check your student id format";
    loginfail.value = true;
    return false;
  }
  else if (!(/.+@snu[.]ac[.]kr$/.test(email.value))){
    constraintErrMsg.value = "Please check your SNU Email";
    loginfail.value = true;
    return false;
  }
  else {
    // student id and email should be unique
    doesExist = false;
    StudentObject.please().filter({"student_id":{"_eq":student_id.value}})
    .then(function(response){
      if (response.length){
        loginfail.value = true;
        constraintErrMsg.value = "This student id is already registered";
        doesExist = true;
      }
      else {
        StudentObject.please().filter({"email":{"_eq":email.value}}).then(function(response){
          if (response.length){
            loginfail.value = true;
            constraintErrMsg.value = "This email is already registered";
            doesExist = true;
          }});
      }
    })
    .then(function(){
      if (!doesExist){
        // If given data is unique and well-formed, put it in db
        StudentObject.please().create(studentObject).then(function (response, raw) {
          //console.log("Added class " + JSON.stringify(response)); 
          storage.write("filedb.txt", JSON.stringify(response)); //write to file
        })
        .catch(function (reason) {
          console.log("forceClass create error: " + reason);
        })
        .then(function(){
          loggedin.value = true;
          loginfail.value = false;
          name.value = "";
          student_id.value = "";
          email.value = "";
        });
        return;
      }
    //TODO: Fix Bug) It shows "Try Again!" even when it's succeeded
    })
  }
}


module.exports = {
  authenticate: authenticate,
  loginfail: loginfail,
  constraintErrMsg: constraintErrMsg,
  name: name,
  student_id: student_id,
  email: email,
  isloggedin: isloggedin,
  loggedin: loggedin
};