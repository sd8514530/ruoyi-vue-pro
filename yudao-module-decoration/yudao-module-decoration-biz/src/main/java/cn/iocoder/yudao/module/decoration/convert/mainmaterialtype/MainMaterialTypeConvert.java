package cn.iocoder.yudao.module.decoration.convert.mainmaterialtype;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo.*;
import cn.iocoder.yudao.module.decoration.dal.dataobject.mainmaterialtype.MainMaterialTypeDO;

/**
 * 装修主材类型 Convert
 *
 * @author 朱慧明
 */
@Mapper
public interface MainMaterialTypeConvert {

    MainMaterialTypeConvert INSTANCE = Mappers.getMapper(MainMaterialTypeConvert.class);

    MainMaterialTypeDO convert(MainMaterialTypeCreateReqVO bean);

    MainMaterialTypeDO convert(MainMaterialTypeUpdateReqVO bean);

    MainMaterialTypeRespVO convert(MainMaterialTypeDO bean);

    List<MainMaterialTypeRespVO> convertList(List<MainMaterialTypeDO> list);

    PageResult<MainMaterialTypeRespVO> convertPage(PageResult<MainMaterialTypeDO> page);

    List<MainMaterialTypeExcelVO> convertList02(List<MainMaterialTypeDO> list);

}
