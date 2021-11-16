package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        service.addPost(post, username);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", service.getPostById(id));
        return "forum/edit";
    }

    @GetMapping("/discussion")
    public String discussion(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", SecurityContextHolder
                .getContext().getAuthentication().getPrincipal());
        model.addAttribute("post", service.getPostById(id));
        model.addAttribute("messages", service.findMessagesByPostId(id));
        return "post";
    }

    @PostMapping("/createMsg")
    public String createMsg(@ModelAttribute Message message,
                            @RequestParam("id") int postId) {
        message.setId(0);
        message.setPostId(postId);
        service.addMessage(message, SecurityContextHolder.getContext().getAuthentication().getName());
        return "redirect:/discussion?id=" + postId;
    }
}