package com.manhattan.java.minproject.service.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.service.admin.dao.HotWordHumanDao;
import com.manhattan.java.minproject.service.admin.dao.HotWordRecordDao;
import com.manhattan.java.minproject.service.admin.dao.HotWordRuleDao;
import com.manhattan.java.minproject.service.admin.dto.HotWordRuleAddDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordRule;
import com.manhattan.java.minproject.service.admin.service.HotWordRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>自定义serviceImpl写在这里</p>
 *
 * <p>说明： 热词规则表服务实现层</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Service
@Slf4j
public class HotWordRuleServiceImpl extends ServiceImpl<HotWordRuleDao, HotWordRule> implements HotWordRuleService {


    @Resource
    HotWordRuleDao dao;

    @Resource
    HotWordHumanDao hotWordHumanDao;

    @Resource
    HotWordRecordDao hotWordRecordDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addHotWordRule(HotWordRuleAddDTO hotWordRuleAddDTO) {
        int num = dao.getNum();
        if (num > 0) {
            return new JsonResult().error("已存在记录不允许新增");
        }
        HotWordRule hotWordRule = new HotWordRule();
        BeanUtils.copyProperties(hotWordRuleAddDTO, hotWordRule);
        dao.insert(hotWordRule);
        return new JsonResult().success("添加成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult updateHotWordRule(HotWordRuleAddDTO hotWordRuleAddDTO) {
        HotWordRule hotWordRule = dao.selectById(hotWordRuleAddDTO.getId());
        if (null == hotWordRule) {
            return new JsonResult().error("不存在该热词规则!");
        }
        hotWordRule.setHotNum(hotWordRuleAddDTO.getHotNum());
        hotWordRule.setRefreshCycle(hotWordRuleAddDTO.getRefreshCycle());
        dao.updateById(hotWordRule);

        // TODO 更新JVM 缓存


        return new JsonResult().success("修改规则成功!");
    }


}