package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class EventDTO {
    private int id; //PK
    private int creator_student; //FK
    private String name;
    private String description;
    private boolean allow_guests;
    private String banner_img;
    private Timestamp start_time;
    private Timestamp end_time;
    private Timestamp last_updated;

    private int group;
    private Set<Integer> topics;
    private Set<Integer> students;
    private Set<Integer> posts;
}
