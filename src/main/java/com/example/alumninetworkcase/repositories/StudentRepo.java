package com.example.alumninetworkcase.repositories;

import com.example.alumninetworkcase.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    @Query("select a from Student a where a.name = ?1")
    Student findByName(String name);
}
