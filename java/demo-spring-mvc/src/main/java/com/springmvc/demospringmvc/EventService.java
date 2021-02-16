package com.springmvc.demospringmvc;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    public List<Event> getEvents(){
        Event event = Event.builder()
                .name("스프링1")
                .limitOfEnrollment(10)
                .startDateTime(LocalDateTime.of(2021, 2, 16, 10 , 0))
                .endDateTime(LocalDateTime.of(2021, 2,16,12,0))
                .build();

        Event event2 = Event.builder()
                .name("스프링2")
                .limitOfEnrollment(10)
                .startDateTime(LocalDateTime.of(2021, 2, 16, 10 , 0))
                .endDateTime(LocalDateTime.of(2021, 2,16,12,0))
                .build();

        return List.of(event, event2);
    }
}
