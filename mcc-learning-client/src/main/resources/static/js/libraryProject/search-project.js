let searchProject = new Object();
let table = null;

$(document).ready(() => {
    getAll();
    
    $("#detailProject").submit((e) => {
        e.preventDefault();
        formValidation(create);
    });
});

function getAll() {
    table = $('#cariTabel').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/finalProject/cari",
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
                render: (data, type, row, meta) => {
                    return `
                        <button 
                            class='btn btn-sm btn-primary'
                            data-toggle="modal" 
                            data-target="#search-project"
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

function setFormProject(data) {
    console.log(data);
    $("#nama_trainer").val(data.trainer);
    $("#deskripsi_trainee").val(data.decription);
    $("#skema_trainee").val(data.skema);
    $("#link_trainee").val(data.link);
}