package ru.job4j.forum.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Post {
    private int id;
    private String name;
    private String description;
    private Calendar created;
    private User author;
    private Set<Message> messages = new HashSet<>();

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + ", author=" + author
                + ", messages=" + messages
                + '}';
    }
}