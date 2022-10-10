package com.example.alumninetworkcase.repositories;

import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniGroupRepo  extends JpaRepository<AlumniGroup, Integer> {
}
