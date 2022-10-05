package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20)
    private String name;

    @Column(length = 200)
    private String description;

    @Column()
    private boolean isPrivate;

    @ManyToMany (mappedBy = "groups")
    private Set<User> users;

    @OneToMany(mappedBy = "group")
    private Set<Event> events;

    @OneToMany(mappedBy = "target_group")
    private Set<Post> posts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
