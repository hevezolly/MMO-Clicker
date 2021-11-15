package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.TeamForm;
import com.clicker.Clicker.entities.UserForm;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.print.attribute.standard.PresentationDirection;

@Controller
public class CreateTeamController {
    @Autowired
    private TeamManagment teamServace;
    @Autowired
    private UserManagment userServace;

    @GetMapping("/create_team")
    public String createTeam(Model model) {
        model.addAttribute("teamForm", new TeamForm());

        return "create_team"; //TODO: create_team.jsp
    }

    @PostMapping("/create_team")
    public String submitTeam(@ModelAttribute("teamForm") @Validated TeamForm teamForm, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "create_team";
        }
        var userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetail instanceof UserDetails))
            return "create_team";
        var username = ((UserDetails)userDetail).getUsername();
        var result = teamServace.createTeam(username, teamForm.getTeam_name());
        switch (result){
            case Success:
                break;
            default:
                model.addAttribute("Error", "что-то пошло не так");
                return "create_team";
        }
        return "redirect:/";
    }
}

