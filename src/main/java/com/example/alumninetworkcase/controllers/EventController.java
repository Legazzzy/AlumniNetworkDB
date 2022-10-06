package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.EventDTO.EventDTO;
import com.example.alumninetworkcase.services.event.EventService;
import com.example.alumninetworkcase.utils.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/event")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final GroupMapper groupMapper;
    private final TopicMapper topicMapper;
    private final PostMapper postMapper;

    public EventController(EventService eventService, EventMapper eventMapper
    , GroupMapper groupMapper, TopicMapper topicMapper, PostMapper postMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.groupMapper = groupMapper;
        this.topicMapper = topicMapper;
        this.postMapper = postMapper;
    }

    @GetMapping // GET: localhost:8080/api/v1/event
    public ResponseEntity getAll() {
        Collection<EventDTO> movies = eventMapper.eventToEventDTO(
                eventService.findAll()
        );
        return ResponseEntity.ok(movies);
    }
}
