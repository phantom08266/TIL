package com.example.demo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler{
    @EventListener
    public void handler(MyEvent event) {
        System.out.println("이벤트를 받았다. 데이터는 "+event.getData());
    }
}
