package cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 装修主材类型 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class MainMaterialTypeBaseVO {

    @ApiModelProperty(value = "主材名称", required = true)
    @NotNull(message = "主材名称不能为空")
    private String typeName;

    @ApiModelProperty(value = "主材类型(字典值)", required = true)
    @NotNull(message = "主材类型(字典值)不能为空")
    private String type;

    @ApiModelProperty(value = "单位", required = true)
    @NotNull(message = "单位不能为空")
    private String unit;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reason1;

    @ApiModelProperty(value = "备用字段2")
    private String reason2;

    @ApiModelProperty(value = "数据状态（0正常 1停用）", required = true)
    @NotNull(message = "数据状态（0正常 1停用）不能为空")
    private Integer status;

}
