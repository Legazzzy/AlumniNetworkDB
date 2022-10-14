package com.example.alumninetworkcase.services.alumnigroup;

import com.example.alumninetworkcase.exceptions.AlumniGroupNotFoundException;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.repositories.AlumniGroupRepo;
import com.example.alumninetworkcase.repositories.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AlumniGroupServiceImplements implements AlumniGroupService {

    private final AlumniGroupRepo alumniGroupRepo;

    private final StudentRepo studentRepo;

    public AlumniGroupServiceImplements(AlumniGroupRepo alumniGroupRepo, StudentRepo studentRepo) {
        this.alumniGroupRepo = alumniGroupRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public AlumniGroup findById(Integer id) {
        return alumniGroupRepo.findById(id)
                .orElseThrow(() -> new AlumniGroupNotFoundException(id));
    }

    @Override
    public Collection<AlumniGroup> findAll() {
        return alumniGroupRepo.findAll();
    }

    @Override
    public AlumniGroup add(AlumniGroup entity) {
        return alumniGroupRepo.save(entity);
    }

    @Override
    public AlumniGroup update(AlumniGroup entity) {
        return alumniGroupRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        alumniGroupRepo.deleteById(id);
    }

    @Override
    public void delete(AlumniGroup entity) {
        alumniGroupRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return alumniGroupRepo.existsById(id);
    }

    @Override
    public Collection<Student> getAllStudentsInAlumniGroup(AlumniGroup alumniGroup) {
        Collection<Student> studentsInAlumniGroup = alumniGroup.getStudents();
        return studentsInAlumniGroup;
    }

    @Override
    public AlumniGroup updateStudentsInAlumniGroup (AlumniGroup alumniGroup, Collection<Integer> ids){
        alumniGroup.getStudents().forEach(c -> c.setAlumniGroups(null));
        Set<Student> students = new HashSet<Student>();

        //Adding the students to the group
        ids.stream().forEach(p -> students.add(studentRepo.findById(p).get()));

        alumniGroup.setStudents(students);
        return alumniGroupRepo.save(alumniGroup);
    }

    @Override
    public AlumniGroup addStudentToGroup (AlumniGroup alumniGroup, int student_id) {
        alumniGroup.getStudents().forEach(c -> c.setAlumniGroups(null));
        Set<Student> students = new HashSet<Student>();

        students.add(studentRepo.findById(student_id).get());

        alumniGroup.setStudents(students);
        return alumniGroupRepo.save(alumniGroup);
    }

    @Override
    public boolean isStudentInGroup (int student_id, AlumniGroup group) {
        for (Student stud : group.getStudents()) {
            if(stud.getId() == student_id) {
                return true;
            }
        }
        return false;
    }
}