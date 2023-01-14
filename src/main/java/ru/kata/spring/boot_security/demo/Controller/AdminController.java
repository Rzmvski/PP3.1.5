package ru.kata.spring.boot_security.demo.Controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Entities.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.Util.UserValidator;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }
    @GetMapping()
    public String index(@ModelAttribute("user") User user, Model model) {
//        List<User> users = userService.getAllUsers();
        model.addAttribute("roles", roleService.getAllRoles());
//        model.addAttribute("users", users);
        return "admin/index";
    }
}
