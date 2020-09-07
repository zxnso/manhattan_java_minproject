package com.manhattan.java.minproject.service.admin.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>代码自动生成，请勿修改</p>
 *
 * <p>说明： 热词规则表实体类</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotWordRuleVO implements Serializable {

    private static final long serialVersionUID = 1599378600183L;
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;
    @ApiModelProperty(name = "refreshCycle", value = "更新周期(小时)")
    private Integer refreshCycle;
    @ApiModelProperty(name = "hotNum", value = "热词数量")
    private Integer hotNum;
    @ApiModelProperty(name = "createId", value = "创建人ID")
    private Long createId;
    @ApiModelProperty(name = "createName", value = "创建人")
    private String createName;
    @ApiModelProperty(name = "createTime", value = "创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(name = "updateId", value = "更新人ID")
    private Long updateId;
    @ApiModelProperty(name = "updateName", value = "更新人")
    private String updateName;
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
