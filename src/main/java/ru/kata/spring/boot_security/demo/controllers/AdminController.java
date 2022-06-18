package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {

        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "users";
    }

    @GetMapping ("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "checkbox") String[] roles) {
        user.setRoles(roleService.getListRoles(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/update-user/{id}")
    public String updateUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "update";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @PostMapping("/users/{id}")
    public String patchUser(@ModelAttribute("user") User user, @PathVariable("id") long id,
                            @RequestParam(value = "checkbox") String[] roles) {
        user.setRoles(roleService.getListRoles(roles));
        userService.update(user, id);
        return "redirect:/admin";
    }
}
