package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.util.Set;

@Data
public class GroupDTO {
    private int id; //PK
    private String name;
    private String description;
    private boolean isPrivate;

    private Set<Integer> users;
    private Set<Integer> events;
    private Set<Integer> posts;
}
