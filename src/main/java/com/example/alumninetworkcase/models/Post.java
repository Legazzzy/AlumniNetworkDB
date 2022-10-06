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
    @JoinColumn(name = "student_id")
    private Student sender_student;  //FK

    @OneToMany(mappedBy = "post")
    private Set<Post> replies;  //FK

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student target_student;  //FK

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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Student getStudent() {
        return sender_student;
    }

    public void setStudent(Student student) {
        this.sender_student = student;
    }

    public Set<Post> getReplies() {
        return replies;
    }

    public void setReplies(Set<Post> replies) {
        this.replies = replies;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Student getTarget_student() {
        return target_student;
    }

    public void setTarget_student(Student target_student) {
        this.target_student = target_student;
    }

    public Group getTarget_group() {
        return target_group;
    }

    public void setTarget_group(Group target_group) {
        this.target_group = target_group;
    }

    public Topic getTarget_topic() {
        return target_topic;
    }

    public void setTarget_topic(Topic target_topic) {
        this.target_topic = target_topic;
    }

    public Event getTarget_event() {
        return target_event;
    }

    public void setTarget_event(Event target_event) {
        this.target_event = target_event;
    }
}
