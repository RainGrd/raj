function loginApi(data) {
    return $axios({
        'url': '/backend/login/login.do',
        'method': 'post',
        data
    })
}

// /*覆盖*/
// function loginApi(data) {
//     return $axios({
//         'url': '/employee/login.do/' + data.username + '/' + data.password,
//         'method': 'post',
//     })
// }

function logoutApi() {
    return $axios({
        'url': '/backend/login/loginOut.do',
        'method': 'post',
    })
}
