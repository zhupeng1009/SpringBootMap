package com.example.demo.common.task;

import com.example.demo.business.service.DeviceService;
import com.example.demo.common.config.UrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.Socket;

/**
 * 多线程 定时任务，检查服务器事是否在线
 */
@Configuration
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@Slf4j
public class DeviceScheduleTask {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private UrlConfig urlConfig;
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    @Async
    @Scheduled(fixedRate = 30000)
    public void airPlane() {

        boolean b=telnetIPPort(urlConfig.getAriPlaneIP(),Integer.valueOf(urlConfig.getAriPlanePort()));
        if(b){
            deviceService.updateByIp(urlConfig.getAriPlaneIP(),"up");

        }else{
            log.error(urlConfig.getAriPlaneIP()+" 服务已关闭");
            deviceService.updateByIp(urlConfig.getAriPlaneIP(),"down");

        }
    }
    @Async
    @Scheduled(fixedRate = 30000)
    public void boat() {
        boolean b=telnetIPPort(urlConfig.getBoatIP(),Integer.valueOf(urlConfig.getBoatPort()));
        if(b){
            deviceService.updateByIp(urlConfig.getAriPlaneIP(),"up");
        }else{
            log.error(urlConfig.getBoatIP()+" 服务已关闭");
            deviceService.updateByIp(urlConfig.getAriPlaneIP(),"down");
        }
    }
    @Async
    @Scheduled(fixedRate = 30000)
    public void car() {
        boolean b=telnetIPPort(urlConfig.getCarIP(),Integer.valueOf(urlConfig.getCarPort()));
        if(b){
            deviceService.updateByIp(urlConfig.getAriPlaneIP(),"up");
        }else{
            log.error(urlConfig.getCarIP()+" 服务已关闭");
            deviceService.updateByIp(urlConfig.getAriPlaneIP(),"down");
        }
    }

    public static boolean telnetIPPort(String ip,int port) {
        Socket client = null;
        try{
            client = new Socket(ip, port);
            client.close();
            return true;
        }catch(Exception e){
           return false;
        }
    }
}