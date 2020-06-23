
var pass = $("#regPassword").val();
$("#regPassword").on("input", function () { checkPassword(pass); })

function checkPassword(input) {
    var decimal = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
    if (input.match(decimal)) {
        // alert('Correct, try another...');
        // return true;
    }
    else {
        // alert('Wrong...!');
        // return false;
    }
}


// function validateEmail(email) {
//     var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
//     return re.test(String(email).toLowerCase());
// }

// $("#regEmail").change(function validate() {

//     if (validateEmail(email)) { // lay gia tri da nhap trong email
//         $("#result1").text(" "); // nay de lam gi 
//     }

//     else
//         $("#result1").text("Must be a valid email address.");


// })