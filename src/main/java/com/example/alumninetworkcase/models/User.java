package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
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
            name = "user_group",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    private Set<Group> groups;

    //User to Event (Many to Many)
    @ManyToMany
    @JoinTable(
            name = "user_event",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")}
    )
    private Set<Event> events;

    //User to Topic (Many to Many)
    @ManyToMany
    @JoinTable(
            name = "user_topic",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}
    )
    private Set<Topic> topics;

    //User to Post (One to Many)
    @OneToMany(mappedBy = "target_user")
    private Set<Post> posts;

}
