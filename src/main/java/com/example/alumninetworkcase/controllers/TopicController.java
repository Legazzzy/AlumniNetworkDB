package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.TopicMapper;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.TopicDTO;
import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.services.topic.TopicService;
import com.example.alumninetworkcase.utils.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/topics")
public class TopicController {
    private final TopicMapper topicMapper;
    private final TopicService topicService;

    public TopicController(TopicMapper topicMapper, TopicService topicService) {
        this.topicMapper = topicMapper;
        this.topicService = topicService;
    }



    @Operation(summary = "Find all Events")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "AlumniGroups successfully found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No alumni groups found",
                    content = @Content)
    })
    @GetMapping // GET: localhost:8080/api/v1/alumnigroup
    public ResponseEntity getAll() {
        Collection<TopicDTO> events = topicMapper.topicToTopicDTO(
                topicService.findAll()
        );
        return ResponseEntity.ok(events);
    }

    //find event with ID
    @Operation(summary = "Get alumni group with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Topic has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Topic does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<TopicDTO> getById(@PathVariable int id) {
        TopicDTO topic = topicMapper.topicToTopicDTO(
                topicService.findById(id)
        );

        return ResponseEntity.ok(topic);
    }

    //add - add new event
    @Operation(summary = "Add new topic")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Topic successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping
    public ResponseEntity add(@RequestBody Topic topic) {
        Topic t = topicService.add(topic);
        URI location = URI.create("topics/" + t.getId());
        return ResponseEntity.created(location).build();
    }
}
