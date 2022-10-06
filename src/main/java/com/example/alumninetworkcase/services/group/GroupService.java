package com.example.alumninetworkcase.services.group;

import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

import java.util.Collection;

public interface GroupService extends CrudService<Group, Integer> {
    Collection<Student> getAllStudentsInGroup(Group group);
}
