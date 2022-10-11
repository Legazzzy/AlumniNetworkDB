package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "sender_student_id")
    private Student sender_student;  //FK

    /*@OneToMany(mappedBy = "post")
    private Set<Post> replies;  //FK

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post reply_post; //FK*/

    @ManyToOne
    @JoinColumn(name = "target_student_id")
    private Student target_student;  //FK

    @ManyToOne
    @JoinColumn(name = "alumniGroup_id")
    private AlumniGroup target_alumniGroup; //FK

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic target_topic; //FK

    @ManyToOne
    @JoinColumn(name = "alumniEvent_id")
    private AlumniEvent target_alumniEvent; //FK

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

    public Student getTarget_student() {
        return target_student;
    }

    public void setTarget_student(Student target_student) {
        this.target_student = target_student;
    }

    public AlumniGroup getTarget_alumniGroup() {
        return target_alumniGroup;
    }

    public void setTarget_alumniGroup(AlumniGroup target_alumniGroup) {
        this.target_alumniGroup = target_alumniGroup;
    }

    public Topic getTarget_topic() {
        return target_topic;
    }

    public void setTarget_topic(Topic target_topic) {
        this.target_topic = target_topic;
    }

    public AlumniEvent getTarget_alumniEvent() {
        return target_alumniEvent;
    }

    public void setTarget_alumniEvent(AlumniEvent target_alumniEvent) {
        this.target_alumniEvent = target_alumniEvent;
    }
/*
    public Set<Post> getReplies() {
        return replies;
    }

    public void setReplies(Set<Post> replies) {
        this.replies = replies;
    }

    public Post getReply_post() {
        return reply_post;
    }

    public void setReply_post(Post reply_post) {this.reply_post = reply_post;}*/
}
