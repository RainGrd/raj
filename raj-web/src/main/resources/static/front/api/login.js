function sendMsg(data) {
    return $axios({
        "url": "/front/login/sendCode.do",
        "method": 'post',
        data
    });
}

function loginApi(data) {
    return $axios({
        'url': '/front/login/login.do',
        'method': 'post',
        data
    })
}

function loginOutApi() {
    return $axios({
        'url': '/user/loginOut',
        'method': 'post',
    })
}

  