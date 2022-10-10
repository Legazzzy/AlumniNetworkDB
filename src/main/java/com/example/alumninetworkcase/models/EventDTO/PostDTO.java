package com.example.alumninetworkcase.models.EventDTO;

import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.models.Topic;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class PostDTO {
    private Timestamp timestamp;
    private int sender_student;  //FK
    private int target_student;  //FK
    private int target_event; //FK
    private int target_alumniGroup; //FK
    private int target_topic; //FK

    //private int reply_post;  //FK
    //private Set<Integer> replies; //FK
}
