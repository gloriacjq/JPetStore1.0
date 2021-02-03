$(function() {
    $(".cartItemQuantity").focus(function () {
        $(this).attr("data-oval", $(this).val()); //将当前值存入自定义属性
    }).on("blur", function () {
        var oldVal = ($(this).attr("data-oval")); //获取原值
        var newVal = ($(this).val()); //获取当前值
        if (oldVal != newVal) {
            $("#cartForm").submit();
        }
    })
})

$(function() {
    var t;
    $(".add").click(function () {
        t = $(this).parent().prev().find("input");
        t.val(parseInt(t.val()) + 1);
        $("#cartForm").submit();
    });
    $(".min").click(function () {
        t = $(this).parent().next().find("input");
        if(t.val() > 1) {
            t.val(parseInt(t.val()) - 1);
            $("#cartForm").submit();
        }
    })
})

