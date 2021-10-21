package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.UserForm;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            var name = ((UserDetails)authentication.getPrincipal()).getUsername();
            var count = userManagment.getUser(name).getClickCount();
            model.addAttribute("click_count", count);
        }
        return "index";
    }

    @Secured("ROLE_USER")
    @PostMapping("/")
    public String someMethod(@RequestParam("click") String clickValue, Model model)
    {
        var userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = ((UserDetails)userDetail).getUsername();
        if ("clicked".equals(clickValue))
            userManagment.userClick(username);
        var count = userManagment.getUser(username).getClickCount();
        model.addAttribute("click_count", count);
        return "index";
    }
}
