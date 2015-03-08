/**
 * Created by anastasia on 05/03/15.
 */

var repeatFileStatusCheck;
var repeatGameStatusCheck;
var filename;
var payload;
var repeatTimeout = 3000;
var repeatTimestamp = 30000;
var currentTime = 0;

$(function() {
    $("#form").validate({
        rules : {
            file : {
                required : true
            }
        },
        messages : {
            file : {
                required : "Choisissez un fichier s'il vous plait"
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
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
         return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function validateUploadCallback(request) {
    var response;

    if (request.readyState == 4) {
        if (request.status == 200) {
            response = request.responseXML.getElementsByTagName("message")[0].childNodes[0].nodeValue;
            if (response === "success") {
                currentTime = 0;
                clearInterval(repeatFileStatusCheck);
                display("Le fichier a bien ete envoye");
                showStartButton();
            }
        } else {
            currentTime = 0;
            clearInterval(repeatFileStatusCheck);
            display(e);
            showForm();
        }
    }
}

function validateUpload() {
    filename = extractFilename();
    var url = "CheckFileStatusServlet?filename=" + filename;

    var request = initRequest();
    request.open("GET", url, true);
    request.onreadystatechange = (function(request) {
        return function() {
            validateUploadCallback(request);
        }
    })(request);
    request.send(null);

}
function parseResponse(response) {
    return response.replace(/\\n/g, "<br />");
}

function checkGameStatusCallback(request) {
    var response;
    if (request.readyState == 4) {
        if (request.status == 200) {
            response = request.responseXML.getElementsByTagName("message")[0].childNodes[0].nodeValue;
            if (response !== "fail") {
                currentTime = 0;
                clearInterval(repeatGameStatusCheck);
                response = parseResponse(response);
                display("Position finale des tondeuses: <br/><br/>" + response);
                showForm();
            }
        } else {
            currentTime = 0;
            clearInterval(repeatGameStatusCheck);
            display(e);
            showForm();
        }
    }

}

function checkGameStatus() {
    var url = "CheckGameStatusServlet?filename=" + filename;

    var request = initRequest();
    request.open("GET", url, true);
    request.onreadystatechange = (function(request) {
        return function() {
            checkGameStatusCallback(request);
        }
    })(request);
    request.send(null);

}

function performAjaxSubmit() {
    var form = document.getElementById('#form');

    var file = document.getElementById("file").files[0];
    payload = new FormData();

    payload.append("file", file);

    var request = initRequest();

    request.onreadystatechange = (function(request) {
        return function() {
            handleServerResponse(request);
        }
    })(request);
    request.open("POST","UploadFileServlet", true);

    hideForm();
    display("Telechargement du fichier en cours...");

    request.send(payload);

    repeatFileStatusCheck = setInterval(function() {
        currentTime += repeatTimeout;
        if (currentTime > repeatTimestamp) {
            currentTime = 0;
            clearInterval(repeatFileStatusCheck);
            display("Serveur n'a pas repondu");
            showForm();
        }
        validateUpload();
    }, repeatTimeout);

}

function handleServerResponse(request) {
    if (request.readyState == 4) {
        if (request.status == 500) {
            clearInterval(repeatFileStatusCheck);
            clearInterval(repeatGameStatusCheck);
            showForm();
            hideStartButton();
            display(request.responseText);
        }
    }
}


function startGameRequest() {
    var url = "StartGameServlet?filename=" + filename;
    var request = initRequest();

    request.onreadystatechange = (function(request) {
        return function() {
            handleServerResponse(request);
        }
    })(request);
    request.open("POST", url, true);
    request.send(null);
    repeatGameStatusCheck = setInterval(function() {
        currentTime += repeatTimeout;
        if (currentTime > repeatTimestamp) {
            currentTime = 0;
            clearInterval(repeatGameStatusCheck);
            display("Serveur n'a pas repondu");
            showForm();
        }
        checkGameStatus();
    }, repeatTimeout);
    display("En attent des resultats...")
    hideStartButton();
}

function display(message) {
    $("#status").html(message);
}
function showStartButton(){
    document.getElementById("start").style.display = "block";
}
function hideStartButton(){
    document.getElementById("start").style.display = "none";
}
function hideForm() {
    document.getElementById("form").style.display = "none";
}

function showForm() {
    document.getElementById("form").style.display = "block";
}
