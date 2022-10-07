package com.example.alumninetworkcase.controllers;


import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.services.event.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final EventService eventService;
    private final EventMapper eventMapper;
    private final TopicMapper topicMapper;
    private final PostMapper postMapper;

    private final AlumniGroupMapper alumniGroupMapper;

    public StudentController(EventService eventService, EventMapper eventMapper, TopicMapper topicMapper, PostMapper postMapper, AlumniGroupMapper alumniGroupMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.topicMapper = topicMapper;
        this.postMapper = postMapper;
        this.alumniGroupMapper = alumniGroupMapper;
    }
}
