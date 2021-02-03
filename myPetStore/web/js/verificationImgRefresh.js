$(function () {
    $("#verificationImg").on("click", reloadImg);
    $("#verificationTips").on("click", reloadImg);
})

function reloadImg() {
    var verify = document.getElementById("verificationImg");
    verify.setAttribute("src", "/verificationCode?" + Math.random());
}

