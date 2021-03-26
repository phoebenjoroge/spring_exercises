package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RollDieController {
    @GetMapping(path = "/roll-dice")
    @ResponseBody
    public String rollDce(){
        return "dice";
    }
}
