/**
 * @filename:HotWordRecordService 2020-09-06 03:50:00
 * @project min_project  V1.0
 * Copyright(c) 2020 <a href=1604148881@qq.com>庄学南</a> Co. Ltd.
 * All right reserved.
 */
package com.manhattan.java.minproject.service.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.service.admin.dto.HotWordRecordAddDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordRecord;

/**
 * <p>自定义service写在这里</p>
 *
 * <p>说明： 热词搜索记录表服务层</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
public interface HotWordRecordService extends IService<HotWordRecord> {

    JsonResult addHotWordRecord(HotWordRecordAddDTO hotWordRecordAddDTO);
}