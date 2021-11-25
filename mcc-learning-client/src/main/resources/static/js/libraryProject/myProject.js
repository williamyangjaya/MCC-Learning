let myProject = new Object();
let table = null;
let projectData = new Object();
let projectDataJudul = new Object();
let idJudul = "";
let historyStatus = null;
let link = null;
let idUser = 0;
let idProject = null;
let project = new Object();


$(document).ready(() => {
    
    getIdUser();
    
    console.log(getAll);
    
    $("#submitJudul").submit((e) => {
        e.preventDefault();
        formValidation(create);
    });
    
    $("#myProjectUpdate").submit((e) => {
        e.preventDefault();
        formValidation(update);
    });
    
    $("#myProjectJudul").submit((e) => {
        e.preventDefault();
        formValidation(updateJudul);
    });
});

function getAll() {
    table = $('#myProject').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: `/finalProject/myProject/${idUser}`,
            datatype: "json",
            dataSrc: ""
        },
        columns: [
            {
                data: "id", name: "No", autoWidth: true,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {
                data: "title", name: "Judul", autoWidth: true
            },
            {
                data: "name", name: "Nama Trainee", autoWidth: true
            },
            {
                data: "trainer", name: "Nama Trainer", autoWidth: true
            },
            {
                data: "decription", name: "Deskripsi", autoWidth: true
            },
            {
                data: "erd", name: "erd", autoWidth: true,
                fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                    if (oData.erd) {
                        $(nTd).html("<a target='_blank' href=' " + oData.erd + "'>" + "click here" + "</a>");
                    }
                }
            },
            {
                data: "uml", name: "uml", autoWidth: true,
                fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                    if (oData.uml) {
                        $(nTd).html("<a target='_blank' href=' " + oData.uml + "'>" + "click here" + "</a>");
                    }
                }
            },
            {
                data: "skema", name: "Skema", autoWidth: true,
               
                fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                    if (oData.skema) {
                        $(nTd).html("<a target='_blank' href=' " + oData.skema + "'>" + "click here" + "</a>");
                    }
                }
            },
            {
                data: "link", name: "Link Full Project", autoWidth: true,
                fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                    if (oData.link) {
                        $(nTd).html("<a target='_blank' href=' " + oData.link + "'>" + "click here" + "</a>");
                    }
                }
            },
            {
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#update-my-project"
                            onclick="getById('${row.idProject}')">
                            
                            <i class='fas fa-sm fa-pencil-alt'></i> Link
                        </button>
                        <br><br>
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#update-judul-project"
                            onclick="getByIdJudul('${row.idProject}')"> Judul
                            
                            <i class='fas fa-sm fa-pencil-alt'></i> 
                        </button>
                        
                    
                    `;
                }
            }
            
        ]
    });
}

function condition(idUser) {
    if (idUser === null) {
        console.log("ISI PROJECT KOEH");
        $("#projectKoeh").remove();
    } else {  
        $("#createProject").remove();
        
    }
}

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

function getIdUser() {
    $.ajax({
        url: "/user",
        type: 'GET',
        success: (res) => {
            console.log(res);
            idUser = res;
            getAll();
            getByIdJudul(res);
            getHistoryData(idUser);
            getMyProject(idUser);
        },
        error: (err) => {
        }
    });
}

function getMyProject(id) {
    console.log(id);
    $.ajax({
        url: `/finalProject/myProject/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res.idProject);
            this.idProject = res.idProject;
            projectData = res;
            setForm(res);
            condition(res.idProject);  
        }
    });
}

function getByIdForUpdateJudul() {
    //this.idProject = $("#btn-submitJudul").val();
    console.log("isi baris 203 = " + this.idProject);
    $.ajax({
        url: `/finalProject/${this.idProject}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            projectData = res;
            //setForm(res);
            setFormJudul(res);
            
        }
    });
}

function getByIdForSubmitLink() {
    idProject = $("#btn-submitProject").val();
    console.log(idProject);
    $.ajax({
        url: `/finalProject/${idProject}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            setForm(res);
        }
    });
}

function getByIdForUpdateJudul2() {
    this.idProject = $("#btn-submitJudul2").val();
    console.log(idProject);
}

function update() {
    project = {
        erd: $("#erd").val(),
        uml: $("#uml").val(),
        skema: $("#skema").val(),
        link: $("#link").val(),
        idProject: projectData.idProject
    };
    
    console.log(projectData.idProject);
   
    let id = $("#idProject").val();
    
    $.ajax({
        url: `/finalProject/update-link/${projectData.idProject}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(project),
        success: (res) => {
             console.log(project);
            console.log(res);
            $("#update-my-project").modal("hide");
            window.location.href =  "/history/my-history";
        },
        error: (err) => {
            errorAlert("Project failed updated");
            console.log("update error");
        }
    });
}

function setForm(data) {
    $("#judul").val(data.title);
    $("#nama").val(data.name);
    $("#trainer").val(data.trainer);
    $("#deskripsi").val(data.decription);
    $("#erd").val(data.erd);
    $("#uml").val(data.uml);
    $("#skema").val(data.skema);
    $("#link").val(data.link);
}

function getByIdJudul(idProject) {
    idProject = $("#btn-submitProject").val();
    console.log("Isi getByIdJudul" + idProject);
    $.ajax({
        url: `/finalProject/judul/${idProject}`,
        type: 'GET',
        success: (res) => {
            console.log("isi console log baris 283" + res);
            projectDataJudul = res;
            idJudul = idProject;
            setFormJudul(res);
            condition();
        }
    });
}

function updateJudul() {
    project = {
        judul: $("#judul1").val(),
        deskripsi: $("#deskripsi1").val()
        //idProject: $projectData.idProject
    };
    console.log(projectData.idProject);
   
    $.ajax({
        url: `/finalProject/update-judul/${projectData.idProject}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(project),
        success: (res) => {
            console.log(project);
            console.log(res);
            $("#update-judul-project").modal("hide");
            window.location.href =  "/history/my-history";
        },
        error: (err) => {
            errorAlert("Project failed updated");
            console.log(project);
        }
    });
}

function setFormJudul(lala) {
    console.log(lala);
    $("#nama1").val(lala.name);
    $("#trainer1").val(lala.trainer);
     $("#judul1").val(lala.title);
    $("#deskripsi1").val(lala.decription);
}

/*
function gotoHistory() {
    window.location.href= "/libraryProject/myHistory";
}
*/

function getHistoryData(idUser) {
    $.ajax({
        url: `/history/myHistory/${idUser}`,
        type: 'GET',
        success: (res) => {
            hideElement(res);
            console.log(res);
        }
    });
}


function hideElement(res) {
    console.log(res);

    historyStatus = res.splice(res.length - 1);
    console.log(historyStatus[0].status);
    
    if(historyStatus[0].status === "Menunggu approval judul" || historyStatus[0].status === "Menunggu approval link project") {
        $("#btn-submitProject").remove();
        $("#btn-submitJudul").remove();
        
    } else if(historyStatus[0].status === "Judul ditolak") {
        $("#btn-submitProject").remove();
    }  else if(historyStatus[0].status === "Development") {
        $("#btn-submitJudul").remove();
    } else if (historyStatus[0].status === "Link project ditolak") {
        $("#btn-submitJudul").remove();
    } else {
        $("#btn-submitProject").remove();
        $("#btn-submitJudul").remove();
    }
    
//    historyStatus = getHistoryData
    
} 

