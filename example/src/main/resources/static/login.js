function login() {
    let username = $("#username").val();
    let passwd = $("#passwd").val();

    if(null == username || '' == username || username.length < 1) {
        alert("username can not ne null");
        return;
    }

    if(null == passwd || '' == passwd || passwd.length < 1) {
        alert("username can not ne null");
        return;
    }

    let url = '/api/login';
    $.post(url, {username: username, password: passwd, }, function (data) {
        if(data.result == 'success') {
            alert('login success!');
        } else {
            alert('login failed!');
        }
    })
}