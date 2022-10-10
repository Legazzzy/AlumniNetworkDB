package com.example.alumninetworkcase.services.event;

import com.example.alumninetworkcase.models.Event;
import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

import java.util.Collection;

public interface EventService extends CrudService<Event, Integer> {
    Collection<Student> getAllStudentsInEvent(Event event);
}
