package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.entities.UserForm;
import com.clicker.Clicker.service.interfaces.UserManagment;
import com.clicker.Clicker.service.interfaces.UserRequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserManagment userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated UserForm userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        var saveRes = userService.createUser(userForm.getUsername(), userForm.getPassword());
        switch (saveRes){
            case Exists:
                model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
                return "registration";
            case Success:
                break;
            default:
                return "registration";
        }
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUser(userForm.getUsername());
        auth = new UsernamePasswordAuthenticationToken(user, userForm.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/";
    }
}
