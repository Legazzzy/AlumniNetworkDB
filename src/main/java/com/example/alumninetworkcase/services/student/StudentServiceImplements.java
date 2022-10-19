package com.example.alumninetworkcase.services.student;

import com.example.alumninetworkcase.exceptions.StudentAlreadyExistsException;
import com.example.alumninetworkcase.exceptions.StudentNotFoundException;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.repositories.StudentRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImplements implements StudentService {

    private final StudentRepo studentRepo;

    public StudentServiceImplements(StudentRepo userRepo) {
        this.studentRepo = userRepo;
    }

    @Override
    public Student findById(String id) {

        return studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Collection<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public Student add(Student entity) {
        return studentRepo.save(entity);
    }

    @Override
    public Student update(Student entity) {
        return studentRepo.save(entity);
    }

    @Override
    public void deleteById(String token) {
        studentRepo.deleteById(token);
    }

    @Override
    public void delete(Student entity) {
        studentRepo.delete(entity);
    }

    @Override
    public boolean exists(String token) {
        return studentRepo.existsById(token);
    }

    @Override
    public Student add(String uid) {
        // Prevents internal server error for duplicates
        if(studentRepo.existsById(uid))
            throw new StudentAlreadyExistsException();
        // Create new user
        Student student = new Student();
        student.setId(uid);
        student.setComplete(false);
        return studentRepo.save(student);
    }


}
