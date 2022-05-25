package cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 装修主材类型 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MainMaterialTypeRespVO extends MainMaterialTypeBaseVO {

    @ApiModelProperty(value = "主键ID", required = true)
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
