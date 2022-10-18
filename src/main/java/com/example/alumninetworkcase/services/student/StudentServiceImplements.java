package com.example.alumninetworkcase.services.student;

import com.example.alumninetworkcase.exceptions.StudentNotFoundException;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.repositories.StudentRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    @Query("select a from Student a where a.name = ?1")
    public Student getByName(String name) {
        Student student = studentRepo.findByName(name);
        return student;
    }

    @Override
    public Student getByToken(String token) {
        Student student = studentRepo.getByToken(token);
        return student;
    }

    @Override
    public Student add(String token) {
        // Prevents internal server error for duplicates
        int id = studentRepo.getByToken(token).getId();
        /*if(studentRepo.existsById(id))
            throw new StudentAlreadyExistsException();*/
        // Create new user
        Student student = new Student();
        student.setToken(token);
        student.setComplete(false);
        return studentRepo.save(student);
    }

}
