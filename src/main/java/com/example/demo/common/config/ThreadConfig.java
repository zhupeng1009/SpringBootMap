package com.example.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadConfig {
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程核心数目
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        //最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(10);
        //配置队列大小
        threadPoolTaskExecutor.setQueueCapacity(50);
        //配置拒绝策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //数据初始化
        threadPoolTaskExecutor.initialize();

        return threadPoolTaskExecutor;
    }

}
