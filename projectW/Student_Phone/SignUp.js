var Observable = require("FuseJS/Observable");

var success = Observable();
var fail = Observable();
var isLoggedIn = Observable();

// When authenticated, success will be true and fail will be false. 
// When not authenticated, vice versa.
function authenticate() {
  success.value = true;
  fail.value = false; 
  setTimeout(setLoggedIn, 100);
}

function setLoggedIn() {
  isLoggedIn.value = true;
}


module.exports = {
  authenticate: authenticate,
  success: success,
  fail: fail,
  isLoggedIn: isLoggedIn
};