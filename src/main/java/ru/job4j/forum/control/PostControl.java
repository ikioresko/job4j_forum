package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    PostService service;

    public PostControl(PostService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "forum/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        service.addPost(post);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", service.getPostById(id));
        return "forum/edit";
    }
}
