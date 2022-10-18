package com.example.alumninetworkcase.services.student;

import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

public interface StudentService extends CrudService<Student, Integer> {
    Student getByName(String name);
    Student getByToken(String token);

    Student add(String token);


}
