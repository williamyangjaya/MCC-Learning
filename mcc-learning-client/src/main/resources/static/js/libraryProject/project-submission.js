let title = new Object();
let table = null;
let validasi = new Object();
let projectData = new Object();

$(document).ready(() => {
    getAll();

    $("#detailProject").submit((e) => {
        e.preventDefault();
        formValidation(create);
    });


});

function getAll() {
    table = $('#projectSubmission').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/finalProject/full-project",
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
                data: "title", name: "Judul Project", autoWidth: true
            },

            {
                data: "link", name: "Link Project", autoWidth: true,
                fnCreatedCell: function (nTd, sData, oData, iRow, iCol) {
                    if (oData.link) {
                        $(nTd).html("<a target='_blank' href=' " + oData.link + "'>" + "click here" + "</a>");
                    }
                }
            },
            {
                data: "trainer", name: "Nama Trainer", autoWidth: true
            },

            {
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#full-project"
                            onclick="getById('${row.idProject}')">
                            <i class="fas fa-clipboard-check"></i> Detail
                        </button>
                     
                    `;
                }
            }
        ]
    });
}

function getById(id) {
    this.idProject = id;
    console.log(id);
    $.ajax({
        url: `/finalProject/get-project/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            projectData = res;
            setFormProject(res);
        }
    });
}

function create() {
    validasi = {
        status: $("#statusApproved").val(),
        note: $("#pesan").val()
    };
    console.log(validasi);
    console.log(projectData.idProject);

    $.ajax({
        url: `/finalProject/validasi-link/${projectData.idProject}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(validasi),
        success: (res) => {
            table.ajax.reload();
            successAlert("Full Project Validated");
            $("#full-project").modal("hide");
//            setInterval(function(){ window.location.reload(); }, 1000);
        },
        error: (err) => {
            errorAlert("Project failed validate");
        }
    });
}

function setFormProject(data) {
    console.log(data);
    $("#nama_trainee").val(data.name);
    $("#judul_trainee").val(data.title);
    $("#deskripsi_trainee").val(data.decription);
    $("#erd_trainee").attr('href', data.erd);
    $("#uml_trainee").attr('href', data.uml);
    $("#skema_trainee").attr('href', data.skema);
    $("#link_trainee").attr('href', data.link);
}


function rejectTitle() {
    validasi = {
        status: $("#statusRejected").val(),
        note: $("#pesan").val()
    };
    console.log(validasi.status);

    $.ajax({
        url: `/finalProject/validasi-link/${projectData.idProject}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(validasi),
        success: (res) => {
            table.ajax.reload();
            successAlert("Full Project Rejected");
            $("#full-project").modal("hide");
        },
        error: (err) => {
            errorAlert("Project failed to Reject");
        }
    });
} 