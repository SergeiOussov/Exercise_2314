package ru.bootstrap_demo.exercise_2314.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bootstrap_demo.exercise_2314.models.User;

@Controller
public class UserController {

    @GetMapping(value = {"/user"})
    public String  showUserPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "userhome";
    }
}
