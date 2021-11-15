package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.AuthorityRepository;
import ru.job4j.forum.store.UserRepo;

@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserRepo users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserRepo users,
                      AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (users.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("errorMessage", "Username already exist !!");
            return "reg";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        model.addAttribute("errorMessage", "Registration completed !!");
        return "login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}