/**
 * @filename:HotWordRuleService 2020-09-06 03:50:00
 * @project min_project  V1.0
 * Copyright(c) 2020 <a href=1604148881@qq.com>庄学南</a> Co. Ltd.
 * All right reserved.
 */
package com.manhattan.java.minproject.service.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.service.admin.dto.HotWordRuleAddDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordRule;

/**
 * <p>自定义service写在这里</p>
 *
 * <p>说明： 热词规则表服务层</P>
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 *
 */
public interface HotWordRuleService extends IService<HotWordRule> {

    JsonResult addHotWordRule(HotWordRuleAddDTO hotWordRuleAddDTO);

    JsonResult updateHotWordRule(HotWordRuleAddDTO hotWordRuleAddDTO);

}