package com.example.demo;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public class EventConverter {

    @Component
    public static class StringToEventConveter implements Converter<String, Event> {
        @Override
        public Event convert(String source) {
            return new Event(Integer.parseInt(source));
        }
    }
    @Component
    public static class EventToStringConveter implements Converter<Event, String>{
        @Override
        public String convert(Event source) {
            return source.getId().toString();
        }
    }
}
