package cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 装修主材类型 Excel 导出 Request VO", description = "参数和 MainMaterialTypePageReqVO 是一致的")
@Data
public class MainMaterialTypeExportReqVO {

    @ApiModelProperty(value = "主材名称")
    private String typeName;

    @ApiModelProperty(value = "主材类型(字典值)")
    private String type;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    private String reason1;

    @ApiModelProperty(value = "备用字段2")
    private String reason2;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "数据状态（0正常 1停用）")
    private Integer status;

}
