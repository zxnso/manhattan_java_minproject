package com.manhattan.java.minproject.service.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.service.admin.dao.HotWordHumanDao;
import com.manhattan.java.minproject.service.admin.dao.HotWordRecordDao;
import com.manhattan.java.minproject.service.admin.dao.HotWordRuleDao;
import com.manhattan.java.minproject.service.admin.dto.HotWordHumanAddDTO;
import com.manhattan.java.minproject.service.admin.dto.HotWordHumanDTO;
import com.manhattan.java.minproject.service.admin.dto.HotWordScheduleDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordHuman;
import com.manhattan.java.minproject.service.admin.entity.HotWordRecord;
import com.manhattan.java.minproject.service.admin.entity.HotWordRule;
import com.manhattan.java.minproject.service.admin.service.HotWordHumanService;
import com.manhattan.java.minproject.service.app.controller.HotWordAppController;
import com.manhattan.java.minproject.service.app.entity.HotWordShow;
import com.manhattan.java.minproject.service.app.service.HotWordShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>自定义serviceImpl写在这里</p>
 *
 * <p>说明： 人工热词表服务实现层</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Service
@Slf4j
public class HotWordHumanServiceImpl extends ServiceImpl<HotWordHumanDao, HotWordHuman> implements HotWordHumanService {

    @Resource
    HotWordHumanDao dao;


    @Resource
    HotWordRecordDao hotWordRecordDao;

    @Resource
    HotWordRuleDao hotWordRuleDao;

    @Resource
    HotWordShowService hotWordShowService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addHotWordHuman(HotWordHumanDTO hotWordHumanDTO) {

        HotWordHuman hotWordHuman1 = baseMapper.selectOne(new QueryWrapper<HotWordHuman>()
                .lambda().eq(HotWordHuman::getHotWord, hotWordHumanDTO.getHotWord())
                .or()
                .eq(HotWordHuman::getSequence, hotWordHumanDTO.getSequence()));
        if (hotWordHuman1!=null){
            hotWordHuman1.setSequence(hotWordHumanDTO.getSequence());
            hotWordHuman1.setHotWord(hotWordHumanDTO.getHotWord());
            baseMapper.updateById(hotWordHuman1);
        }else {
            HotWordHuman hotWordHuman = new HotWordHuman();
            hotWordHuman.setHotWord(hotWordHumanDTO.getHotWord());
            hotWordHuman.setSequence(hotWordHumanDTO.getSequence());
            hotWordHuman.setCreateTime(new Date());
            baseMapper.insert(hotWordHuman);
        }

        scheduleHotWord();
        return new JsonResult().success("添加成功!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult updateteHotWordHuman(HotWordHumanAddDTO hotWordHumanAddDTO) {
        baseMapper.delete(new QueryWrapper<HotWordHuman>().lambda().isNotNull(HotWordHuman::getId));
        List<HotWordHuman> list = Lists.newArrayList();
        hotWordHumanAddDTO.getHotWordHumanDTOList().forEach(hotWordHumanDTO -> {
            HotWordHuman hotWordHuman = new HotWordHuman();
            hotWordHuman.setHotWord(hotWordHumanDTO.getHotWord());
            hotWordHuman.setSequence(hotWordHumanDTO.getSequence());
            list.add(hotWordHuman);
        });
        this.saveBatch(list);
        scheduleHotWord();
        return new JsonResult().success("更新成功!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult deleteteHotWordHuman(HotWordHumanDTO hotWordHumanDTO) {
        baseMapper.deleteBatchIds(hotWordHumanDTO.getIdBatch());
        return new JsonResult().success("删除成功!");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void scheduleHotWord() {
        HotWordRule hotWordRule = hotWordRuleDao.getFirst();
        if (null == hotWordRule) {
            return;
        }
        List<HotWordHuman> hotWordHumanList = dao.selectList(new QueryWrapper<HotWordHuman>().lambda().isNotNull(HotWordHuman::getId));


        Integer hotNum = hotWordRule.getHotNum();
        Integer refreshCycle = hotWordRule.getRefreshCycle();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeTime = now.withHour(refreshCycle);
        DateTimeFormatter dtft = DateTimeFormatter.ofPattern("yyyyMMddHH");
        String beforeTimeStr = dtft.format(beforeTime);
        Long beforeTimeLong = Long.valueOf(beforeTimeStr);

        Integer selHotNum = null;
        if (hotWordHumanList.isEmpty()) {
            selHotNum = new Integer(hotNum);
        } else {
            selHotNum = new Integer(hotNum + hotWordHumanList.size());
        }
        HotWordScheduleDTO hotWordScheduleDTO = new HotWordScheduleDTO(beforeTimeLong, selHotNum);

        List<HotWordRecord> hotWordRecordList = hotWordRecordDao.scheduleHotWord(hotWordScheduleDTO);


        if (!hotWordRecordList.isEmpty()) {
            // 去除hotWordRecordList和hotWordHumanList的交集
            removeList(hotNum, selHotNum, hotWordRecordList, hotWordHumanList);
            // 插入数据库
            insertHotWord(beforeTimeLong, hotWordRecordList);
            // 放入 JVM 内存
            setHotWordMap(hotWordRecordList);

        }
        return;

    }

    private void removeList(Integer hotNum, Integer selHotNum, List<HotWordRecord> hotWordRecordList, List<HotWordHuman> hotWordHumanList) {
        if (selHotNum.intValue() > hotNum.intValue()) {

            for (int i = 0; i < hotWordRecordList.size(); i++) {
                for (int j = 0; j < hotWordHumanList.size(); j++) {
                    if (hotWordRecordList.get(i).getHotWord().equals(hotWordHumanList.get(j).getHotWord())) {
                        hotWordRecordList.remove(i);
                    }
                }
            }

            if (hotWordRecordList.size() > hotNum.intValue()) {
                for (int i = 0; i < hotWordRecordList.size() - hotNum.intValue(); i++) {
                    hotWordRecordList.remove(hotNum);
                }
            }

            hotWordHumanList.stream().sorted(Comparator.comparing(HotWordHuman::getSequence)).collect(Collectors.toList());

            List<HotWordRecord> list = Lists.newArrayList();
            for (int i = 0; i < hotNum; i++) {

                for (int j = 0; j < hotWordHumanList.size(); j++) {
                    if (hotWordRecordList.isEmpty()) {
                        HotWordRecord hotWordRecord = new HotWordRecord();
                        hotWordRecord.setHotWord(hotWordHumanList.get(j).getHotWord());
                        list.add(hotWordRecord);
                        hotWordHumanList.remove(j);
                    } else if (hotWordHumanList.get(j).getSequence().intValue() == i + 1) {
                        HotWordRecord hotWordRecord = new HotWordRecord();
                        hotWordRecord.setHotWord(hotWordHumanList.get(j).getHotWord());
                        list.add(hotWordRecord);
                        hotWordHumanList.remove(j);
                    }
                }
                if (list.size() == i) {
                    if (!hotWordRecordList.isEmpty()) {
                        list.add(hotWordRecordList.get(0));
                        hotWordRecordList.remove(0);
                    }
                }

            }


            if (list.size() > hotNum.intValue()) {
                for (int i = 0; i < list.size() - hotNum.intValue(); i++) {
                    list.remove(hotNum);
                }
            }
            if (!hotWordRecordList.isEmpty()) {
                for (int i = 0; i < hotWordRecordList.size(); i++) {
                    hotWordRecordList.remove(0);
                }
            }

            list.forEach(hotWordRecord -> {
                hotWordRecordList.add(hotWordRecord);
            });

        }
    }

    private void insertHotWord(Long afterTimeLong, List<HotWordRecord> hotWordRecordList) {
        List<HotWordShow> list = Lists.newArrayList();

        for (int i = 0; i < hotWordRecordList.size(); i++) {
            HotWordShow hotWordShow = new HotWordShow();
            hotWordShow.setCreateTime(new Date());
            hotWordShow.setHotWord(hotWordRecordList.get(i).getHotWord());
            hotWordShow.setSequence(i + 1);
            hotWordShow.setSearchNum(hotWordRecordList.get(i).getSearchNum());
            hotWordShow.setTimeQuantum(afterTimeLong);
            list.add(hotWordShow);
        }


        hotWordShowService.insertList(list);
    }

    private void setHotWordMap(List<HotWordRecord> hotWordRecordList) {

        for (int i = 0; i < hotWordRecordList.size(); i++) {
            HotWordAppController.hotWordList.add(hotWordRecordList.get(i).getHotWord());
//            HotWordAppController.hotWordMap.put(i + 1, hotWordRecordList.get(i).getHotWord());
        }

    }
}