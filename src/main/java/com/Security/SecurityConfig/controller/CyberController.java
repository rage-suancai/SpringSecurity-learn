package com.Security.SecurityConfig.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CyberController {

    @GetMapping("/")
    public String index() {
        return "indexTest";
    }

    @ResponseBody
    @PostMapping("/pay")
    public JSONObject pay(@RequestParam String account) {

        JSONObject object = new JSONObject();

        System.out.println("转账给" + account + "成功 交易已完成");
        object.put("success", true); return object;

    }

}
