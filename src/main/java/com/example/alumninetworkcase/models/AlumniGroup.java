package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AlumniGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String description;

    @Column(nullable=false)
    private boolean _private;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student alumnigroup_creator_student; //FK

    @ManyToMany(mappedBy = "alumniGroups")
    private Set<Student> students;

    @OneToMany(mappedBy = "group_invite")
    private Set<MembershipInvite> groupMembershipInvites;

    @OneToMany(mappedBy = "alumniGroup")
    private Set<AlumniEvent> alumniEvents;

    @OneToMany(mappedBy = "target_alumniGroup")
    private Set<Post> posts;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getAlumnigroup_creator_student() { return alumnigroup_creator_student; }

    public Student setAlumnigroup_creator_student(Student alumnigroup_creator_student) { return this.alumnigroup_creator_student = alumnigroup_creator_student; }

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

    public Boolean get_private() {
        return _private;
    }
    public void set_private(boolean _private) {
        this._private = _private;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<AlumniEvent> getAlumniEvents() {
        return alumniEvents;
    }

    public void setAlumniEvents(Set<AlumniEvent> alumniEvents) {
        this.alumniEvents = alumniEvents;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<MembershipInvite> getGroupMembershipInvites() {
        return groupMembershipInvites;
    }

    public void setGroupMembershipInvites(Set<MembershipInvite> groupMembershipInvites){
        this.groupMembershipInvites = groupMembershipInvites;
    }
}