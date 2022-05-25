package cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 装修主材类型 Excel VO
 *
 * @author 朱慧明
 */
@Data
public class MainMaterialTypeExcelVO {

    @ExcelProperty("主键ID")
    private Integer id;

    @ExcelProperty("主材名称")
    private String typeName;

    @ExcelProperty("主材类型(字典值)")
    private String type;

    @ExcelProperty("单位")
    private String unit;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("备用字段1")
    private String reason1;

    @ExcelProperty("备用字段2")
    private String reason2;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("数据状态（0正常 1停用）")
    private Integer status;

}
