package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.util.Set;

@Data
public class AlumniGroupDTO {
    private int id; //PK
    private int alumnigroup_creator_student; //FK
    private String name;
    private String description;
    private boolean _private;

    private Set<Integer> students;
    private Set<Integer> alumniEvents;
    private Set<Integer> posts;
    private Set<Integer> groupMembershipInvites;
}
