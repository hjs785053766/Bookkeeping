package com.api.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Notice {
    int state;
    Long total;
    Integer pages;
    Integer pageNum;
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

    public Notice(HttpStatus state, Object data, String notice, Long total, Integer pageNum, Integer pages) {
        this.state = state.value();
        this.data = data;
        this.notice = notice;
        this.total = total;
        this.pageNum = pageNum;
        this.pages = pages;
    }
}
