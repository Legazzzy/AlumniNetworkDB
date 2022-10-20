package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class AlumniEventDTO {
    private int id; //PK
    private String creator_student; //FK
    private String name;
    private String description;
    private boolean allow_guests;
    private String banner_img;
    private Timestamp start_time;
    private Timestamp end_time;
    private Timestamp last_updated;

    private int alumniGroup;
    private Set<Integer> topics;
    private Set<String> students;
    private Set<Integer> posts;
    private Set<Integer> eventRSVPs;
}
