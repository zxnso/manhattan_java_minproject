package com.manhattan.java.minproject.service.listener;

import com.manhattan.java.minproject.common.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * <p>
 *
 * </p>
 *
 * @author <a href="mailto:zhuangxuenan.com">庄学南</a>
 * @version V0.0.1
 * @date 2020/9/8
 */

public class ContexListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LoggerFactory.getLogger(ContexListener.class);

    public ContexListener() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext() instanceof AnnotationConfigServletWebServerApplicationContext) {
            log.info("ApplcationContex refreshed.set SpringApplicationContext again.");
            log.info("old Context object:{}", SpringContextUtil.getApplicationContext());
            log.info("new Context object:{}", event.getApplicationContext());
            SpringContextUtil.setApplicationContext(event.getApplicationContext());
        }

    }
}
