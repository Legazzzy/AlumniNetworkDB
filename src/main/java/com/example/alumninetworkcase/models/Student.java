package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (length = 50)
    private String name;

    @Column(length = 200)
    private String picture;

    @Column(length = 20)
    private String status;

    @Column(length = 200)
    private String bio;

    @Column(length = 200)
    private String fun_fact;

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


    public String getPicture() { return picture; }

    public void setPicture(String picture) { this.picture = picture; }


    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public String getBio() { return bio; }

    public void setBio(String bio) { this.bio = bio; }

    public String getFun_fact() { return fun_fact; }

    public void setFun_fact(String fun_fact) { this.fun_fact = fun_fact; }

    //User to Group (Many to Many)
    @ManyToMany
    @JoinTable(
            name = "student_group",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    private Set<Group> groups;

    //User to Event (Many to Many)
    @ManyToMany
    @JoinTable(
            name = "student_event",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private Set<Event> events;

    @OneToMany(mappedBy = "creator_student")
    private Set<Event> createdEvents;

    //User to Topic (Many to Many)
    @ManyToMany
    @JoinTable(
            name = "student_topic",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}
    )
    private Set<Topic> topics;

    //User to Post (One to Many)
    @OneToMany(mappedBy = "target_student")
    private Set<Post> posts;

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void setCreatedEvents(Set<Event> createdEvents) {
        this.createdEvents = createdEvents;
    }
}
