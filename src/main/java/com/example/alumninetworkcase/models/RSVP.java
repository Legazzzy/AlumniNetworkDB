package com.example.alumninetworkcase.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RSVP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student event_student_invitation; //FK

    @ManyToOne
    @JoinColumn(name = "alumniEvent_id")
    private AlumniEvent event_invite; //FK

    @Column(length = 200)
    private String status;

    @Column(length = 200)
    private int guest_count;

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getEvent_student_invitation() {
        return event_student_invitation;
    }

    public void setEvent_student_invitation(Student event_student_invitation) {
        this.event_student_invitation = event_student_invitation;
    }

    public AlumniEvent getEvent_invite() {
        return event_invite;
    }

    public void setEvent_invite(AlumniEvent event_invite) {
        this.event_invite = event_invite;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
