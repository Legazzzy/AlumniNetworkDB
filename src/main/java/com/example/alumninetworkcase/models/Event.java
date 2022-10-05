package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //PK

    @Column(length = 50)
    private Timestamp last_updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User created_by; //FK

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String description;

    @Column()
    private boolean allow_guests;

    @Column(length = 255)
    private String banner_img;

    @Column()
    private Timestamp start_time;

    @Column()
    private Timestamp end_time;

    @ManyToMany(mappedBy = "events")
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "event_topic",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}
    )
    private Set<Topic> topics;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "target_event")
    private Set<Post> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
