package cn.iocoder.yudao.module.decoration.dal.dataobject.mainmaterialtype;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 装修主材类型 DO
 *
 * @author 朱慧明
 */
@TableName("decoration_main_material_type")
@KeySequence("decoration_main_material_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainMaterialTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Integer id;
    /**
     * 主材名称
     */
    private String typeName;
    /**
     * 主材类型(字典值)
     */
    private String type;
    /**
     * 单位
     */
    private String unit;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reason1;
    /**
     * 备用字段2
     */
    private String reason2;
    /**
     * 数据状态（0正常 1停用）
     */
    private Integer status;

}
