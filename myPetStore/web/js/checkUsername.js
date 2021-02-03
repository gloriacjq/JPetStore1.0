$(function () {
    $("#username").on("blur", function () {
        $.ajax({
            type    : "GET",
            url     : "usernameExist?username="+this.value,
            success : function (data){
                if(data.msg === "Exist"){
                    $("#usernameTips").attr("class", "errorTips").text("Invalid");
                }else if(data.msg === "Not Exist"){
                    $("#usernameTips").attr("class", "okTips").text("Available");
                }
            }
        });
    })
})
