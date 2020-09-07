package com.manhattan.java.minproject.service.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>代码自动生成，请勿修改</p>
 *
 * <p>说明： 热词搜索记录表实体类</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class HotWordRecordAddDTO implements Serializable {

    private static final long serialVersionUID = 1599378600145L;

    @ApiModelProperty(name = "hotWord", value = "热词")
    @NotBlank(message = "热词不能为空")
    private String hotWord;
    @ApiModelProperty(name = "searchNum", value = "次数")
    private Integer searchNum;
    @ApiModelProperty(name = "timeQuantum", value = "时间段")
    private Long timeQuantum;
    @ApiModelProperty(name = "createTime", value = "插入时间")
    private Date createTime;


}
