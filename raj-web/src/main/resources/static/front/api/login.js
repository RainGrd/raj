function sendMsg(data) {
    return $axios({
        "url": "/user/sendCode.do",
        "method": 'post',
        data
    });
}

function loginApi(data) {
    return $axios({
        'url': '/user/login.do',
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

  