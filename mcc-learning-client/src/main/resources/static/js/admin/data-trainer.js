let trainee = new Object();
let table = null;

$(document).ready(() => {
    getAll();
});

function getAll() {
    table = $('#trainerTable').DataTable({
        filter: true,
        orderMulti: true,
        ajax: {
            url: "/admin/get-trainer",
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
                data: "name", name: "Nama Trainee", autoWidth: true
            },
            {
                data: "email", name: "Email", autoWidth: true
            },
            {
                data: "kelas", name: "class", autoWidth: true
            }
        ],
        success: (res) => {
        }
    });
}
