package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.util.Set;

@Data
public class StudentDTO {
    private int id; //PK
    private String token;
    private String name;
    private String picture;
    private String status;
    private String bio;
    private String fun_fact;

    private Set<Integer> createdAlumniEvents;
    private Set<Integer> alumniGroups;
    private Set<Integer> alumniEvents;
    private Set<Integer> topics;
    private Set<Integer> posts;
}
