package com.example.alumninetworkcase.services.alumnigroup;

import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.MembershipInvite;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

import java.util.Collection;

public interface AlumniGroupService extends CrudService<AlumniGroup, Integer> {
    Collection<Student> getAllStudentsInAlumniGroup(AlumniGroup alumniGroup);

    AlumniGroup updateStudentsInAlumniGroup(AlumniGroup alumniGroup, Collection<String> studentIds);

    AlumniGroup addStudentToGroup(AlumniGroup alumniGroup, String student_id);

    AlumniGroup addCreatorStudentToGroup(AlumniGroup alumniGroup, String student_id);

    public boolean isStudentInGroup(String student_id, AlumniGroup group);
}