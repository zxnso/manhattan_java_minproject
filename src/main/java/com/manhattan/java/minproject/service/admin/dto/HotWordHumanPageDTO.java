package com.manhattan.java.minproject.service.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>代码自动生成，请勿修改</p>
 *
 * <p>说明： 人工热词表实体类</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotWordHumanPageDTO implements Serializable {

    private static final long serialVersionUID = 1599378599615L;

    @ApiModelProperty(name = "hotWord", value = "热词")
    private String hotWord;
    @ApiModelProperty(name = "sequence", value = "顺序")
    private Integer sequence;


}
