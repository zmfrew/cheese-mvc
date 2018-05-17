package com.zachfrew.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    public Boolean isValidCheeseName(String cheeseName) {
        char[] cheeseNameLetters = cheeseName.toCharArray();
        int cheeseNameLength = cheeseNameLetters.length;
        for (int i = 0; i < cheeseNameLength; i++) {
            char cheeseNameLetter = cheeseNameLetters[i];
            if (!cheeseName.equals("") && Character.isLetter(cheeseNameLetter)) {
                return true;
            }
        }
        return false;
    }


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription, Model model) {
        String cheeseNameError = "";
        if (isValidCheeseName(cheeseName)) {
            cheeses.put(cheeseName, cheeseDescription);
            // redirect to /cheese - already in the cheese handler
            return "redirect:";
        }
        cheeseNameError = "Please enter a valid cheese name!";
        model.addAttribute("title", "Add Cheese");
        model.addAttribute("cheeseNameError", cheeseNameError);
        return "cheese/add";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Goodbye Cheeses");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheese) {

        for (String cheeseToRemove : cheese) {
            cheeses.remove(cheeseToRemove);
        }

        return "redirect:";
    }

}
