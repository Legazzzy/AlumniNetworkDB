package com.example.alumninetworkcase.services.alumnievent;

import com.example.alumninetworkcase.exceptions.AlumniEventNotFoundException;
import com.example.alumninetworkcase.models.AlumniEvent;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.repositories.AlumniEventRepo;
import com.example.alumninetworkcase.repositories.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class AlumniEventServiceImplements implements AlumniEventService {

    private final AlumniEventRepo alumniEventRepo;

    private final StudentRepo studentRepo;

    public AlumniEventServiceImplements(AlumniEventRepo alumniEventRepo, StudentRepo studentRepo) {
        this.alumniEventRepo = alumniEventRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public AlumniEvent findById(Integer id) {
        return alumniEventRepo.findById(id)
                .orElseThrow(() -> new AlumniEventNotFoundException(id));
    }

    @Override
    public Collection<AlumniEvent> findAll() {
        return alumniEventRepo.findAll();
    }

    @Override
    public AlumniEvent add(AlumniEvent entity) {
        return alumniEventRepo.save(entity);
    }

    @Override
    public AlumniEvent update(AlumniEvent entity) {
        return alumniEventRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        alumniEventRepo.deleteById(id);
    }

    @Override
    public void delete(AlumniEvent entity) {
        alumniEventRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return alumniEventRepo.existsById(id);
    }

    @Override
    public Collection<Student> getAllStudentsInAlumniEvent(AlumniEvent alumniEvent) {
        Collection<Student> studentsInGroup = alumniEvent.getStudents();
        return studentsInGroup;
    }

    @Override
    public AlumniEvent addStudentToEvent(AlumniEvent alumniEvent, String student_id) {
        Set<Student> students = alumniEvent.getStudents();
        students.add(studentRepo.findById(student_id).get());
        alumniEvent.setStudents(students);
        return alumniEventRepo.save(alumniEvent);
    }


    @Override
    public boolean isStudentInEvent (String student_id, AlumniEvent event) {
        for (Student stud : event.getStudents()) {
            if(stud.getId().equals(student_id)) {
                return true;
            }
        }
        return false;
    }
}
