
// var pass = $("#regPassword").val();
// $("#regPassword").on("input", function () { checkPassword(pass); })

// function checkPassword(input) {
//     var decimal = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
//     if (input.match(decimal)) {
//         // alert('Correct, try another...');
//         // return true;
//     }
//     else {
//         // alert('Wrong...!');
//         // return false;
//     }
// }
function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('txt').innerHTML =
    h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
  }
  function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
  }
