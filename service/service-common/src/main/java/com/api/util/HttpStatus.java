package com.api.util;

import org.springframework.lang.Nullable;

public enum HttpStatus {
    OK(200, "成功"),
    INTERNAL_SERVER_ERROR(500, "内部错误");


    private final int value;
    private final String reasonPhrase;

    private HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public HttpStatus.Series series() {
        return HttpStatus.Series.valueOf(this);
    }

    public boolean is1xxInformational() {
        return this.series() == HttpStatus.Series.INFORMATIONAL;
    }

    public boolean is2xxSuccessful() {
        return this.series() == HttpStatus.Series.SUCCESSFUL;
    }

    public boolean is3xxRedirection() {
        return this.series() == HttpStatus.Series.REDIRECTION;
    }

    public boolean is4xxClientError() {
        return this.series() == HttpStatus.Series.CLIENT_ERROR;
    }

    public boolean is5xxServerError() {
        return this.series() == HttpStatus.Series.SERVER_ERROR;
    }

    public boolean isError() {
        return this.is4xxClientError() || this.is5xxServerError();
    }

    public String toString() {
        return this.value + " " + this.name();
    }

    public static HttpStatus valueOf(int statusCode) {
        HttpStatus status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("没有匹配常量 [" + statusCode + "]");
        } else {
            return status;
        }
    }

    @Nullable
    public static HttpStatus resolve(int statusCode) {
        HttpStatus[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            HttpStatus status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }

        return null;
    }

    public static enum Series {
        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        public static HttpStatus.Series valueOf(HttpStatus status) {
            return valueOf(status.value);
        }

        public static HttpStatus.Series valueOf(int statusCode) {
            HttpStatus.Series series = resolve(statusCode);
            if (series == null) {
                throw new IllegalArgumentException("没有匹配常量 [" + statusCode + "]");
            } else {
                return series;
            }
        }

        @Nullable
        public static HttpStatus.Series resolve(int statusCode) {
            int seriesCode = statusCode / 100;
            HttpStatus.Series[] var2 = values();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                HttpStatus.Series series = var2[var4];
                if (series.value == seriesCode) {
                    return series;
                }
            }

            return null;
        }
    }
}
