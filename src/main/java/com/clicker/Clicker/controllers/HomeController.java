package com.clicker.Clicker.controllers;

import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    UserManagment userManagment;

    @GetMapping("/")
    public String Index(Model model) {
        var user = userManagment.getAuthUser();
        if (user != null) {
            var count = user.getClickCount();
            model.addAttribute("click_count", count);
        }
        return "index";
    }

    @Secured("ROLE_USER")
    @PostMapping("/")
    public String someMethod(@RequestParam("click") String clickValue, Model model)
    {
        var user = userManagment.getAuthUser();
        if ("clicked".equals(clickValue))
            userManagment.userClick(user.getUsername());
        var count = userManagment.getUser(user.getUsername()).getClickCount();
        model.addAttribute("click_count", count);
        return "index";
    }
}
