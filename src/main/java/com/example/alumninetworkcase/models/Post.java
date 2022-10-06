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
    @JoinColumn(name = "sender_student_id")
    private Student sender_student;  //FK

    @OneToMany(mappedBy = "post")
    private Set<Post> replies;  //FK

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post reply_post;

    @ManyToOne
    @JoinColumn(name = "target_student_id")
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

    public Student getSender_student() {
        return sender_student;
    }

    public void setSender_student(Student student) {
        this.sender_student = student;
    }

    public Set<Post> getReplies() {
        return replies;
    }

    public void setReplies(Set<Post> replies) {
        this.replies = replies;
    }

    public Post getReply_post() {
        return reply_post;
    }

    public void setReply_post(Post reply_post) {this.reply_post = reply_post;}

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
