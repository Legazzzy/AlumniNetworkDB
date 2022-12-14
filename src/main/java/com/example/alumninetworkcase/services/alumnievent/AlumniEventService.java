package com.example.alumninetworkcase.services.alumnievent;
import com.example.alumninetworkcase.models.AlumniEvent;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

import java.util.Collection;

public interface AlumniEventService extends CrudService<AlumniEvent, Integer> {
    Collection<Student> getAllStudentsInAlumniEvent(AlumniEvent alumniEvent);
    AlumniEvent addStudentToEvent(AlumniEvent alumniEvent, String student_id);
    public boolean isStudentInEvent(String student_id, AlumniEvent event);}
