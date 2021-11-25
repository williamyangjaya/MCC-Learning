let attendance = new Object();
let idEmployee = 0;
let holidayID = null;

const days = [
    'Sunday',
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday'
];
const months = [
    'January',
    'February',
    'March',
    'April',
    'May',
    'June',
    'July',
    'August',
    'September',
    'October',
    'November',
    'December'
];
$(document).ready(() => {
    getMyAttendance();
    $("#form-download").submit((e) => {
        e.preventDefault();
        download();
    });
});

function getHoliday() {
    $.ajax({
        url: "https://raw.githubusercontent.com/guangrei/Json-Indonesia-holidays/master/calendar.json",
        type: 'GET',
        crossDomain: true,
        success: (res) => {
            holidayID = res;
        },
        error: (err) => {

        }
    });
}

function download() {
    attendance = {
        idEmployee: idEmployee,
        startDate: $("#from-date").val(),
        endDate: $("#to-date").val()
    };

    $.ajax({
        url: "/attendance/downloadExcelFile",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(attendance),
        success: (res) => {
            if (res === true) {
                successAlert("Download Success");
                $("#modal-download").modal("hide");
            } else {
                errorAlert("Download Failed");
            }
        },
        error: (err) => {
            errorAlert("Download Failed");
        }
    });
}

function getAttendanceByEmpId() {
    $.ajax({
        url: `/attendance/get/${idEmployee}`,
        type: 'GET',
        async: true,
        dataType: 'json',
        success: function (data) {
            const d = new Date();
            const dayName = days[d.getDay()]; // Thursday
            const date = d.getDate();
            const monthName = months[d.getMonth()];
            const year = d.getFullYear();
            const formatted = `${dayName}, ${date} ${monthName} ${year}`;
            const dHistory = new Date(data[data.length - 1].date);
            const dateHistory = dHistory.getDate();
            const monthNameHistory = months[dHistory.getMonth()];
            const yearHistory = dHistory.getFullYear();
            const check = `${year}${('0' + (d.getMonth() + 1)).slice(-2)}${('0' + date).slice(-2)}`;
            let myObj = JSON.parse(holidayID);
            let firstKey = Object.keys(myObj);
            let todayAttendance = "";
            todayAttendance = todayAttendance +
                    `
<div class="card card-stats mt-3">
    <div class="card-body">
        <div class="row">
            <div class="col-7">
                <h5 class="card-title text-uppercase text-muted mb-0">${dayName}</h5>
                <span class="h2 font-weight-bold mb-0">${date} ${monthName} ${year}</span>
                <p class="mt-3 mb-0 text-sm">
                    <div id="status" class="control">
                        <label class="radio">
                          <input type="radio" name="status" value="present" checked>Present
                        </label>
                        <label class="radio">
                          <input type="radio" name="status" value="absent">Absent
                        </label>
                    </div>
                    <span class="text-success mr-2"><i class="fa fa-book mr-1"></i>Note:</span>
                    <input type="text" id="note" name="note">
                </p>
            </div>
            <div class="col-5 text-right py-4">
                <button onclick="onMyPresent()" class="btn btn-primary" style="border-radius: .8rem;">Present Now</button>
            </div>
        </div>
    </div>
</div>
`;
            const dayWeekend = days[d.getDay()]; // Thursday
            
            if (!(date === dateHistory && monthName === monthNameHistory && year === yearHistory
                    || dayWeekend === 'Saturday' || dayWeekend === 'Sunday'))
            {
                $("#content-attendance").append(todayAttendance);
            }

            let element = "";
            for (let i = data.length - 1; i >= 0; i--) {
                const d = new Date(data[i].date);
                const dayName = days[d.getDay()]; // Thursday
                const date = d.getDate();
                const monthName = months[d.getMonth()];
                const year = d.getFullYear();
                const formatted = `${dayName}, ${date} ${monthName} ${year}`;
                let note = data[i].note;
                if (note === null) {
                    note = "";
                }
                var status = data[i].status;
                var verified = data[i].isVerified;
                var createdAt = data[i].createdAt.substr(11,5);
                var lateTime = diff("08:00",createdAt);
                lateTime = lateTime.split(":");
                if (verified === true) {
                    if (status === "present") {
                        element = element +
                                `
<div class="card card-stats mt-3">
    <div class="card-body">
        <div class="row">
            <div class="col-7">
                <h5 class="card-title text-uppercase text-muted mb-0">${dayName}</h5>
                    <span class="h2 font-weight-bold mb-0">${date} ${monthName} ${year}</span>
                    <p class="font-weight-bold mb-0 text-capitalize text-primary">
                        <i class="fa fa-check-circle mr-1 text-info"></i>${status}
                    </p>
                    <p class="mt-3 mb-0 text-sm">
                        <span class="text-success mr-2"><i class="fa fa-book mr-1"></i>Note:</span>
                        <span class="text-nowrap">${note}</span>
                    </p>
            </div>
        </div>
    </div>    
</div>
`;
                    } else {
                        element = element +
                                `
<div class="card card-stats mt-3">
    <div class="card-body">
        <div class="row">
            <div class="col-7">
                <h5 class="card-title text-uppercase text-muted mb-0">${dayName}</h5>
                    <span class="h2 font-weight-bold mb-0">${date} ${monthName} ${year}</span>
                    <p class="font-weight-bold mb-0 text-capitalize text-danger">
                        <i class="fa fa-check-circle mr-1 text-info"></i>${status} ${lateTime[0]} Hours ${lateTime[1]} Minutes
                    </p>
                    <p class="mt-3 mb-0 text-sm">
                        <span class="text-success mr-2"><i class="fa fa-book mr-1"></i>Note:</span>
                        <span class="text-nowrap">${note}</span>
                    </p>
            </div>
        </div>
    </div>    
</div>
`;
                    }
                } else {
                    if (status === "present") {


                        element = element +
                                `
<div class="card card-stats mt-3">
    <div class="card-body">
        <div class="row">
            <div class="col-7">
                <h5 class="card-title text-uppercase text-muted mb-0">${dayName}</h5>
                    <span class="h2 font-weight-bold mb-0">${date} ${monthName} ${year}</span>
                    <p class="font-weight-bold mb-0 text-capitalize text-primary">
                        ${status}
                    </p>
                    <p class="mt-3 mb-0 text-sm">
                        <span class="text-success mr-2"><i class="fa fa-book mr-1"></i>Note:</span>
                        <span class="text-nowrap">${note}</span>
                    </p>
            </div>
        </div>
    </div>    
</div>
`;
                    } else {
                        element = element +
                                `
<div class="card card-stats mt-3">
    <div class="card-body">
        <div class="row">
            <div class="col-7">
                <h5 class="card-title text-uppercase text-muted mb-0">${dayName}</h5>
                    <span class="h2 font-weight-bold mb-0">${date} ${monthName} ${year}</span>
                    <p class="font-weight-bold mb-0 text-capitalize text-danger">
                        ${status} ${lateTime[0]} Hours ${lateTime[1]} Minutes
                    </p>
                    <p class="mt-3 mb-0 text-sm">
                        <span class="text-success mr-2"><i class="fa fa-book mr-1"></i>Note:</span>
                        <span class="text-nowrap">${note}</span>
                    </p>
            </div>
        </div>
    </div>    
</div>
`;
                    }
                }
            }
            $("#content-attendance").append(element);
        }
    });
}

function getMyAttendance() {
    $.ajax({
        url: "/user",
        type: 'GET',
        success: (res) => {
            idEmployee = res;
            getHoliday();
            getAttendanceByEmpId();
        },
        error: (err) => {
        }
    });
}

function onMyPresent() {
    $.ajax({
        url: "/user",
        type: 'GET',
        success: (res) => {
            onPresent();
        },
        error: (err) => {
        }
    });
}

function onPresent() {
    const d = new Date();
    const dayName = days[d.getDay()]; // Thursday
    const date = d.getDate();
    const monthName = months[d.getMonth()];
    const year = d.getFullYear();
    const formatted = `${dayName}, ${date} ${monthName} ${year}`;
    var status = document.getElementsByName('status');
    for (i = 0; i < status.length; i++) {
        if (status[i].checked)
            status = status[i].value;
    }
    if (status === 'present' && d.getHours() >= 8 && d.getMinutes() >= 0 && d.getSeconds() >= 0) {
        status = "late";
    }
    attendance = {
        date: `${year}-${('0' + (d.getMonth() + 1)).slice(-2)}-${('0' + date).slice(-2)}`,
        createdAt: `${year}-${('0' + (d.getMonth() + 1)).slice(-2)}-${('0' + date).slice(-2)} ${('0' + d.getHours()).slice(-2)}:${('0' + d.getMinutes()).slice(-2)}:${('0' + d.getSeconds()).slice(-2)}`,
        status: status,
        note: $("#note").val(),
        isVerified: false,
        idEmployee: {
            idEmployee: idEmployee
        }
    };

    $.ajax({
        url: "/attendance",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(attendance),
        success: (res) => {
            successAlert("Present Success");
            setInterval(function(){ window.location.reload(); }, 1000);
        },
        error: (err) => {
            errorAlert("Present Failed");
        }
    });
}

function diff(start, end) {
    start = start.split(":");
    end = end.split(":");
    var startDate = new Date(0, 0, 0, start[0], start[1], 0);
    var endDate = new Date(0, 0, 0, end[0], end[1], 0);
    var diff = endDate.getTime() - startDate.getTime();
    var hours = Math.floor(diff / 1000 / 60 / 60);
    diff -= hours * 1000 * 60 * 60;
    var minutes = Math.floor(diff / 1000 / 60);
    
    return (hours < 9 ? "0" : "") + hours + ":" + (minutes < 9 ? "0" : "") + minutes;
}