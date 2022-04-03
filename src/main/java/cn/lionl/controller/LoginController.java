package cn.lionl.controller;


import cn.lionl.annotation.PassToken;
import cn.lionl.utils.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PassToken
    @RequestMapping(value = "/getOpenid", method = RequestMethod.GET)
    public String getOpenid(@RequestParam(value = "code")String code, @RequestParam(value = "grant_type")String grant_type) {
        Map<String, String> data = new HashMap<>();
        data.put("appid", "wx8d03710b8c404cfd");
        data.put("secret", "a43edf81e79029a235fc27e4241a968b");
        data.put("js_code", code);
        data.put("grant_type", grant_type);

        String response = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=%s",
                                        data.get("appid"), data.get("secret"), data.get("js_code"), data.get("grant_type"));
        System.out.println(response);
        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(response, String.class);
        JSONObject jsonObject =JSONObject.parseObject(str);

        String token = JwtUtil.sign(1);
//        https://api.weixin.qq.com/sns/jscode2session?appid=wx8d03710b8c404cfd&secret=a43edf81e79029a235fc27e4241a968b&js_code=043MhHkl2F32T84J0bol2qOt8F3MhHkh&grant_type=authorization_code
        System.out.println(jsonObject);

        return str;

    }
}
