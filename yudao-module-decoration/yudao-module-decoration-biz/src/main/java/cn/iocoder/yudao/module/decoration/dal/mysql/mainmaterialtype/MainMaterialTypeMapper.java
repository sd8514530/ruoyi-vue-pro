package cn.iocoder.yudao.module.decoration.dal.mysql.mainmaterialtype;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.decoration.dal.dataobject.mainmaterialtype.MainMaterialTypeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo.*;

/**
 * 装修主材类型 Mapper
 *
 * @author 朱慧明
 */
@Mapper
public interface MainMaterialTypeMapper extends BaseMapperX<MainMaterialTypeDO> {

    default PageResult<MainMaterialTypeDO> selectPage(MainMaterialTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MainMaterialTypeDO>()
                .likeIfPresent(MainMaterialTypeDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(MainMaterialTypeDO::getType, reqVO.getType())
                .eqIfPresent(MainMaterialTypeDO::getUnit, reqVO.getUnit())
                .eqIfPresent(MainMaterialTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MainMaterialTypeDO::getReason1, reqVO.getReason1())
                .eqIfPresent(MainMaterialTypeDO::getReason2, reqVO.getReason2())
                .betweenIfPresent(MainMaterialTypeDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent(MainMaterialTypeDO::getStatus, reqVO.getStatus())
                .orderByDesc(MainMaterialTypeDO::getId));
    }

    default List<MainMaterialTypeDO> selectList(MainMaterialTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MainMaterialTypeDO>()
                .likeIfPresent(MainMaterialTypeDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(MainMaterialTypeDO::getType, reqVO.getType())
                .eqIfPresent(MainMaterialTypeDO::getUnit, reqVO.getUnit())
                .eqIfPresent(MainMaterialTypeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MainMaterialTypeDO::getReason1, reqVO.getReason1())
                .eqIfPresent(MainMaterialTypeDO::getReason2, reqVO.getReason2())
                .betweenIfPresent(MainMaterialTypeDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent(MainMaterialTypeDO::getStatus, reqVO.getStatus())
                .orderByDesc(MainMaterialTypeDO::getId));
    }

}
