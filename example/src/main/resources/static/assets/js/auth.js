$(function () {
    let channel = 'account';

    $("#check_channel").click(function () {
        if('account' == channel) {
            $("#form_1").css("display","none");
            $("#form_2").css("display","block");
            channel = 'mobile';
            $("#check_channel").html("Use Password");
            $("#check_channel").css("width", "40%");
            $("#account_error_msg").html("");
            $("#mobile_error_msg").html("");
        } else if('mobile' == channel) {
            $("#form_1").css("display","block");
            $("#form_2").css("display","none");
            channel = 'account';
            $("#check_channel").html("Use Tel");
            $("#check_channel").css("width", "30%");
            $("#account_error_msg").html("");
            $("#mobile_error_msg").html("");
        }
    })
})

function login_mobile() {
    let mobile = $("#mobile").val();
    let code = $("#validateCode_sms").val();

    if(null == mobile || '' == mobile || mobile.length < 1) {
        alert("mobile can not ne null");
        return;
    }

    let url = '/auth/mobile';
    $.post(url, {mobile: mobile, validate_code: code}, function (data) {
        console.info(data);
        if(data.success == true) {
            window.location.href = "/page/index";
        } else {
            let msg = data.message;
            $("#mobile_error_msg").html("<span>" + msg + "</span>");
        }
    }).error(function (e) {
        let exp = eval("(" +e.responseText+ ")");
        $("#mobile_error_msg").html("<span>" + exp.message + "</span>");
    });
}

function getCode() {
    let url = '/captcha/sms?mobile=' + $("#mobile").val();
    $.get(url, function (data) {
        alert("验证码已发送");
    });
}

function login_account() {
    let username = $("#username").val();
    let passwd = $("#password").val();
    let code = $("#validateCode_image").val();

    if(null == username || '' == username || username.length < 1) {
        alert("username can not ne null");
        return;
    }

    if(null == passwd || '' == passwd || passwd.length < 1) {
        alert("username can not ne null");
        return;
    }

    let url = '/auth/account';
    $.post(url, {username: username, password: passwd, validate_code: code}, function (data) {
        console.info(data);
        if(data.success == true) {
            window.location.href = "/page/index";
        } else {
            let msg = data.message;
            $("#account_error_msg").html("<span>" + msg + "</span>");
        }
    }).error(function (e) {
        let exp = eval("(" +e.responseText+ ")");
        $("#account_error_msg").html("<span>" + exp.message + "</span>");
    });
}
