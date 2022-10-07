package com.example.alumninetworkcase.models.EventDTO;

import java.util.Set;

public class AlumniGroupDTO {
    private int id; //PK
    private String name;
    private String description;
    private boolean is_private;

    private Set<Integer> students;
    private Set<Integer> events;
    private Set<Integer> posts;
}
