package cn.iocoder.yudao.module.decoration.controller.admin.mainmaterialtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 装修主材类型更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MainMaterialTypeUpdateReqVO extends MainMaterialTypeBaseVO {

    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键ID不能为空")
    private Integer id;

}
