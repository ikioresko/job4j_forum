package ru.job4j.forum.store;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1")
    User findUserByUsername(String name);
}
