package com.Security.AuthorizationTest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CyberController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PreAuthorize("hasAnyRole('user', 'admin')")
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    // -------------------------------------------------------------------------

    @GetMapping("/indexTest1")
    public String indexTest1() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        User user = (User) authentication.getPrincipal();
        System.out.println(user.getUsername());
        System.out.println(user.getAuthorities()); return "index";

    }
    @GetMapping("/indexTest2")
    public String indexTest2(@SessionAttribute("SPRING_SECURITY_CONTEXT") SecurityContext context) {

        Authentication authentication = context.getAuthentication();

        User user = (User) authentication.getPrincipal();
        System.out.println(user.getUsername());
        System.out.println(user.getAuthorities()); return "index";

    }

    @GetMapping("/indexTest3")
    public String indexTest3() {

        new Thread(() -> {
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();

            User user = (User) authentication.getPrincipal();
            System.out.println(user.getUsername());
            System.out.println(user.getAuthorities());
        }).start(); return "index";

    }

}
