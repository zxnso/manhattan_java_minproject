package com.manhattan.java.minproject.service.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class HotWordScheduleDTO implements Serializable {

    @ApiModelProperty(name = "beforeTimeLong", value = "时间段起始值")
    private Long beforeTimeLong;

    @ApiModelProperty(name = "hotNum", value = "热词数量")
    private Integer hotNum;
}
