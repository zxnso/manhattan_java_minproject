package com.manhattan.java.minproject.service.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manhattan.java.minproject.common.result.JsonResult;
import com.manhattan.java.minproject.service.admin.dao.HotWordRecordDao;
import com.manhattan.java.minproject.service.admin.dto.HotWordRecordAddDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordRecord;
import com.manhattan.java.minproject.service.admin.service.HotWordRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>自定义serviceImpl写在这里</p>
 *
 * <p>说明： 热词搜索记录表服务实现层</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Service
@Slf4j
public class HotWordRecordServiceImpl extends ServiceImpl<HotWordRecordDao, HotWordRecord> implements HotWordRecordService {

    @Resource
    HotWordRecordDao dao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addHotWordRecord(HotWordRecordAddDTO hotWordRecordAddDTO) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtft = DateTimeFormatter.ofPattern("yyyyMMddHH");
        String beforeTimeStr = dtft.format(now);
        Long beforeTimeLong = Long.valueOf(beforeTimeStr);

        HotWordRecord hotWordRecord = baseMapper.selectOne(new QueryWrapper<HotWordRecord>().lambda()
                .eq(HotWordRecord::getHotWord, hotWordRecordAddDTO.getHotWord())
                .eq(HotWordRecord::getTimeQuantum, beforeTimeLong));
        if (null != hotWordRecord) {
            hotWordRecordAddDTO.setTimeQuantum(beforeTimeLong);
            dao.updateNum(hotWordRecordAddDTO);
        } else {
            synchronized (this) {
                HotWordRecord hotWordRecord2 = baseMapper.selectOne(new QueryWrapper<HotWordRecord>().lambda()
                        .eq(HotWordRecord::getHotWord, hotWordRecordAddDTO.getHotWord())
                        .eq(HotWordRecord::getTimeQuantum, beforeTimeLong));
                if (null != hotWordRecord2) {
                    hotWordRecordAddDTO.setTimeQuantum(beforeTimeLong);
                    dao.updateNum(hotWordRecordAddDTO);
                } else {
                    HotWordRecord hotWordRecordAdd = new HotWordRecord();
                    hotWordRecordAdd.setHotWord(hotWordRecordAddDTO.getHotWord());
                    hotWordRecordAdd.setTimeQuantum(beforeTimeLong);
                    hotWordRecordAdd.setCreateTime(new Date());
                    hotWordRecordAdd.setSearchNum(1);
                    baseMapper.insert(hotWordRecordAdd);
                }
            }
        }

        return new JsonResult().success("新增热词成功!");
    }
}