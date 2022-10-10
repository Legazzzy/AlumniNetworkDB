package com.example.alumninetworkcase.services.alumnigroup;

import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

import java.util.Collection;

public interface AlumniGroupService extends CrudService<AlumniGroup, Integer> {
    Collection<Student> getAllStudentsInAlumniGroup(AlumniGroup alumniGroup);
}