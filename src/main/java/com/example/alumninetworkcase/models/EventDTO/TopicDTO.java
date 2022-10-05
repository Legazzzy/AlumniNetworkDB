package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.util.Set;

@Data
public class TopicDTO {
    private int id;
    private String name;
    private String description;

    private Set<Integer> users;
    private Set<Integer> events;
    private Set<Integer> posts;
}
