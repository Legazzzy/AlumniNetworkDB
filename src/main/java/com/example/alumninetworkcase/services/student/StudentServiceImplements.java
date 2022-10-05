package com.example.alumninetworkcase.services.student;

import com.example.alumninetworkcase.exceptions.StudentNotFoundException;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.repositories.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImplements implements StudentService {

    private final StudentRepo studentRepo;

    public StudentServiceImplements(StudentRepo userRepo) {
        this.studentRepo = userRepo;
    }

    @Override
    public Student findById(Integer id) {
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
    public void deleteById(Integer id) {
        studentRepo.deleteById(id);
    }

    @Override
    public void delete(Student entity) {
        studentRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return studentRepo.existsById(id);
    }
}
