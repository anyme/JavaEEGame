/**
 * Created by anastasia on 05/03/15.
 */

var repeatFileStatusCheck;
var repeatGameStatusCheck;
var request;
var filename;
var payload;
var repeatTimeout = 3000;

$(function() {
    $("#form").validate({
        rules : {
            file : {
                required : true
            }
        },
        messages : {
            file : {
                required : "Choisissez un fichier s'il vous plait",
            }
        },
        submitHandler: performAjaxSubmit
    });
});

function extractFilename() {
    var filename = document.getElementById("file").value;
    var lastIndex = filename.lastIndexOf("\\");
    if (lastIndex >= 0) {
        filename = filename.substring(lastIndex + 1);
    }

    return filename;
}

function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function validateUploadCallback() {
    var response;
    if (request.readyState == 4) {
        if (request.status == 200) {
            response = request.responseXML.getElementsByTagName("message")[0].childNodes[0].nodeValue;
            if (response === "success") {
                clearInterval(repeatFileStatusCheck);
                display("Le fichier a bien ete envoye");
                showStartButton();
            }
        }
    }
}

function validateUpload() {
    filename = extractFilename();
    var url = "CheckFileStatusServlet?filename=" + filename;

    initRequest();
    request.open("GET", url, true);
    request.onreadystatechange = validateUploadCallback;
    request.send(null);

}

function performAjaxSubmit() {
    var form = document.getElementById('#form');

    var file = document.getElementById("file").files[0];
    payload = new FormData();

    payload.append("file", file);

    initRequest()

    request.open("POST","UploadFileServlet", true);

    hideForm();
    display("Telechargement du fichier en cours...");

    request.send(payload);

    repeatFileStatusCheck = setInterval(function() {
        validateUpload();
    }, repeatTimeout);

}


function display(message) {
    $("#status").html(message);
}
function hideForm() {
    document.getElementById("form").style.display = "none";
}

function showForm() {
    document.getElementById("form").style.display = "block";
}
