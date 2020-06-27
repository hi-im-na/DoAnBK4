
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


function createTable() {

    // thiet lap container
    var container = document.getElementById("container");
    // thiet lap so o 
    var countCell = parseInt(cell.value);
    var countAddCell = parseInt(addCell.value);
    var countCells = countCell + countAddCell;
    alert(countCell);
    // thiet lap so cot
    if (countCells > 6) var countColumn = 7;
    else var countColumn = 0;

    // thiet lap so dong
    if (countColumn > 0) {
        var countRow = parseInt(countCells / countColumn);
        //alert(countRow);
        var soODura = countCells % countColumn;
        //alert(soODura);
    } else {
        var soODura = countCells;
    }

    var tagTable = document.createElement("table");

    tagTable.border = 1;

    // ve bang 
    for (var i = 0; i < countRow; i++) {
        var tagRow = document.createElement("tr");
        tagTable.appendChild(tagRow);

        for (var j = 0; j < countColumn; j++) {
            var tagColumn = document.createElement("td");
            var textNode = document.createTextNode(i + " - " + j);
            tagColumn.appendChild(textNode);
            tagRow.appendChild(tagColumn);
        }
    }

    // ve so o du ra
    var tagRow = document.createElement("tr");
    if (soODura != 0) tagTable.appendChild(tagRow);
    while (soODura--) {
        var tagColumn = document.createElement("td");
        var textNode = document.createTextNode("new");
        tagColumn.appendChild(textNode);
        tagRow.appendChild(tagColumn);
    }

    container.appendChild(tagTable);

    return true;

}

{/* <form action="">
  Số ô: <input type="text" id="ô">
  <br>
  Số ô trống: <input type="text" id="otrong">
  <br>
  <input type="button" value="Tạo table" onclick="return createTable()">
</form>

<br>
  
<div id="container">

</div> html */}