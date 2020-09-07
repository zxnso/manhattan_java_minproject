package com.manhattan.java.minproject.service.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manhattan.java.minproject.service.app.dao.HotWordShowDao;
import com.manhattan.java.minproject.service.app.entity.HotWordShow;
import com.manhattan.java.minproject.service.app.service.HotWordShowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>自定义serviceImpl写在这里</p>
 *
 * <p>说明： 热词表服务实现层</P>
 *
 * @version: V1.0
 * @author: <a href=1604148881@qq.com>庄学南</a>
 */
@Service
@Slf4j
public class HotWordShowServiceImpl extends ServiceImpl<HotWordShowDao, HotWordShow> implements HotWordShowService {

    @Override
    public Boolean insertList(List<HotWordShow> list) {

        return this.saveBatch(list);
    }
}