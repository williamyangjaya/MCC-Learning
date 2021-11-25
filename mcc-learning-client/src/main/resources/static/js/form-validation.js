function formValidation(action) {
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function (form) {
        if (form.checkValidity()) {
            action();
        }
        form.classList.add('was-validated');
    });
}