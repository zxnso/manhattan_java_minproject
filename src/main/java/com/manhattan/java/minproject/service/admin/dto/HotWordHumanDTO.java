package com.manhattan.java.minproject.service.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class HotWordHumanDTO implements Serializable {

    @ApiModelProperty(name = "hotWord", value = "热词")
    @NotBlank(message = "热词不能为空")
    private String hotWord;
    @ApiModelProperty(name = "order", value = "顺序")
    @NotNull(message = "顺序不能为空!")
    private Integer order;

    @ApiModelProperty(name = "idBatch", value = "批量主键处理")
    private List<Long> idBatch;

}
