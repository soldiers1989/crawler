
$('#myTab').find('li').click( function() {
    if(!$(this).hasClass("active")&&$(this).index()==0){
        $('#loginType').val('INSURANCE_CARD');
        $('#idnum').find('input').attr("disabled","disabled");
        $('#card').find('input').removeAttr("disabled");
    }
    if(!$(this).hasClass("active")&&$(this).index()==1){
        $('#loginType').val('IDNUM');
        $('#card').find('input').attr("disabled","disabled");
        $('#idnum').find('input').removeAttr("disabled");
    }
});

/**
 * 点击下一步
 */
$('#nextBtn').click( function() {
    nextLogin(); //触发爬虫
});

//表单验证
function verifyForm1() {
    var username1 = $('#username1').val().trim();//姓名
    var password1 = $('#password1').val().trim();//密码
    if ((username1 == null || username1 == "") ||
        (password1 == null || password1 == "")) {
        $("#nextBtn").addClass("disabled");
    }else{
        $("#nextBtn").removeClass("disabled");
    }
}

function verifyForm2() {
    var username2 = $('#username2').val().trim();//姓名
    var password2 = $('#password2').val().trim();//密码
    if ((username2 == null || username2 == "") ||
        (password1 == null || password2 == "")) {
        $("#nextBtn").addClass("disabled");
    }else{
        $("#nextBtn").removeClass("disabled");
    }
}

/**
 * 表单改变事件
 */
$('input').bind('input propertychange', function() {
    if($('#loginType').val()=='INSURANCE_CARD'){
        verifyForm1();
    }
    if($('#loginType').val()=='IDNUM'){
        verifyForm2();
    }
});

$(function(){
    verifyForm1();
    $('#idnum').find('input').attr("disabled","disabled");
});