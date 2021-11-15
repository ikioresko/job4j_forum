package ru.job4j.forum.store;

import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.List;

public interface Store {
    List<Post> findAllPost();

    List<User> findAllUser();

    Post addPost(Post post);

    Post findPostById(int id);

    Message addMessage(int postId, Message message);

    List<Message> findMessagesByPostId(int postId);
}
