package com.manhattan.java.minproject.service.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.service.admin.dto.HotWordHumanAddDTO;
import com.manhattan.java.minproject.service.admin.dto.HotWordHumanDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordHuman;

/**
 * <p>自定义service写在这里</p>
 *
 * <p>说明： 人工热词表服务层</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
public interface HotWordHumanService extends IService<HotWordHuman> {

    JsonResult addHotWordHuman(HotWordHumanDTO hotWordHumanDTO);

    void scheduleHotWord();


    JsonResult updateteHotWordHuman(HotWordHumanAddDTO hotWordHumanAddDTO);

    JsonResult deleteteHotWordHuman(HotWordHumanDTO hotWordHumanDTO);
}