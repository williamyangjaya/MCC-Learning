let idUser = 0;

$(document).ready(() => {
    getIdUser();
});

function getMyProfile() {
    $.ajax({
        url: `/myprofile/trainer/${idUser}`,
        datatype: "json",
        type: 'GET',
        success: (res) => {
            console.log(res);
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
                `;
            $("#profile-content").append(element);
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
            getMyProfile();
        },
        error: (err) => {
        }
    });
}