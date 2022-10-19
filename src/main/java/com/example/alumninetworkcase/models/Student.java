package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    private String id;

    @Column (length = 50)
    private String name;

    @Column(length = 200)
    private String picture;

    @Column(length = 50)
    private String status;

    @Column(length = 200)
    private String bio;

    @Column(length = 200)
    private String fun_fact;


    @Column(length = 200)
    private boolean complete;



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

    @OneToMany(mappedBy = "invited_student")
    private Set<MembershipInvite> studentMembershipInvites;

    @OneToMany(mappedBy = "event_student_invitation")
    private Set<RSVP> studentRSVPs;

    //User to created groups
    @OneToMany(mappedBy = "alumnigroup_creator_student")
    private Set<AlumniGroup> ownedAlumniGroups;

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<AlumniGroup> getOwnedAlumniGroups() {
        return ownedAlumniGroups;
    }

    public void setOwnedAlumniGroups(Set<AlumniGroup> ownedAlumniGroups) {
        this.ownedAlumniGroups = ownedAlumniGroups;
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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Set<AlumniEvent> getCreatedAlumniEvents() {
        return createdAlumniEvents;
    }

    public void setCreatedAlumniEvents(Set<AlumniEvent> createdAlumniEvents) {
        this.createdAlumniEvents = createdAlumniEvents;
    }

    public Set<MembershipInvite> getStudentMembershipInvites() {
        return studentMembershipInvites;
    }

    public void setStudentMembershipInvites(Set<MembershipInvite> studentMembershipInvites) {
        this.studentMembershipInvites = studentMembershipInvites;
    }

    public Set<RSVP> getStudentRSVPs() {
        return studentRSVPs;
    }

    public void setStudentRSVPs(Set<RSVP> studentRSVPs) {
        this.studentRSVPs = studentRSVPs;
    }
}
