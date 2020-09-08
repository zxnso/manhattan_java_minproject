package com.manhattan.java.minproject.service.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.common.result.PageParam;
import com.manhattan.java.minproject.common.result.Pager;
import com.manhattan.java.minproject.service.admin.dao.HotWordHumanDao;
import com.manhattan.java.minproject.service.admin.dao.HotWordRuleDao;
import com.manhattan.java.minproject.service.admin.dto.*;
import com.manhattan.java.minproject.service.admin.entity.HotWordHuman;
import com.manhattan.java.minproject.service.admin.entity.HotWordRule;
import com.manhattan.java.minproject.service.admin.service.HotWordHumanService;
import com.manhattan.java.minproject.service.admin.service.HotWordRecordService;
import com.manhattan.java.minproject.service.admin.service.HotWordRuleService;
import com.manhattan.java.minproject.service.admin.vo.HotWordHumanVO;
import com.manhattan.java.minproject.service.admin.vo.HotWordRuleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * <p>自定义方法写在这里</p>
 *
 * <p>说明： 热词管理台</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Api(description = "热词管理台", value = "热词管理台")
@RestController
@RequestMapping("/hotWordAdmin")
@Slf4j
public class HotWordAdminController {

    @Autowired
    HotWordRuleService hotWordRuleService;

    @Autowired
    HotWordHumanService hotWordHumanService;

    @Autowired
    HotWordRecordService hotWordRecordService;

    @Resource
    HotWordRuleDao hotWordRuleDao;

    @Resource
    HotWordHumanDao hotWordHumanDao;


    @PostMapping("/pagesHotWordRule")
    @ApiOperation(value = "热词规则列表", notes = "作者：<a href=mailto:zhuangxuenan.com>庄学南</a>")
    JsonResult<Pager<HotWordRuleVO>> pagesHotWordRule(@RequestBody PageParam<HotWordRulePageDTO> pageParam) {
        log.info("【热词规则列表】{},请求参数：{}", "接口请求", pageParam);

        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageParam.getPageNum(), pageParam.getPageSize());
        Page<HotWordRuleVO> hotWordRulePage = hotWordRuleDao.pageAll(page, pageParam.getParam());
        return new JsonResult().success(hotWordRulePage);
    }

    @PostMapping("/addHotWordRule")
    @ApiOperation(value = "添加热词规则", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    public JsonResult addHotWordRule(@Valid @RequestBody HotWordRuleAddDTO hotWordRuleAddDTO) {
        log.info("【添加热词规则】{},请求参数：{}", "接口请求", hotWordRuleAddDTO);
        return hotWordRuleService.addHotWordRule(hotWordRuleAddDTO);
    }

    @PostMapping("/updateHotWordRule")
    @ApiOperation(value = "修改热词规则", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    public JsonResult updateHotWordRule(@Valid @RequestBody HotWordRuleAddDTO hotWordRuleAddDTO) {
        log.info("【修改热词规则】{},请求参数：{}", "接口请求", hotWordRuleAddDTO);
        if (null == hotWordRuleAddDTO.getId()) {
            return new JsonResult().error("请指明修改的记录!");
        }
        return hotWordRuleService.updateHotWordRule(hotWordRuleAddDTO);
    }


    @PostMapping("/pagesHotWordHuman")
    @ApiOperation(value = "人工热词列表", notes = "作者：<a href=mailto:zhuangxuenan.com>庄学南</a>")
    JsonResult<Pager<HotWordHumanVO>> pagesHotWordHuman(@RequestBody PageParam<HotWordHumanPageDTO> pageParam) {
        log.info("【人工热词列表】{},请求参数：{}", "接口请求", pageParam);

        Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageParam.getPageNum(), pageParam.getPageSize());
        Page<HotWordHumanVO> hotWordHumanPage = hotWordHumanDao.pageAll(page, pageParam.getParam());
        return new JsonResult().success(hotWordHumanPage);
    }

    @PostMapping("/addHotWordHuman")
    @ApiOperation(value = "添加人工热词", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    public JsonResult addHotWordHuman(@Valid @RequestBody HotWordHumanDTO hotWordHumanDTO) {
        log.info("【添加人工热词】{},请求参数：{}", "接口请求", hotWordHumanDTO);
        return hotWordHumanService.addHotWordHuman(hotWordHumanDTO);
    }

    @PostMapping("/updateteHotWordHuman")
    @ApiOperation(value = "更新人工热词", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    public JsonResult updateteHotWordHuman(@Valid @RequestBody HotWordHumanAddDTO hotWordHumanAddDTO) {
        log.info("【更新人工热词】{},请求参数：{}", "接口请求", hotWordHumanAddDTO);
        return hotWordHumanService.updateteHotWordHuman(hotWordHumanAddDTO);
    }

    @PostMapping("/deleteteHotWordHuman")
    @ApiOperation(value = "删除人工热词", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    public JsonResult deleteteHotWordHuman(@Valid @RequestBody HotWordHumanDTO hotWordHumanDTO) {
        log.info("【删除人工热词】{},请求参数：{}", "接口请求", hotWordHumanDTO);
        if (hotWordHumanDTO.getIdBatch().isEmpty()) {
            return new JsonResult().error("请选择要删除的热词!");
        }
        return hotWordHumanService.deleteteHotWordHuman(hotWordHumanDTO);
    }


    /**
     * 统计热词定时器
     *
     * @param
     * @return
     */
    @Scheduled(cron = "0 0 * * * ?")
    @ApiOperation(value = "统计热词定时器", notes = "作者：<a href=1604148881@qq.com.com>庄学南</a>")
    @PostMapping(value = "/scheduleHotWord")
    void scheduleHotWord() {

        log.info("【统计热词定时器】开始执行");
        hotWordHumanService.scheduleHotWord();

        log.info("【统计热词定时器】执行完成");
    }

}