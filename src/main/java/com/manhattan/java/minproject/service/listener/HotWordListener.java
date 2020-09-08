package com.manhattan.java.minproject.service.listener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manhattan.java.minproject.common.util.SpringContextUtil;
import com.manhattan.java.minproject.service.admin.dao.HotWordRuleDao;
import com.manhattan.java.minproject.service.admin.entity.HotWordRule;
import com.manhattan.java.minproject.service.admin.service.impl.HotWordHumanServiceImpl;
import com.manhattan.java.minproject.service.app.controller.HotWordAppController;
import com.manhattan.java.minproject.service.app.dao.HotWordShowDao;
import com.manhattan.java.minproject.service.app.entity.HotWordShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 策略模式
 * 将供应商对象在项目启动时放入JVM
 * </p>
 *
 * @author <a href="mailto:zhuangxuenan.com">庄学南</a>
 * @version V0.0.1
 * @date 2020/6/16
 */
@Slf4j
public class HotWordListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    HotWordHumanServiceImpl hotWordHumanService;

    @Autowired
    HotWordRuleDao hotWordRuleDao;
    @Autowired
    HotWordShowDao hotWordShowDao;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (hotWordHumanService == null) {
            hotWordHumanService = (HotWordHumanServiceImpl) SpringContextUtil.getBean("hotWordHumanServiceImpl");
        }
        if (hotWordShowDao == null) {
            hotWordShowDao = (HotWordShowDao) SpringContextUtil.getBean("hotWordShowDao");
        }
        if (hotWordRuleDao == null) {
            hotWordRuleDao = (HotWordRuleDao) SpringContextUtil.getBean("hotWordRuleDao");
        }
        if (hotWordShowDao == null) {
            hotWordShowDao = (HotWordShowDao) SpringContextUtil.getBean("hotWordShowDao");
        }

        HotWordRule hotWordRule = hotWordRuleDao.getFirst();
        if (null == hotWordRule) {
            return;
        }

        Integer refreshCycle = hotWordRule.getRefreshCycle();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beforeTime = now.withHour(refreshCycle);
        DateTimeFormatter dtft = DateTimeFormatter.ofPattern("yyyyMMddHH");
        String beforeTimeStr = dtft.format(beforeTime);
        Long beforeTimeLong = Long.valueOf(beforeTimeStr);
        List<HotWordShow> list = hotWordShowDao.selectList(new QueryWrapper<HotWordShow>()
                .lambda().eq(HotWordShow::getTimeQuantum, beforeTimeLong));
        if (!list.isEmpty()) {
            list.forEach(hotWordShow -> {
                HotWordAppController.hotWordList.add(hotWordShow.getHotWord());
            });
        } else {
            hotWordHumanService.scheduleHotWord();
        }


    }

}
