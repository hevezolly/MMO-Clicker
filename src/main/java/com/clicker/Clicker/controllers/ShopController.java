package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.entities.items.Item;
import com.clicker.Clicker.service.interfaces.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Pattern;

@Controller
public class ShopController {

    private ItemManagment itemManagment;
    private TeamManagment teamManagment;
    private UserManagment userManagment;
    private Pattern compiledPattern;

    private static final String commandFormat = "^buy_(team|user)_(\\d+)$";

    @Autowired
    public ShopController(ItemManagment itemManagment, TeamManagment teamManagment, UserManagment userManagment) {
        this.itemManagment = itemManagment;
        this.teamManagment = teamManagment;
        this.userManagment = userManagment;
        compiledPattern = Pattern.compile(commandFormat);
    }

    @GetMapping("/shop")
    private String getShop(Model model){
        var user = userManagment.getAuthUser();
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("userItems", itemManagment.GetUserItems());
        if (user.isLeader())
            model.addAttribute("teamItems", itemManagment.GetTeamItems());
        return "shop";
    }

    private String buyTeamItem(int index, Team team, Model model){
        var item = itemManagment.GetIndexedTeamItem(index);
        if (item == null)
            model.addAttribute("itemNotFound", "предмет не найден");
        else if (teamManagment.BuyItem(team, item) == TeamRequestResult.NotEnoughMoney)
            model.addAttribute("notEnoughMoney", "предмет слишком дорогой");
        else
            model.addAttribute("successfulPurchase", "предмет куплен");
        return "shop";
    }

    private String buyUserItem(int index, User user, Model model){
        var item = itemManagment.GetIndexedUserItem(index);
        if (item == null)
            model.addAttribute("itemNotFound", "предмет не найден");
        if (userManagment.buyItem(user.getUsername(), item) == UserRequestResult.NotEnoughMoney)
            model.addAttribute("notEnoughMoney", "предмет слишком дорогой");
        else
            model.addAttribute("successfulPurchase", "предмет куплен");
        return "shop";
    }

    @PostMapping("/shop")
    private String buy(@RequestParam("command") String command, Model model){
        var matcher = compiledPattern.matcher(command);
        if (!matcher.find())
            return "shop";
        var type = matcher.group(1);
        var index = Integer.parseInt(matcher.group(2));

        var user = userManagment.getAuthUser();
        Item item = null;

        if ("user".equals(type))
            return buyUserItem(index, user, model);

        if (!user.isLeader())
            return "shop";

        return buyTeamItem(index, user.getCurrent_team(), model);
    }
}
