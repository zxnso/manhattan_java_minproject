/**
 * @filename:HotWordShowService 2020-09-06 03:50:00
 * @project min_project  V1.0
 * Copyright(c) 2020 <a href=1604148881@qq.com>庄学南</a> Co. Ltd.
 * All right reserved.
 */
package com.manhattan.java.minproject.service.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manhattan.java.minproject.service.app.entity.HotWordShow;

import java.util.List;

/**
 * <p>自定义service写在这里</p>
 *
 * <p>说明： 热词表服务层</P>
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 *
 */
public interface HotWordShowService extends IService<HotWordShow> {

    Boolean insertList(List<HotWordShow> list);

}