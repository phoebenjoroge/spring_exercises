package com.codeup.codeup_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RollDieController {
    @GetMapping(path = "/roll-dice")
    public String rollDice(){
        return "dice";
    }

    @PostMapping("/roll-dice/1")
    public String viewDiceRollResult1(
            @RequestParam(name = "dice1") String dice1,
            Model viewModel){
        viewModel.addAttribute("Message", "Welcome to " + dice1 + "!");
        return "diceresult";
    }
    @PostMapping("/roll-dice/2")
    public String viewDiceRollResult2(
            @RequestParam(name = "dice2") String dice2,
            Model viewModel){
        viewModel.addAttribute("Message", "Welcome to " + dice2 + "!");
        return "diceresult";
    }
    @PostMapping("/roll-dice/3")
    public String viewDiceRollResult3(
            @RequestParam(name = "dice3") String dice2,
            Model viewModel){
        viewModel.addAttribute("Message", "You rolled  " + dice2 + "!");
        return "diceresult";
    }
}
