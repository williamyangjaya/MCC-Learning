let idUser = 0;
var status = "";

$(document).ready(() => {
    getIdUser();
});

function getMyProfile() {
    $.ajax({
        url: `/myprofile/${idUser}`,
        datatype: "json",
        type: 'GET',
        success: (res) => {
            let element = "";
            $('#profile-content').html('');
            element = element +
                    `<h1>Profile</h1>
                <div class="row mt-5">
                    <div class="col-auto">
                        <span class="card-profile-image">
                            <img src="../assets/img/theme/team-4.jpg" class="rounded-circle">
                        </span>
                    </div>
                    <div class="col-auto mt-3">
                        <h2 style="text-transform: capitalize">${res.name}</h2>
                        <h3>${res.email}</h3>
                        <h3 style="text-transform: capitalize">${res.position}</h3>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <div class="form-group">
                            <label class="form-control-label" for="batch">Batch MCC</label>
                            <input value="${res.batch}" type="text" id="batch" class="form-control" disabled="disabled" style="width: 25%; text-transform: capitalize">
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" for="kelas">MCC Class</label>
                            <input value="${res.kelas}" type="text" id="kelas" class="form-control" disabled="disabled" style="width: 25%; text-transform: capitalize">
                        </div>
                        <div class="form-group">
                            <label class="form-control-label" for="status">Status</label>
                            <input value="${this.status}" type="text" id="status" class="form-control" disabled="disabled" style="width: 25%; text-transform: capitalize">
                        </div>
                    </div>
                </div>`;
            $("#profile-content").append(element);
        }
    });
}

function getIdUser() {
    $.ajax({
        url: "/user",
        type: 'GET',
        success: (res) => {
            idUser = res;
            getHistory();
            getMyProfile();
        },
        error: (err) => {
        }
    });
}

function getHistory(){
    $.ajax({
        url: `/history/myHistory/${idUser}`,
        type: 'GET',
        success: (res) => {
            console.log(res);
            if(res.length===0) {
                this.status = "TRAINEE";
            } else if(res[res.length-1].status === "Project telah selesai dikerjakan"){
                this.status = "LULUS";
            } else{
                this.status = "TRAINEE";
            }
        },
        error: (err) => {
        }
    });
}