package com.manhattan.java.minproject;

import com.manhattan.java.minproject.service.listener.ContexListener;
import com.manhattan.java.minproject.service.listener.HotWordListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ContexListener());
        springApplication.addListeners(new HotWordListener());
        springApplication.run(args);
    }

}
