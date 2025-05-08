package com.rookies3.MySpringBootLab.exception.advice;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data/// 되도록 data어노테이션은 쓰지않는편이좋음 여러가지 합쳐놓은 어노테이션이라 남발하면안되기때문
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private String timestamp;

    public String getTimestamp() {
        LocalDateTime ldt = LocalDateTime.now();
        return DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss E a",
                Locale.KOREA).format(ldt);
    }
}