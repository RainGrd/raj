// 查询列表接口
const getDishPage = (params) => {
    return $axios({
        url: '/dish/queryDishForPage.do',
        method: 'get',
        params
    })
}

// 删除接口
const deleteDish = (ids) => {
    return $axios({
        url: '/dish/deleteDishById.do',
        method: 'delete',
        params: {ids}
    })
}

// 修改接口
const editDish = (params) => {
    return $axios({
        url: '/dish/modifyDishById.do',
        method: 'put',
        data: {...params}
    })
}

// 新增接口
const addDish = (params) => {
    return $axios({
        url: '/dish/saveDish.do',
        method: 'post',
        data: {...params}
    })
}

// 查询详情
const queryDishById = (id) => {
    return $axios({
        url: `/dish/getDishById.do/${id}`,
        method: 'get'
    })
}

// 获取菜品分类列表
const getCategoryList = (params) => {
    return $axios({
        url: '/category/queryCategoryListByCategory.do',
        method: 'get',
        params
    })
}

// 查菜品列表的接口
const queryDishList = (params) => {
    return $axios({
        url: '/dish/queryDishListByCategoryId.do',
        method: 'get',
        params
    })
}

// 文件down预览
const commonDownload = (params) => {
    return $axios({
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        url: '/common/download.do',
        method: 'get',
        params
    })
}

// 起售停售---批量起售停售接口
const dishStatusByStatus = (params) => {
    return $axios({
        url: `/dish/modifyDishByStatus.do/${params.status}`,
        method: 'post',
        params: {ids: params.id}
    })
}