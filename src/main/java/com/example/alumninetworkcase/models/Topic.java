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
    private Set<AlumniEvent> alumniEvents;

    //User to Post (One to Many)
    @OneToMany(mappedBy = "target_topic")
    private Set<Post> posts;

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<AlumniEvent> getAlumniEvents() {
        return alumniEvents;
    }

    public void setAlumniEvents(Set<AlumniEvent> alumniEvents) {
        this.alumniEvents = alumniEvents;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
