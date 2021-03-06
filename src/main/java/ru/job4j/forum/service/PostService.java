package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.AuthorityRepository;
import ru.job4j.forum.store.MessageRepo;
import ru.job4j.forum.store.PostRepository;
import ru.job4j.forum.store.UserRepo;

import java.util.Calendar;
import java.util.List;

@Service
public class PostService {
    private final PostRepository store;
    private final UserRepo userStore;
    private final AuthorityRepository authorities;
    private final MessageRepo messageStore;

    public PostService(PostRepository store, UserRepo userStore, AuthorityRepository authorities,
                       MessageRepo messageStore) {
        this.store = store;
        this.userStore = userStore;
        this.authorities = authorities;
        this.messageStore = messageStore;
    }

    public List<Post> getAllPost() {
        return (List<Post>) store.findAll();
    }

    public List<User> getAllUser() {
        return (List<User>) userStore.findAll();
    }

    public Message addMessage(Message message, String username) {
        message.setAuthor(getUserByName(username));
        message.setCreated(Calendar.getInstance());
        return messageStore.save(message);
    }

    public List<Message> findMessagesByPostId(int postId) {
        return messageStore.findMessageByPostId(postId);
    }

    public Post addPost(Post post, String username) {
        post.setAuthor(getUserByName(username));
        post.setCreated(Calendar.getInstance());
        return store.save(post);
    }

    private User getUserByName(String name) {
        return userStore.findUserByUsername(name);
    }

    public Post getPostById(int id) {
        return store.findById(id).orElse(null);
    }

    public User findUserByUsername(String name) {
        return userStore.findUserByUsername(name);
    }

    public User addUser(User user) {
        return userStore.save(user);
    }

    public Authority findByAuthority(String authority) {
        return authorities.findByAuthority(authority);
    }
}