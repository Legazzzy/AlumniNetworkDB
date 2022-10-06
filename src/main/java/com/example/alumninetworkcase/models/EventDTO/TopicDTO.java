package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.util.Set;

@Data
public class TopicDTO {
    private int id; //PK
    private String name;
    private String description;

    private Set<Integer> students;
    private Set<Integer> events;
    private Set<Integer> posts;
}
