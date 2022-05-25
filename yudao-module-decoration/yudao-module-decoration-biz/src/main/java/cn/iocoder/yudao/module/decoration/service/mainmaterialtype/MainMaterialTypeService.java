package cn.iocoder.yudao.module.decoration.service.mainmaterialtype;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo.*;
import cn.iocoder.yudao.module.decoration.dal.dataobject.mainmaterialtype.MainMaterialTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 装修主材类型 Service 接口
 *
 * @author 朱慧明
 */
public interface MainMaterialTypeService {

    /**
     * 创建装修主材类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createMainMaterialType(@Valid MainMaterialTypeCreateReqVO createReqVO);

    /**
     * 更新装修主材类型
     *
     * @param updateReqVO 更新信息
     */
    void updateMainMaterialType(@Valid MainMaterialTypeUpdateReqVO updateReqVO);

    /**
     * 删除装修主材类型
     *
     * @param id 编号
     */
    void deleteMainMaterialType(Integer id);

    /**
     * 获得装修主材类型
     *
     * @param id 编号
     * @return 装修主材类型
     */
    MainMaterialTypeDO getMainMaterialType(Integer id);

    /**
     * 获得装修主材类型列表
     *
     * @param ids 编号
     * @return 装修主材类型列表
     */
    List<MainMaterialTypeDO> getMainMaterialTypeList(Collection<Integer> ids);

    /**
     * 获得装修主材类型分页
     *
     * @param pageReqVO 分页查询
     * @return 装修主材类型分页
     */
    PageResult<MainMaterialTypeDO> getMainMaterialTypePage(MainMaterialTypePageReqVO pageReqVO);

    /**
     * 获得装修主材类型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 装修主材类型列表
     */
    List<MainMaterialTypeDO> getMainMaterialTypeList(MainMaterialTypeExportReqVO exportReqVO);

}
