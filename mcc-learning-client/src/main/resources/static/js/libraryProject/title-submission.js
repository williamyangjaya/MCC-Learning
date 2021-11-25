let title = new Object();
let table = null;
let validasi = new Object();
let projectData = new Object() ;

$(document).ready(() => {
    getAll();
    
    $("#detailTitle").submit((e) => {
        e.preventDefault();
        formValidation(create);
    });

});
function getAll() {
    table = $('#titleSubmission').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/finalProject/title",
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
                data: "batch", name: "Batch", autoWidth: true
            },
            {
                data: "judul", name: "Judul Project", autoWidth: true
            },
            {
                data: "trainer", name: "Trainer", autoWidth: true
            },

            {
                render: (data, type, row, meta) => {
                    return `
                             <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#pesan-project"
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
    $.ajax({
        url: `/finalProject/${id}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            projectData = res;
             setForm(res);
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
        url: `/finalProject/validasi-judul/${projectData.idProject}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(validasi),
        success: (res) => {
            $("#pesan-project").modal("hide");
//            table.ajax.reload();
            successAlert("Title Validated");
            setInterval(function(){ window.location.reload(); }, 1000);
        },
        error: (err) => {
            errorAlert("Title Failed to Validate");
        }
    });
}

function setForm(data) {
    $("#nama-trainee").val(data.name);
    $("#judul-trainee").val(data.title);
    $("#deskripsi-trainee").val(data.decription);
}

function rejectTitle() {
    validasi = {
        status: $("#statusRejected").val(),
        note: $("#pesan").val()
    };
    console.log(validasi.status);

    $.ajax({
        url: `/finalProject/validasi-judul/${projectData.idProject}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(validasi),
        success: (res) => {
            table.ajax.reload();
            successAlert("Title Rejected");
            $("#pesan-project").modal("hide");
        },
        error: (err) => {
            errorAlert("Title Failed to Reject");
        }
    });
} 