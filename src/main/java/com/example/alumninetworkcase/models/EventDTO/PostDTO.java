package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostDTO {
    private Timestamp timestamp;
    private int sender_id;  //FK
    private int reply_parent_id;  //FK
    private int target_user;  //FK
    private int target_group; //FK
    private int target_topic; //FK
}
