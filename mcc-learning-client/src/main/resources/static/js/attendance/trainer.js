let attendance = new Object();
let input = new Object();
let idEmployee = 0;
let inputData = new Object();

$(document).ready(() => {
    $("#form-view").submit((e) => {
        e.preventDefault();
        getAttendance();
        $("#modal-view").modal("hide");
    });

    $("#btnVerify").click((e) => {
        e.preventDefault();
        verifyAttendance(this.inputData);
    });
});

function verifyAttendance(input) {
    for (let i = 0; i < input.length; i++) {
        if (getCheckedValues().includes(`${i}`) === true) {
            isVerified = true;
        } else {
            isVerified = false;
        }

        attendance = {
            idAttendance: input[i].idAttendance,
            date: input[i].date,
            createdAt: input[i].createdAt,
            status: input[i].status,
            note: input[i].note,
            isVerified: isVerified,
            idEmployee: {
                idEmployee: input[i].idEmployee.idEmployee
            }
        };

        $.ajax({
            url: `attendance/${input[i].idAttendance}`,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(attendance),
            success: function (data) {
                document.getElementById("btnVerify").style.display = "none";
            },
            error: (err) => {
                errorAlert("Verified Failed");
            }
        });
    }
    successAlert("Verified Successfully");
    let allCheckboxes = document.querySelectorAll("#checkVerify");
    for (var i = 0; i < allCheckboxes.length; i++) {
        allCheckboxes[i].setAttribute('disabled', true);
    }
}

function getAttendance() {
    const d = new Date();
    const date = d.getDate();
    const month = d.getMonth();
    const year = d.getFullYear();
    const formatted = `${date} ${month} ${year}`;
    const today = `${year}-${('0' + (d.getMonth() + 1)).slice(-2)}-${('0' + date).slice(-2)}`;
    var mydate = new Date($("#date").val());
    var mymonth = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"][mydate.getMonth()];
    var strDate = mydate.getDate() + ' ' + mymonth + ' ' + mydate.getFullYear();

    attendance = {
        batch: $("#batch").val(),
        date: $("#date").val()
    };

    $.ajax({
        url: "/attendance/getAttendance",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(attendance),
        success: function (data) {
            console.log(data);
            getData(data);
            document.getElementById("btnView").style.display = "none";
            document.getElementById("btnBack").style.display = "inline";
            document.getElementById("btnVerify").style.display = "inline";
            document.getElementById("dateAttend").style.display = "inline";
            document.getElementById("dateView").textContent = strDate;
            for (let i = 0; i < data.length; i++) {
                let todayAttendance = "";
                todayAttendance = todayAttendance +
                        `
<form id="form-view-attendance">
    <div class="card card-stats mt-3">
        <div class="card-body">
            <div class="row">
                <div class="col-6 py-4">
                    <span class="h2 font-weight-bold mb-0">${data[i].idEmployee.name}</span>
                </div>
                <div class="col-5 text-right py-4">
                    <input type="checkbox" id="checkVerify" value="${i}" checked>
                </div>
            </div>
        </div>
    </div>
</form>
`;
                $("#content-attendance").append(todayAttendance);
            }
        }
    });
}

function getData(input) {
    this.inputData = input;
}

function getCheckedValues() {
    return Array.from(document.querySelectorAll('input[type="checkbox"]'))
            .filter((checkbox) => checkbox.checked)
            .map((checkbox) => checkbox.value);
}