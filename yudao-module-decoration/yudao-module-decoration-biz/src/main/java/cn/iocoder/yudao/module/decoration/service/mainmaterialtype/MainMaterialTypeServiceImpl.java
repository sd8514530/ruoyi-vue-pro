package cn.iocoder.yudao.module.decoration.service.mainmaterialtype;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo.*;
import cn.iocoder.yudao.module.decoration.dal.dataobject.mainmaterialtype.MainMaterialTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.decoration.convert.mainmaterialtype.MainMaterialTypeConvert;
import cn.iocoder.yudao.module.decoration.dal.mysql.mainmaterialtype.MainMaterialTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.decoration.enums.ErrorCodeConstants.*;

/**
 * 装修主材类型 Service 实现类
 *
 * @author 朱慧明
 */
@Service
@Validated
public class MainMaterialTypeServiceImpl implements MainMaterialTypeService {

    @Resource
    private MainMaterialTypeMapper mainMaterialTypeMapper;

    @Override
    public Integer createMainMaterialType(MainMaterialTypeCreateReqVO createReqVO) {
        // 插入
        MainMaterialTypeDO mainMaterialType = MainMaterialTypeConvert.INSTANCE.convert(createReqVO);
        mainMaterialTypeMapper.insert(mainMaterialType);
        // 返回
        return mainMaterialType.getId();
    }

    @Override
    public void updateMainMaterialType(MainMaterialTypeUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateMainMaterialTypeExists(updateReqVO.getId());
        // 更新
        MainMaterialTypeDO updateObj = MainMaterialTypeConvert.INSTANCE.convert(updateReqVO);
        mainMaterialTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMainMaterialType(Integer id) {
        // 校验存在
        this.validateMainMaterialTypeExists(id);
        // 删除
        mainMaterialTypeMapper.deleteById(id);
    }

    private void validateMainMaterialTypeExists(Integer id) {
        if (mainMaterialTypeMapper.selectById(id) == null) {
            throw exception(MAIN_MATERIAL_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public MainMaterialTypeDO getMainMaterialType(Integer id) {
        return mainMaterialTypeMapper.selectById(id);
    }

    @Override
    public List<MainMaterialTypeDO> getMainMaterialTypeList(Collection<Integer> ids) {
        return mainMaterialTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MainMaterialTypeDO> getMainMaterialTypePage(MainMaterialTypePageReqVO pageReqVO) {
        return mainMaterialTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MainMaterialTypeDO> getMainMaterialTypeList(MainMaterialTypeExportReqVO exportReqVO) {
        return mainMaterialTypeMapper.selectList(exportReqVO);
    }

}
