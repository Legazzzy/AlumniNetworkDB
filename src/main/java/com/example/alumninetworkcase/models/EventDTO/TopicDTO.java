package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.util.Set;

@Data
public class TopicDTO {
    private int id; //PK
    private String name;
    private String description;

    private Set<String> students;
    private Set<Integer> alumniEvents;
    private Set<Integer> posts;
}
