$(document).ready(() => {
    getImgClass();
});



function getImgClass() {
    let imageJava = `<img src="/img/class/java.jpeg" id="classImg" style="width: 200px;" class="rounded" alt="">`;
    let imageNet = `<img src="/img/class/net.jpeg" id="classImg" style="width: 200px;" class="rounded" alt="">`;

    $('#className1').append(imageJava);
    $('#className2').append(imageNet);
}


