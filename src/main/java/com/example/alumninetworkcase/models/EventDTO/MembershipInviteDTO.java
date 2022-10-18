package com.example.alumninetworkcase.models.EventDTO;

import lombok.Data;

@Data
public class MembershipInviteDTO {
    private int id; //PK
    private int invited_student; //FK
    private int group_invite; //FK
    private String status;
}
