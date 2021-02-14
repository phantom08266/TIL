package com.example.demo;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
    private int data;

    private Object source;

    public int getData() {
        return data;
    }

    public Object getSource() {
        return source;
    }

    public MyEvent(Object source, int data) {
        super(source);
        this.data = data;
        this.source = source;
    }
}
