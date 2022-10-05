package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

}
