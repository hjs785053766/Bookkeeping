package com.api.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Notice {
    HttpStatus state;
	Long total;
    Integer pageNum;
    String notice;
    Object data;

    public Notice() {

    }

    public Notice(HttpStatus state, String notice) {
        this.state = state;
        this.notice = notice;
    }

    public Notice(HttpStatus state, Object data, String notice) {
        this.state = state;
        this.data = data;
        this.notice = notice;
    }

    public Notice(HttpStatus state, Object data, String notice, Long total, Integer pageNum) {
        this.state = state;
        this.data = data;
        this.notice = notice;
        this.total = total;
        this.pageNum = pageNum;
    }
}
