package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.forms.TeamForm;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CreateTeamController {
    @Autowired
    private TeamManagment teamServace;
    @Autowired
    private UserManagment userServace;

    @GetMapping("/create_team")
    public String createTeam(Model model) {
        var team = userServace.getAuthUser().getCurrent_team();
        if (team != null)
            return "redirect:/";
        model.addAttribute("teamForm", new TeamForm());

        return "create_team"; //TODO: create_team.jsp
    }

    @PostMapping("/create_team")
    public String submitTeam(@ModelAttribute("teamForm") @Validated TeamForm teamForm, BindingResult bindingResult, Model model){
        var user = userServace.getAuthUser();
        if (user == null)
            return "create_team";
        var username = user.getUsername();
        var result = teamServace.createTeam(username, teamForm.getTeam_name());
        switch (result){
            case Success:
                break;
            case TeamExists:
                model.addAttribute("teamNameError", "команда с таким именем уже существует");
                return "create_team";
            default:
                model.addAttribute("Error", "что-то пошло не так");
                return "create_team";
        }
        return "redirect:/team";
    }
}

