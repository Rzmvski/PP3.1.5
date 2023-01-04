package ru.kata.spring.boot_security.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Entities.Role;
import ru.kata.spring.boot_security.demo.Entities.User;
import ru.kata.spring.boot_security.demo.Service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()).get());
        return "user/index";
    }
    //---------Edit---------
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(id, user);
        return "redirect:/";
    }

    //--------Delete-----------
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }
}
