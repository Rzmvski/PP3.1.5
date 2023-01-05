package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Entities.User;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.Util.UserValidator;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class RegistrationController {
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private UserValidator userValidator;
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder, UserValidator userValidator, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
        this.roleService = roleService;
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        user.setRoles(roleService.findByName("ROLE_USER").stream().collect(Collectors.toSet()));
        userService.add(user);
        return "redirect:/user";
    }
}
