var changepassword = new Object();
var idUser = 0;

$(document).ready(() => {
    getIdUser();
    $("#changePasswordForm").submit((e) => {
        e.preventDefault();
        formValidation(update);
    });
});

function getIdUser() {
    $.ajax({
        url: "/user",
        type: 'GET',
        success: (res) => {
            idUser = res;
        }
    });
}

function update() {
    changepassword = {
        oldPassword: $("#oldPassword").val(),
        newPassword: $("#newPassword").val(),
        repeatNewPassword: $("#repeatNewPassword").val()
    };

    $.ajax({
        url: `/myprofile/changepassword/${this.idUser}`,
        type: 'PUT',
        dataType: 'text',
        data: changepassword,
        success: (res) => {
            successAlert("Password Changed!");
            window.location.href = `http://localhost:8080/logout`;
        },
        error: (err) => {
            errorAlert("Please Check Again! ");
        }
    });
}