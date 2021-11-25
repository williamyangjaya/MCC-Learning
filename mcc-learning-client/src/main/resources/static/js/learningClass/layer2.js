$(document).ready(() => {
    getCategory();
});



function getCategory(idClass, idCategory) {
    $.ajax({
        url: `/getCategory/${idClass}/${idCategory}`,
        type: 'GET',
        success: (res) => {
             console.log(res);
        }
    });
}


