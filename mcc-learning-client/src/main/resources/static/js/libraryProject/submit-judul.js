let project = new Object();
let table = null;
$(document).ready(() => {

    $("#submitJudul").submit((e) => {
        e.preventDefault();
        formValidation(create);
    });
});

function create() {
    project = {
        judul: $("#judul").val(),
        deskripsi: $("#deskripsi").val(),
        idMccSatu: $("#idMccSatu").val(),
        idMccDua: $("#idMccDua").val(),
        idMccTiga: $("#idMccTiga").val()

    };
    console.log(project);
    
    $.ajax({
        url: `/finalProject/trainee`,
        type: 'POST',
        data: JSON.stringify(project),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: (res) => {
            console.log("Success");
            
            $("#create-judul").modal("hide");
            document.getElementById("createForm").reset();
            window.location.href = `/history/my-history`;
        },
        error: function (err) {
            console.log("gagal");
            
            successAlert("submit title successfully");
            $("#create-judul").modal("hide");
            window.location.href = `/history/my-history`;
        }
    });
}

