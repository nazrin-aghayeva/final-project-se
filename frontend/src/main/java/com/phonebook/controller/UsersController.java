package com.phonebook.controller;

import com.phonebook.client.PhonebookClient;
import com.phonebook.dto.UserDto;
import com.phonebook.dto.UserReq;
import com.phonebook.dto.UserResp;
import java.net.InetAddress;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UsersController {

    private final PhonebookClient phonebookClient;

    @GetMapping("/")
    public String listUsers(Model model) {
        List<UserDto> users = phonebookClient.getAllUsers();
        try {
            model.addAttribute("hostname", InetAddress.getLocalHost().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("users", users);
        model.addAttribute("userReq", new UserReq());
        return "user";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute UserReq userReq, Model model) {
        UserResp userResp = phonebookClient.addUser(userReq);
        try {
            model.addAttribute("hostname", InetAddress.getLocalHost().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("userResp", userResp);
        return "operation";
    }
}
