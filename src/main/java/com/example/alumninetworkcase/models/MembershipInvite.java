package com.example.alumninetworkcase.models;

import javax.persistence.*;

@Entity
public class MembershipInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student invited_student; //FK

    @ManyToOne
    @JoinColumn(name = "alumniGroup_id")
    private AlumniGroup group_invite; //FK


    @Column(length=200)
    private String status;

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getInvited_student() {
        return invited_student;
    }

    public void setInvited_student(Student invited_student) {
        this.invited_student = invited_student;
    }

    public AlumniGroup getGroup_invite() {
        return group_invite;
    }

    public void setGroup_invite(AlumniGroup group_invite) {
        this.group_invite = group_invite;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
