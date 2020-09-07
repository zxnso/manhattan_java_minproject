package com.manhattan.java.minproject.service.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

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
public class HotWordHumanAddDTO implements Serializable {

    @ApiModelProperty(name = "hotWordHumanDTOList", value = "热词集合")
    @NotEmpty(message = "热词集合不能为空")
    private List<HotWordHumanDTO> hotWordHumanDTOList;


}
