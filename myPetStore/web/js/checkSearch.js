function checkSearch(){
    if(document.getElementsByName("keyword")[0].value.match(/^[ ]*$/)){
        alert('输入不能为空');
        document.getElementsByName("keyword")[0].value = "";
        document.getElementsByName("keyword")[0].focus();
        return false;
    }
}