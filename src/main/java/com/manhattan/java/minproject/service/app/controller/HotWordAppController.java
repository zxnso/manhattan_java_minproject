/**
 * @filename:HotWordRecordController 2020-09-06 03:50:00
 * @project min_project  V1.0
 * Copyright(c) 2020 <a href=1604148881@qq.com>庄学南</a> Co. Ltd.
 * All right reserved.
 */
package com.manhattan.java.minproject.service.app.controller;

import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.service.admin.dto.HotWordRecordAddDTO;
import com.manhattan.java.minproject.service.admin.service.HotWordRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>自定义方法写在这里</p>
 *
 * <p>说明： 热词APP接口层</P>
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 *
 */
@Api(description = "热词APP接口层", value = "热词APP接口层")
@RestController
@RequestMapping("/hotWordApp")
@Slf4j
public class HotWordAppController {

    public static Map<Integer, String> hotWordMap = new HashMap<>();

    @Autowired
    HotWordRecordService hotWordRecordService;

    static {
        if (hotWordMap.isEmpty()) {
            hotWordMap.put(1, "默认值");
        }
    }

    @PostMapping("/getHotWord")
    @ApiOperation(value = "获取热词", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    public JsonResult getHotWord() {
        log.info("【获取热词】{},请求参数：{}", "接口请求", null);
        return new JsonResult().success(hotWordMap);
    }

    @PostMapping("/selectHotWord")
    @ApiOperation(value = "热词搜索", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    public JsonResult selectHotWord(@Valid @RequestBody HotWordRecordAddDTO hotWordRecordAddDTO) {
        log.info("【热词搜索】{},请求参数：{}", "接口请求", hotWordRecordAddDTO);
        return hotWordRecordService.addHotWordRecord(hotWordRecordAddDTO);
    }


}