package ru.bootstrap_demo.exercise_2314.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bootstrap_demo.exercise_2314.models.User;
import ru.bootstrap_demo.exercise_2314.services.RoleService;
import ru.bootstrap_demo.exercise_2314.services.UserService;

import java.security.Principal;
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
    public String showAdminPage(Model model, Principal principal) {
        List<User> users = userService.allUsers();
        users.forEach(u -> u.setPassword(""));
        model.addAttribute("userlist", users);
        model.addAttribute("admin", userService.findByEmail(principal.getName()));
        model.addAttribute("newuser", new User());
        model.addAttribute("rolelist", roleService.allRoles());
        return "admin";
    }

    @PostMapping("/admin")
    public String addNewUser(@ModelAttribute("admin") User admin, @ModelAttribute("newuser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PutMapping ("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}
