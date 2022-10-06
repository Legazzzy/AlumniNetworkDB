package com.example.alumninetworkcase.models.EventDTO;

import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.models.Topic;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDTO {
    private Timestamp timestamp;
    private int sender_student;  //FK
    private int reply_parent_id;  //FK
    private int target_student;  //FK
    private int target_event; //FK
    private int target_group; //FK
    private int target_topic; //FK
}
