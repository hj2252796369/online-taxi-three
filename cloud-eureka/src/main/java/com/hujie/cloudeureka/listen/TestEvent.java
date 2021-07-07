package com.hujie.cloudeureka.listen;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.stereotype.Component;

/**
 * @program: online-taxi-three
 * @ClassName TestEvent
 * @description:
 * @author: huJie
 * @create: 2021-07-07 21:09
 **/
@Component
public class TestEvent {
    public void listen(EurekaInstanceCanceledEvent event){
        System.out.println("下线：" + event.getServerId()+" Name:" + event.getAppName());
    }
}
