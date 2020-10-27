package com.servicegateway.utils;

import org.springframework.http.HttpStatus;

public class Notice {
    int state;
    String notice;
    Object data;

    public Notice() {

    }

    public Notice(HttpStatus state, String notice) {
        this.state = state.value();
        this.notice = notice;
    }

    public Notice(HttpStatus state, Object data, String notice) {
        this.state = state.value();
        this.data = data;
        this.notice = notice;
    }

    public int getState() {
        return state;
    }

    public void setState(HttpStatus state) {
        this.state = state.value();
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
