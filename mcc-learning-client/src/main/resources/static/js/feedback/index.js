var updatefeedback = new Object();
var category = null;

$(document).ready(() => {
    
});

function getCategory(cat) {
    $.ajax({
        url:`/feedback/${cat}`,
        type:"GET",
        success: (res) => {
            this.category = res.category;
        }
    });
}

function update() {
    updatefeedback = {
        link : $("#linkFeedback").val()
    };

    $.ajax({
        url: `/feedback/update/${category}`,
        type: 'PUT',
        data: updatefeedback,
        success: (res) => {
            successAlert("Update Success");
        },
        error: (err) => {
            errorAlert("Update Failed");
        }
    });
}
