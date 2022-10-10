package com.example.alumninetworkcase.repositories;

import com.example.alumninetworkcase.models.AlumniEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniEventRepo extends JpaRepository<AlumniEvent, Integer> {
}
