package com.example.alumninetworkcase.services.alumnievent;

import com.example.alumninetworkcase.exceptions.AlumniEventNotFoundException;
import com.example.alumninetworkcase.models.AlumniEvent;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.repositories.AlumniEventRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AlumniEventServiceImplements implements AlumniEventService {

    private final AlumniEventRepo alumniEventRepo;

    public AlumniEventServiceImplements(AlumniEventRepo alumniEventRepo) {
        this.alumniEventRepo = alumniEventRepo;
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
}
