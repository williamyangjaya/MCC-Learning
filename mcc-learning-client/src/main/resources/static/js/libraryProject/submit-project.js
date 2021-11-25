let submit_project = new Object();
let table = null;

$(document).ready(() => {
    getAll();
    $("#submitProject").submit((e) => {
        e.preventDefault();
        formValidation(update);
    });
});

function getById(id) {
    this.idProject = id;
    $.ajax({
        url: `/finalProject/${id}`,
        type: 'GET',
        success: (res) => {
            setForm(res);
        }
    });
}

/*
function update() {
    project = {
        idProject: $("#idProject").val(),
        judul: $("#judul").val(),
        deskripsi: $("#deskripsi").val(),
        idMcc1: $("#idMcc").val(),
        idMcc2: $("#idMcc").val(),
        idMcc3: $("#idMcc").val(),
        erd: $("#erd").val(),
        uml: $("#uml").val(),
        skema: $("#skema").val(),
        link: $("#link").val()
    };
    
    console.log(project)

    let id = $("#idProject").val();
    $.ajax({
        url: `/trainee/${id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(project),
        success: (res) => {
            successAlert("Full Project Updated");
            table.ajax.reload();
            $("#update-project").modal("hide");
            window.location.href = "/project/my-project";
        },
        error: (err) => {
            console.log("Project gagal di update libnkanyasa")
            errorAlert("Full Project failed updated");
        }
    });
} */

function setForm(data) {
    $("#idProject").val(data.idProject);
    $("#judul").val(data.judul);
    $("#deskripsi").val(data.deskripsi);
    $("#idMcc").val(data.idMcc);
    $("#idMcc").val(data.idMcc);
    $("#idMcc").val(data.idMcc);
    $("#erd").val(data.erd);
    $("#uml").val(data.uml);
    $("#skema").val(data.skema);
    $("#link").val(data.link);
}

    