package com.example.alumninetworkcase.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class User {

    @Id
    private String uid;
    private String bio;
    private boolean complete;
}
