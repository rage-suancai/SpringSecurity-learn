package com.Security.TestTheEnvironment.SFATest.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/cyber3")
@Controller
public class CyberController {

    @GetMapping("/index")
    public String index(HttpSession session) {

        if (session.getAttribute("login") != null) return "indexTest";
        return "loginTest";

    }

    @PostMapping("/login")
    public String loginTest(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        if ("cxk".equals(username) && "123456".equals(password)) {
            session.setAttribute("login", true); return "/indexTest";
        } else {
            model.addAttribute("status", true); return "loginTest";
        }

    }

    @ResponseBody
    @PostMapping("/pay")
    public JSONObject pay(@RequestParam String account, HttpSession session) {

        JSONObject object = new JSONObject();

        if (session.getAttribute("login") != null) {
            System.out.println("转账给: " + account + "成功 交易已完成");
            object.put("success", true);
        } else {
            System.out.println("转账给: " + account + "失败 用户未登录");
            object.put("success", false);
        }
        return object;

    }

}
