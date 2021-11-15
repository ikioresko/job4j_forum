package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Message;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    @Query("select m from Message m where m.postId = ?1")
    Message findMessageByPostId(int postId);
}
