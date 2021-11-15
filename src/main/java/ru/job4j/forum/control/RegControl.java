package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final PostService service;

    public RegControl(PasswordEncoder encoder, PostService service) {
        this.encoder = encoder;
        this.service = service;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (service.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMessage", "Username already exist !!");
            return "reg";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(service.findByAuthority("ROLE_USER"));
        service.addUser(user);
        model.addAttribute("errorMessage", "Registration completed !!");
        return "login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}