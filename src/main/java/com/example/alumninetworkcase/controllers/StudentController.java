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
    private final GroupMapper groupMapper;
    private final TopicMapper topicMapper;
    private final PostMapper postMapper;

    public StudentController(EventService eventService, EventMapper eventMapper, GroupMapper groupMapper, TopicMapper topicMapper, PostMapper postMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.groupMapper = groupMapper;
        this.topicMapper = topicMapper;
        this.postMapper = postMapper;
    }
}
