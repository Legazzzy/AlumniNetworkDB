package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class RSVPDTO {
    private int id;

    private int event_student_invitation; //FK
    private int event_invite; //FK
    private String status;
    private int guest_count;
}
