package com.vyacheslav.chat.controllers;

import com.vyacheslav.chat.model.User;
import com.vyacheslav.chat.repositoty.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository ur;

    @GetMapping("/registration")
    public String getRegPage(Model m)
    {
        return "registery";
    }

    @PostMapping("/registration")
    public String addUser(User us, Model m)
    {
        if(us.getUsername().length()<4)
        {
            m.addAttribute("error","Username too small, he should be bigger than 4 letters");
            return "registery";
        }
        else if(us.getPassword().length()<8)
        {
            m.addAttribute("error","Password too small, he should be bigger than 8 letters");
            return "registery";
        }
        else if(ur.findByUsername(us.getUsername())!=null)
        {
            m.addAttribute("error","That username already exist");
            return "registery";
        }
        us.setActive(true);
        ur.save(us);
        return "login";
    }
}
