// 查询列表页接口
const getOrderDetailPage = (params) => {
  return $axios({
    url: '/order/queryOrderPage.do',
    method: 'get',
    params
  })
}

// 查看接口
const queryOrderDetailById = (id) => {
  return $axios({
    url: `/orderDetail/${id}`,
    method: 'get'
  })
}

// 取消，派送，完成接口
const editOrderDetail = (params) => {
  return $axios({
    url: '/order/editOrderDetail.do',
    method: 'put',
    data: { ...params }
  })
}
