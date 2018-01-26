package com.ruszkowski89.Hibernate.dto;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    private User user;

    public Book() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }
}
