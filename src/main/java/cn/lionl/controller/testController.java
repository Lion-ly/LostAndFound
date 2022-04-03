package cn.lionl.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class testController {
    @GetMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "hello world";
    }

    @PostMapping("/upload")
    public Integer upload(@RequestBody JSONObject req){
        System.out.println(req);
        int a = req.getInteger("num1");
        int b = req.getInteger("num2");
        return a+b;
    }
}
