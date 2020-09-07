package com.manhattan.java.minproject.service.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manhattan.java.minproject.service.admin.dto.HotWordHumanPageDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordHuman;
import com.manhattan.java.minproject.service.admin.vo.HotWordHumanVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>自定义mapper写在这里</p>
 *
 * <p>说明： 人工热词表数据访问层</p>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Mapper
public interface HotWordHumanDao extends BaseMapper<HotWordHuman> {

    Page<HotWordHumanVO> pageAll(Page<Map<String, Object>> page, @Param(value = "param") HotWordHumanPageDTO param);
}
