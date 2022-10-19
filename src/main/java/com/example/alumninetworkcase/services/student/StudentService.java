package com.example.alumninetworkcase.services.student;

import com.example.alumninetworkcase.exceptions.StudentNotFoundException;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

public interface StudentService extends CrudService<Student, String> {

    void deleteById(String token);

    boolean exists(String token);
    Student add(String token);
    Student add(Student user);

}
