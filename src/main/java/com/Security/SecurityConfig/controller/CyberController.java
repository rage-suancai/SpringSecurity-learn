package com.Security.SecurityConfig.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CyberController {

    @Resource
    private UserDetailsManager manager;
    @Resource
    private PasswordEncoder encoder;

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

    @ResponseBody
    @PostMapping("/change-password")
    public JSONObject changePassword(@RequestParam String oldPassword,
                                     @RequestParam String newPassword) {

        JSONObject object = new JSONObject();

        manager.changePassword(oldPassword, encoder.encode(newPassword));
        object.put("success", true); return object;

    }

}
