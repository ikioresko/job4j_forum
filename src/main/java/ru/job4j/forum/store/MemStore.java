package ru.job4j.forum.store;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemStore implements Store {
    private final Map<Integer, Post> postStore = new HashMap<>();
    private final Map<Integer, User> userStore = new HashMap<>();
    private final Map<Integer, Message> messageStore = new HashMap<>();
    private final AtomicInteger postId = new AtomicInteger(1);
    private final AtomicInteger messageId = new AtomicInteger(0);

    public MemStore() {
        Post post = new Post();
        post.setId(1);
        post.setDescription("desc");
        post.setName("name");
        post.setCreated(new Calendar.Builder().build());
        postStore.put(1, post);
    }

    @Override
    public List<Post> findAllPost() {
        return new ArrayList<>(postStore.values());
    }

    @Override
    public List<User> findAllUser() {
        return new ArrayList<>(userStore.values());
    }

    @Override
    public Post addPost(Post post) {
        return post.getId() == 0 ? add(post) : edit(post);
    }

    private Post add(Post post) {
        int id = postId.incrementAndGet();
        post.setId(id);
        return postStore.put(id, post);
    }

    private Post edit(Post post) {
        return postStore.put(post.getId(), post);
    }

    @Override
    public Post findPostById(int id) {
        return postStore.get(id);
    }

    @Override
    public Message addMessage(int postId, Message message) {
        message.setPostId(postId);
        int id = messageId.incrementAndGet();
        message.setId(id);
        messageStore.put(id, message);
        return message;
    }

    @Override
    public List<Message> findMessagesByPostId(int postId) {
        return new ArrayList<>(messageStore.values());
    }
}
