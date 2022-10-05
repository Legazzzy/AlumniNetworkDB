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
    @ManyToMany (mappedBy = "topics")
    private Set<Student> students;

    //Variable used to store a Set of all the events that are a part of the topic
    @ManyToMany (mappedBy = "topics")
    private Set<Event> events;

    //User to Post (One to Many)
    @OneToMany(mappedBy = "target_topic")
    private Set<Post> posts;
}
