package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Variable used to store a Set of all the users that are a part of the topic
    @ManyToMany (mappedBy = "users")
    private Set<User> users;

    //Variable used to store a Set of all the events that are a part of the topic
    @ManyToMany (mappedBy = "events")
    private Set<Event> events;

    //User to Post (One to Many)
    @OneToMany(mappedBy = "topics")
    private Set<Post> posts;
}
