package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

import java.util.Set;

@Data
public class StudentDTO {
    private String id; //PK
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
    private Set<Integer> ownedAlumniGroups;
    private Set<Integer> studentMembershipInvites;
    private Set<Integer> studentRSVPs;
}
