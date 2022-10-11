package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stud_id;

    @Column (length = 50)
    private String stud_name;

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
        return stud_id;
    }

    public void setId(int id) {
        this.stud_id = stud_id;
    }

    public String getName() { return stud_name; }

    public void setName(String stud_name) { this.stud_name = stud_name; }


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
            name = "student_alumnigroup",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "alumnigroup_id")}
    )
    private Set<AlumniGroup> alumniGroups;

    //User to AlumniEvent (Many to Many)
    @ManyToMany
    @JoinTable(
            name = "student_alumniEvent",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "alumniEvent_id")}
    )
    private Set<AlumniEvent> alumniEvents;

    @OneToMany(mappedBy = "creator_student")
    private Set<AlumniEvent> createdAlumniEvents;

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

    public Set<AlumniGroup> getAlumniGroups() {
        return alumniGroups;
    }

    public void setAlumniGroups(Set<AlumniGroup> alumniGroups) {
        this.alumniGroups = alumniGroups;
    }

    public Set<AlumniEvent> getAlumniEvents() {
        return alumniEvents;
    }

    public void setAlumniEvents(Set<AlumniEvent> alumniEvents) {
        this.alumniEvents = alumniEvents;
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

    public Set<AlumniEvent> getCreatedAlumniEvents() {
        return createdAlumniEvents;
    }

    public void setCreatedAlumniEvents(Set<AlumniEvent> createdAlumniEvents) {
        this.createdAlumniEvents = createdAlumniEvents;
    }
}
