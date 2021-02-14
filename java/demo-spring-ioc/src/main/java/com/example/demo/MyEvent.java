package com.example.demo;

import org.springframework.context.ApplicationEvent;

public class MyEvent{
    private int data;

    private Object source;

    public MyEvent(Object source, int data) {
        this.data = data;
        this.source = source;
    }

    public int getData() {
        return data;
    }

    public Object getSource() {
        return source;
    }
}
