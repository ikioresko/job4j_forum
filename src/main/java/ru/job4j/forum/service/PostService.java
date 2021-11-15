package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.Store;

import java.util.Calendar;
import java.util.List;

@Service
public class PostService {
    private final Store store;

    public PostService(Store store) {
        this.store = store;
    }

    public List<Post> getAllPost() {
        return store.findAllPost();
    }

    public List<User> getAllUser() {
        return store.findAllUser();
    }

    public Post addPost(Post post) {
        post.setCreated(Calendar.getInstance());
        return store.addPost(post);
    }

    public Post getPostById(int id) {
        return store.findPostById(id);
    }

    public Message addMessage(int postId, Message message) {
        message.setCreated(Calendar.getInstance());
        return store.addMessage(postId, message);
    }

    public List<Message> findMessagesByPostId(int postId) {
        return store.findMessagesByPostId(postId);
    }
}