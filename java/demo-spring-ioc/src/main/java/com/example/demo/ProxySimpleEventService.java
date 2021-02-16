package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProxySimpleEventService implements EventService{
    @Autowired
    SimpleEventService simpleEventService;
//    EventService simpleEventService; // 이렇게 해도 됨.
    @Override
    public void createEvent() {
        long begin = System.currentTimeMillis();
        simpleEventService.createEvent();
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void publishEvent() {
        long begin = System.currentTimeMillis();
        simpleEventService.publishEvent();
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void deleteEvent() {
        simpleEventService.deleteEvent();
    }
}
