package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDTO {
    private Timestamp timestamp;
    private int sender_student;  //FK
    private int target_student;  //FK
    private int target_alumniEvent; //FK
    private int target_alumniGroup; //FK
    private int target_topic; //FK

    //private int reply_post;  //FK
    //private Set<Integer> replies; //FK
}
