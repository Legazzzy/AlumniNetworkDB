package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

@Data
public class GroupDTO {
    private int id; //PK
    private String name;
    private String description;
    private boolean isPrivate;
}
