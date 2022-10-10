package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.Event;
import com.example.alumninetworkcase.models.EventDTO.EventDTO;
import com.example.alumninetworkcase.models.EventDTO.GroupDTO;
import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.event.EventService;
import com.example.alumninetworkcase.services.student.StudentService;
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
@RequestMapping(path = "api/v1/event")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;
    private final TopicMapper topicMapper;
    private final PostMapper postMapper;

    public EventController(EventService eventService, EventMapper eventMapper
    , GroupMapper groupMapper, StudentMapper studentMapper, StudentService studentService, TopicMapper topicMapper, PostMapper postMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.groupMapper = groupMapper;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
        this.topicMapper = topicMapper;
        this.postMapper = postMapper;
    }

    @Operation(summary = "Find all Events")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Events successfully found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No events found",
                    content = @Content)
    })
    @GetMapping // GET: localhost:8080/api/v1/event
    public ResponseEntity getAll() {
        Collection<EventDTO> events = eventMapper.eventToEventDTO(
                eventService.findAll()
        );
        return ResponseEntity.ok(events);
    }

    //find event with ID
    @Operation(summary = "Get event with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Event has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Group.class))),
            @ApiResponse(responseCode = "404",
                    description = "Event does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable int id) {
        EventDTO event = eventMapper.eventToEventDTO(
                eventService.findById(id)
        );

        return ResponseEntity.ok(event);
    }

    //find all students in event with ID
    @Operation(summary = "Find students from event with ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Event students has successfully been found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Event not found with supplied ID",
                    content = @Content)
    })
    @GetMapping("/getAllStudentsInEvent/{id}")
    public ResponseEntity getAllStudentsInEvent(@PathVariable int id){
        if (!eventService.exists(id)) {
            ResponseEntity.badRequest().build();
        }
        Event event = eventService.findById(id);
        Collection<Student> students = eventService.getAllStudentsInEvent(event);
        return ResponseEntity.ok(students.stream().map(movie -> studentMapper.studentToStudentDTO(movie)));
    }

    //add - add new event
    @Operation(summary = "Add new event")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "event successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping
    public ResponseEntity add(@RequestBody Event event) {
        Event e = eventService.add(event);
        URI location = URI.create("events/" + e.getId());
        return ResponseEntity.created(location).build();
    }

    //update - update an existing group
    @Operation(summary = "Update group")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "group successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody GroupDTO groupDTO, @PathVariable int id) {
        if (id != groupDTO.getId())
            return ResponseEntity.badRequest().build();
        groupService.update(
                groupMapper.GroupDTOToGroup(groupDTO));
        return ResponseEntity.noContent().build();
    }

    //deleteById - delete a franchise by id
    @Operation(summary = "Delete a group by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //delete - delete a franchise
    @Operation(summary = "Delete group with RequestBody")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @DeleteMapping()
    public ResponseEntity delete(@RequestBody Group group) {
        groupService.delete(group);
        return ResponseEntity.noContent().build();
    }
}
