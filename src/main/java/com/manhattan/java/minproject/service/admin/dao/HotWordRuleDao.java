package com.manhattan.java.minproject.service.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manhattan.java.minproject.service.admin.dto.HotWordRulePageDTO;
import com.manhattan.java.minproject.service.admin.entity.HotWordRule;
import com.manhattan.java.minproject.service.admin.vo.HotWordRuleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>自定义mapper写在这里</p>
 *
 * <p>说明： 热词规则表数据访问层</p>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Mapper
public interface HotWordRuleDao extends BaseMapper<HotWordRule> {

    int getNum();

    HotWordRule getFirst();

    Page<HotWordRuleVO> pageAll(Page<Map<String, Object>> page, @Param(value = "param") HotWordRulePageDTO param);
}
