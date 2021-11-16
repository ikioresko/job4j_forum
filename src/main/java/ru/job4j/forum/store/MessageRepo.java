package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    @Query("select m from Message m where m.postId = ?1")
    List<Message> findMessageByPostId(int postId);
}
