package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;  //FK

    @OneToMany(mappedBy = "post")
    private Set<Post> replies;  //FK

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User target_user;  //FK

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group target_group; //FK

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic target_topic; //FK

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event target_event; //FK

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
