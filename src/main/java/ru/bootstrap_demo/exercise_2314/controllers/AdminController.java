package ru.bootstrap_demo.exercise_2314.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.bootstrap_demo.exercise_2314.models.Role;
import ru.bootstrap_demo.exercise_2314.models.User;
import ru.bootstrap_demo.exercise_2314.services.RoleService;
import ru.bootstrap_demo.exercise_2314.services.UserService;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("userList", userService.allUsers());
        return "admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUserEditPage(@PathVariable("id") Long id, ModelMap model) {
        User user = userService.findUserById(id);
        model.addAttribute("addnew", false);
        model.addAttribute("user", user);
        List<Role> roles = roleService.allRoles();
        model.addAttribute("allRoles", roles);
        return "useredit";
    }

    @DeleteMapping("/admin/edit/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/new")
    public String showRegistrationPage(Model model) {
        model.addAttribute("addnew", true);
        model.addAttribute("user", new User());
        List<Role> roles = roleService.allRoles();
        model.addAttribute("allRoles", roles);
        return "useredit";
    }

    @PostMapping("/admin/new")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (!userService.saveUser(user)){
            model.addAttribute("userExistsError", true);
            List<Role> roles = roleService.allRoles();
            model.addAttribute("allRoles", roles);
            model.addAttribute("addnew", true);
            return "useredit";
        }
        return "redirect:/admin";
    }

    @PutMapping("/admin/edit/{id}")
    public String editUser(@ModelAttribute("user") User user, Model model) {
        if (!userService.updateUser(user)){
            model.addAttribute("userExistsError", true);
            List<Role> roles = roleService.allRoles();
            model.addAttribute("allRoles", roles);
            model.addAttribute("addnew", false);
            return "useredit";
        }
        return "redirect:/admin";
    }
}
