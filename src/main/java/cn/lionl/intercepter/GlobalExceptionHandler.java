package cn.lionl.intercepter;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Object exceptionHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();

        String message = e.getMessage();
        if (message == null || "".equals(message)) {
            map.put("msg", "未知错误");
        } else {
            map.put("msg", message);
        }
        return map;
    }
}
