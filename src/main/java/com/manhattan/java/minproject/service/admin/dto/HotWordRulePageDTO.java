package com.manhattan.java.minproject.service.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href=1604148881@qq.com.com>庄学南</a>
 * @version V0.0.1
 * @date 2020/9/6
 */
@Data
public class HotWordRulePageDTO implements Serializable {

    @ApiModelProperty(name = "refreshCycle", value = "更新周期(小时)")
    private Integer refreshCycle;

    @ApiModelProperty(name = "hotNum", value = "热词数量")
    private Integer hotNum;
}
