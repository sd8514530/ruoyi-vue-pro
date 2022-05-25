import request from '@/utils/request'


// 创建装修主材类型
export function createMainMaterialType(data) {
    return request({
        url: '/decoration/main-material-type/create',
        method: 'post',
        data: data
    })
}

// 更新装修主材类型
export function updateMainMaterialType(data) {
    return request({
        url: '/decoration/main-material-type/update',
        method: 'put',
        data: data
    })
}

// 删除装修主材类型
export function deleteMainMaterialType(id) {
    return request({
        url: '/decoration/main-material-type/delete?id=' + id,
        method: 'delete'
    })
}

// 获得装修主材类型
export function getMainMaterialType(id) {
    return request({
        url: '/decoration/main-material-type/get?id=' + id,
        method: 'get'
    })
}

// 获得装修主材类型分页
export function getMainMaterialTypePage(query) {
    return request({
        url: '/decoration/main-material-type/page',
        method: 'get',
        params: query
    })
}

// 导出装修主材类型 Excel
export function exportMainMaterialTypeExcel(query) {
    return request({
        url: '/decoration/main-material-type/export-excel',
        method: 'get',
        params: query,
        responseType: 'blob'
    })
}