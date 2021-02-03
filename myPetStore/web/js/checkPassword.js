$(function () {
    $("#rptSignUpPwd").on("blur", function () {
        if($("#signUpPwd").val() !== $("#rptSignUpPwd").val()){
            $("#passwordTips").attr("class", "errorTips").text("Wrong");
        }else if($("#signUpPwd").val() == $("#rptSignUpPwd").val()){
            $("#passwordTips").attr("class", "okTips").text("Ok");
        }
    });
})
