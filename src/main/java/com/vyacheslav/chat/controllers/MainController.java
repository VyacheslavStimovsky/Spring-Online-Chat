package com.vyacheslav.chat.controllers;

import com.vyacheslav.chat.model.Message;
import com.vyacheslav.chat.repositoty.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    MessageRepository mr;

    @GetMapping({"/chat", "/"})
    public String getLoginPage(Model model)
    {
        String sender;
        //создаю сессию, чтобы узнать имя юзера
        Object session = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sender = ((UserDetails)(session)).getUsername();
        model.addAttribute("sender",sender);
        return "chat";
    }

    @PostMapping("/sendMessage")
    public String getUserMessage(Message msg, Model m)
    {
        String sender;
        //создаю сессию, чтобы узнать имя юзера
        Object session = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sender = ((UserDetails)(session)).getUsername();
        m.addAttribute("sender",sender);
        if(!msg.getMessageText().isEmpty())
        {
            mr.save(msg);
        }
        return "chat";
    }

    @GetMapping("/getMessages")
    public ResponseEntity<LinkedList<Message>> getMessages(Model m)
    {
        LinkedList<Message> result = new LinkedList<Message>();
        //конвертирую итераторы в List
        mr.findAll().forEach(result::add);
        return ResponseEntity.ok(result);
    }
}
