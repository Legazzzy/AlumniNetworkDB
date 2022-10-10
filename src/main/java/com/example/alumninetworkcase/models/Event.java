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
    @JoinColumn(name = "student_id")
    private Student creator_student; //FK

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
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "event_topic",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}
    )
    private Set<Topic> topics;

    @ManyToOne
    @JoinColumn(name = "alumniGroup_id")
    private AlumniGroup alumniGroup;

    @OneToMany(mappedBy = "target_event")
    private Set<Post> posts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Timestamp last_updated) {
        this.last_updated = last_updated;
    }

    public Student getCreator_student() {
        return creator_student;
    }

    public void setCreator_student(Student creator_student) {
        this.creator_student = creator_student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllow_guests() {
        return allow_guests;
    }

    public void setAllow_guests(boolean allow_guests) {
        this.allow_guests = allow_guests;
    }

    public String getBanner_img() {
        return banner_img;
    }

    public void setBanner_img(String banner_img) {
        this.banner_img = banner_img;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public AlumniGroup getAlumniGroup() {
        return alumniGroup;
    }

    public void setAlumniGroup(AlumniGroup alumniGroup) {
        this.alumniGroup = alumniGroup;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
