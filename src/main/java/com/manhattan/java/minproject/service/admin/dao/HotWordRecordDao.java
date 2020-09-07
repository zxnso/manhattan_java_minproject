package com.manhattan.java.minproject.service.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manhattan.java.minproject.service.admin.dto.HotWordRecordAddDTO;
import com.manhattan.java.minproject.service.admin.dto.HotWordScheduleDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>自定义mapper写在这里</p>
 *
 * <p>说明： 热词搜索记录表数据访问层</p>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Mapper
public interface HotWordRecordDao extends BaseMapper<HotWordRecord> {

    List<HotWordRecord> scheduleHotWord(@Param(value = "scheduleDTO") HotWordScheduleDTO hotWordScheduleDTO);

    int updateNum(@Param(value = "recordAddDTO") HotWordRecordAddDTO hotWordRecordAddDTO);
}
