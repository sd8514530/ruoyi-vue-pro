package cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo.*;
import cn.iocoder.yudao.module.decoration.dal.dataobject.mainmaterialtype.MainMaterialTypeDO;
import cn.iocoder.yudao.module.decoration.convert.mainmaterialtype.MainMaterialTypeConvert;
import cn.iocoder.yudao.module.decoration.service.mainmaterialtype.MainMaterialTypeService;

@Api(tags = "管理后台 - 装修主材类型")
@RestController
@RequestMapping("/decoration/main-material-type")
@Validated
public class MainMaterialTypeController {

    @Resource
    private MainMaterialTypeService mainMaterialTypeService;

    @PostMapping("/create")
    @ApiOperation("创建装修主材类型")
    @PreAuthorize("@ss.hasPermission('decoration:main-material-type:create')")
    public CommonResult<Integer> createMainMaterialType(@Valid @RequestBody MainMaterialTypeCreateReqVO createReqVO) {
        return success(mainMaterialTypeService.createMainMaterialType(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新装修主材类型")
    @PreAuthorize("@ss.hasPermission('decoration:main-material-type:update')")
    public CommonResult<Boolean> updateMainMaterialType(@Valid @RequestBody MainMaterialTypeUpdateReqVO updateReqVO) {
        mainMaterialTypeService.updateMainMaterialType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除装修主材类型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('decoration:main-material-type:delete')")
    public CommonResult<Boolean> deleteMainMaterialType(@RequestParam("id") Integer id) {
        mainMaterialTypeService.deleteMainMaterialType(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得装修主材类型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('decoration:main-material-type:query')")
    public CommonResult<MainMaterialTypeRespVO> getMainMaterialType(@RequestParam("id") Integer id) {
        MainMaterialTypeDO mainMaterialType = mainMaterialTypeService.getMainMaterialType(id);
        return success(MainMaterialTypeConvert.INSTANCE.convert(mainMaterialType));
    }

    @GetMapping("/list")
    @ApiOperation("获得装修主材类型列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('decoration:main-material-type:query')")
    public CommonResult<List<MainMaterialTypeRespVO>> getMainMaterialTypeList(@RequestParam("ids") Collection<Integer> ids) {
        List<MainMaterialTypeDO> list = mainMaterialTypeService.getMainMaterialTypeList(ids);
        return success(MainMaterialTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得装修主材类型分页")
    @PreAuthorize("@ss.hasPermission('decoration:main-material-type:query')")
    public CommonResult<PageResult<MainMaterialTypeRespVO>> getMainMaterialTypePage(@Valid MainMaterialTypePageReqVO pageVO) {
        PageResult<MainMaterialTypeDO> pageResult = mainMaterialTypeService.getMainMaterialTypePage(pageVO);
        return success(MainMaterialTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出装修主材类型 Excel")
    @PreAuthorize("@ss.hasPermission('decoration:main-material-type:export')")
    @OperateLog(type = EXPORT)
    public void exportMainMaterialTypeExcel(@Valid MainMaterialTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MainMaterialTypeDO> list = mainMaterialTypeService.getMainMaterialTypeList(exportReqVO);
        // 导出 Excel
        List<MainMaterialTypeExcelVO> datas = MainMaterialTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "装修主材类型.xls", "数据", MainMaterialTypeExcelVO.class, datas);
    }

}
