package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventDTO {
    private Timestamp last_updated;
    private int created_by; //FK
    private int event_id; //PK
    private String name;
    private String description;
    private boolean allow_guests;
    private String banner_img;
    private Timestamp start_time;
    private Timestamp end_time;
}
