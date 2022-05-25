package cn.iocoder.yudao.module.decoration.service.mainmaterialtype;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo.*;
import cn.iocoder.yudao.module.decoration.dal.dataobject.mainmaterialtype.MainMaterialTypeDO;
import cn.iocoder.yudao.module.decoration.dal.mysql.mainmaterialtype.MainMaterialTypeMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.decoration.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link MainMaterialTypeServiceImpl} 的单元测试类
*
* @author 朱慧明
*/
@Import(MainMaterialTypeServiceImpl.class)
public class MainMaterialTypeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private MainMaterialTypeServiceImpl mainMaterialTypeService;

    @Resource
    private MainMaterialTypeMapper mainMaterialTypeMapper;

    @Test
    public void testCreateMainMaterialType_success() {
        // 准备参数
        MainMaterialTypeCreateReqVO reqVO = randomPojo(MainMaterialTypeCreateReqVO.class);

        // 调用
        Integer mainMaterialTypeId = mainMaterialTypeService.createMainMaterialType(reqVO);
        // 断言
        assertNotNull(mainMaterialTypeId);
        // 校验记录的属性是否正确
        MainMaterialTypeDO mainMaterialType = mainMaterialTypeMapper.selectById(mainMaterialTypeId);
        assertPojoEquals(reqVO, mainMaterialType);
    }

    @Test
    public void testUpdateMainMaterialType_success() {
        // mock 数据
        MainMaterialTypeDO dbMainMaterialType = randomPojo(MainMaterialTypeDO.class);
        mainMaterialTypeMapper.insert(dbMainMaterialType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        MainMaterialTypeUpdateReqVO reqVO = randomPojo(MainMaterialTypeUpdateReqVO.class, o -> {
            o.setId(dbMainMaterialType.getId()); // 设置更新的 ID
        });

        // 调用
        mainMaterialTypeService.updateMainMaterialType(reqVO);
        // 校验是否更新正确
        MainMaterialTypeDO mainMaterialType = mainMaterialTypeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, mainMaterialType);
    }

    @Test
    public void testUpdateMainMaterialType_notExists() {
        // 准备参数
        MainMaterialTypeUpdateReqVO reqVO = randomPojo(MainMaterialTypeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> mainMaterialTypeService.updateMainMaterialType(reqVO), MAIN_MATERIAL_TYPE_NOT_EXISTS);
    }

    @Test
    public void testDeleteMainMaterialType_success() {
        // mock 数据
        MainMaterialTypeDO dbMainMaterialType = randomPojo(MainMaterialTypeDO.class);
        mainMaterialTypeMapper.insert(dbMainMaterialType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Integer id = dbMainMaterialType.getId();

        // 调用
        mainMaterialTypeService.deleteMainMaterialType(id);
       // 校验数据不存在了
       assertNull(mainMaterialTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteMainMaterialType_notExists() {
        // 准备参数
        Integer id =1;// randomIntegerId();

        // 调用, 并断言异常
        assertServiceException(() -> mainMaterialTypeService.deleteMainMaterialType(id), MAIN_MATERIAL_TYPE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMainMaterialTypePage() {
       // mock 数据
       MainMaterialTypeDO dbMainMaterialType = randomPojo(MainMaterialTypeDO.class, o -> { // 等会查询到
           o.setTypeName(null);
           o.setType(null);
           o.setUnit(null);
           o.setRemark(null);
           o.setReason1(null);
           o.setReason2(null);
           o.setCreateTime(null);
           o.setStatus(null);
       });
       mainMaterialTypeMapper.insert(dbMainMaterialType);
       // 测试 typeName 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setTypeName(null)));
       // 测试 type 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setType(null)));
       // 测试 unit 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setUnit(null)));
       // 测试 remark 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setRemark(null)));
       // 测试 reason1 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setReason1(null)));
       // 测试 reason2 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setReason2(null)));
       // 测试 createTime 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setCreateTime(null)));
       // 测试 status 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setStatus(null)));
       // 准备参数
       MainMaterialTypePageReqVO reqVO = new MainMaterialTypePageReqVO();
       reqVO.setTypeName(null);
       reqVO.setType(null);
       reqVO.setUnit(null);
       reqVO.setRemark(null);
       reqVO.setReason1(null);
       reqVO.setReason2(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);
       reqVO.setStatus(null);

       // 调用
       PageResult<MainMaterialTypeDO> pageResult = mainMaterialTypeService.getMainMaterialTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbMainMaterialType, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetMainMaterialTypeList() {
       // mock 数据
       MainMaterialTypeDO dbMainMaterialType = randomPojo(MainMaterialTypeDO.class, o -> { // 等会查询到
           o.setTypeName(null);
           o.setType(null);
           o.setUnit(null);
           o.setRemark(null);
           o.setReason1(null);
           o.setReason2(null);
           o.setCreateTime(null);
           o.setStatus(null);
       });
       mainMaterialTypeMapper.insert(dbMainMaterialType);
       // 测试 typeName 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setTypeName(null)));
       // 测试 type 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setType(null)));
       // 测试 unit 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setUnit(null)));
       // 测试 remark 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setRemark(null)));
       // 测试 reason1 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setReason1(null)));
       // 测试 reason2 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setReason2(null)));
       // 测试 createTime 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setCreateTime(null)));
       // 测试 status 不匹配
       mainMaterialTypeMapper.insert(cloneIgnoreId(dbMainMaterialType, o -> o.setStatus(null)));
       // 准备参数
       MainMaterialTypeExportReqVO reqVO = new MainMaterialTypeExportReqVO();
       reqVO.setTypeName(null);
       reqVO.setType(null);
       reqVO.setUnit(null);
       reqVO.setRemark(null);
       reqVO.setReason1(null);
       reqVO.setReason2(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);
       reqVO.setStatus(null);

       // 调用
       List<MainMaterialTypeDO> list = mainMaterialTypeService.getMainMaterialTypeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbMainMaterialType, list.get(0));
    }

}
