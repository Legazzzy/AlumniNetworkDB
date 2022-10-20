package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class PostDTO {
    private int id;
    private Timestamp timestamp;
    private String title;
    private String content;
    private String sender_student;  //FK
    private String target_student;  //FK
    private int target_alumniEvent; //FK
    private int target_alumniGroup; //FK
    private int target_topic; //FK
    private int reply_post;  //FK
    private Set<Integer> replies;
}
