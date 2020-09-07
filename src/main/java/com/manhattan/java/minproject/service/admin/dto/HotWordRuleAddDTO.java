package com.manhattan.java.minproject.service.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
public class HotWordRuleAddDTO implements Serializable {
    @ApiModelProperty(name = "id", value = "主键")
    private Long id;

    @ApiModelProperty(name = "refreshCycle", value = "更新周期(小时)")
    @NotNull(message = "热词更新周期不能为空!")
    private Integer refreshCycle;

    @ApiModelProperty(name = "hotNum", value = "热词数量")
    @NotNull(message = "热词数量不能为空!")
    private Integer hotNum;
}
