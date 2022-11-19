//查询员工
function getMemberList(params) {
    return $axios({
        url: '/backend/member/queryEmployeeForPage.do',
        method: 'get',
        params
    })
}

// 修改---启用禁用接口
function enableOrDisableEmployee(params) {
    return $axios({
        url: '/backend/member/updateEmployee.do',
        method: 'put',
        data: {...params}
    })
}

// 新增---添加员工
function addEmployee(params) {
    return $axios({
        url: '/backend/member/insertEmployee.do',
        method: 'post',
        data: {...params}
    })
}

// 修改---添加员工
function editEmployee(params) {
    return $axios({
        url: '/backend/member/updateEmployee.do',
        method: 'put',
        data: {...params}
    })
}

// 修改页面反查详情接口
function queryEmployeeById(id) {
    return $axios({
        url: `/backend/member/selectById.do/${id}`,
        method: 'get'
    })
}