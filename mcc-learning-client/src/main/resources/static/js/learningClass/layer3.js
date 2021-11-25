let idClass = "";
let category = "";
let file;
let title;
let module;
let exam;

let idExam;
let moduleName;

$(document).ready(() => {
    getidClassAndCategory();
//    getExamById(1);
    

    $("#createModule").submit((e) => {
        e.preventDefault();
        uploadModule();
    });
    
    $("#formUploadAnswer").submit((e) => {
        e.preventDefault();
        console.log("FAKYU");
        uploadAnswer();
    });
});

function getidClassAndCategory() {

    idClass = $("#idClass").val();
    category = document.getElementById("categoryName").innerHTML;


    console.log("ID CLASS = " + idClass);
    console.log("CATEGORY = " + category)

}


function uploadModule() {

    getidClassAndCategory();
    
    let formData = new FormData();
    let file = $('#files')[0].files[0];
    
    formData.append('file', file);
    formData.append('title', $("#judulModul").val());
    formData.append('idClass', idClass);
    formData.append('category', category);
    
    console.log("FILEE" + file);


    $.ajax({
        url: 'http://localhost:8082/module/uploadFile',
        type: 'POST',
        data: formData,
        enctype : 'multipart/form-data',
        contentType : false,
        cache : false,
        processData : false,
        success: (res) => {
            console.log("SUCCESS" + res);
            successAlert("Upload Module successfully");
            $('#moduleModal').modal('hide');
            document.getElementById("createModule").reset();
        },
        error: (err) => {
            console.log("ERROR" + err);
        }
    });
}

function getExamById(idExam, moduleName) {
    console.log("GET EXAM BY ID = " + idExam);
    this.idExam = idExam;
    
    $.ajax({
        url: `/exam/${idExam}`,
        type: `GET`,
        success: (res) => {
            console.log(res);
            exam = res;
        }
    });
    
    $('#examName').val(moduleName);
    $('#btnDownloadExam').attr("href", exam.url);
    $('#deadline').val(exam.deadline);
}



function uploadAnswer() {
    
    let formData = new FormData();
    let file = $('#uploadAnswer')[0].files[0];
    
    console.log("HALOO "+this.idExam);
    console.log("FILEEE " + file);
    
    formData.append('file', file);
    formData.append('idEmployee', document.getElementById('employeeId').innerHTML);
    formData.append('idExam', this.idExam);
    
    $.ajax({
        url: 'http://localhost:8082/answer/uploadFile',
        type: 'POST',
        data: formData,
        enctype : 'multipart/form-data',
        contentType : false,
        cache : false,
        processData : false,
        success: (res) => {
            console.log("SUCCESS" + res);
            successAlert("Upload Answer successfully");
            $('#modalInsertExam').modal('hide');
            document.getElementById("formUploadAnswer").reset();
        },
        error: (err) => {
            console.log("ERROR" + err);
        }
    });
}