package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.PostRepository;
import ru.job4j.forum.store.UserRepo;

import java.util.Calendar;
import java.util.List;

@Service
public class PostService {
    private final PostRepository store;
    private final UserRepo userStore;

    public PostService(PostRepository store, UserRepo userStore) {
        this.store = store;
        this.userStore = userStore;
    }

    public List<Post> getAllPost() {
        return (List<Post>) store.findAll();
    }

    public List<User> getAllUser() {
        return (List<User>) userStore.findAll();
    }

    public Post addPost(Post post) {
        post.setCreated(Calendar.getInstance());
        return store.save(post);
    }

    public Post getPostById(int id) {
        return store.findById(id).orElse(null);
    }
}