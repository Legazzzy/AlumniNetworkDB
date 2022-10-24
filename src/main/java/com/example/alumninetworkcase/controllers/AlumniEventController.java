package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.AlumniEvent;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniEventDTO;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
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
import java.util.HashSet;

@RestController
@RequestMapping(path = "api/v1/alumniEvent")
public class AlumniEventController {
    private final AlumniEventService eventService;
    private final AlumniEventMapper eventMapper;
    private final AlumniGroupService alumniGroupService;
    private final AlumniGroupMapper groupMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;
    private final TopicMapper topicMapper;
    private final PostMapper postMapper;

    public AlumniEventController(AlumniEventService eventService, AlumniEventMapper eventMapper
            , AlumniGroupService alumniGroupService, TopicMapper topicMapper, PostMapper postMapper, AlumniGroupMapper groupMapper, StudentMapper studentMapper, StudentService studentService) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.alumniGroupService = alumniGroupService;
        this.groupMapper = groupMapper;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
        this.topicMapper = topicMapper;
        this.postMapper = postMapper;
    }

    @Operation(summary = "Find all Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Events successfully found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No events found",
                    content = @Content)
    })
    @GetMapping // GET: localhost:8080/api/v1/event
    public ResponseEntity getAll() {
        Collection<AlumniEventDTO> events = eventMapper.alumniEventToAlumniEventDTO(
                eventService.findAll()
        );
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "Find all alumni groups joined by specific student")
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
    @GetMapping("displayJoinedEvents") // GET: localhost:8080/api/v1/alumnigroup/displayJoinedGroups
    public ResponseEntity displayJoinedEvents(String accessing_student_id) {
        Collection<AlumniEventDTO> allEvents = eventMapper.alumniEventToAlumniEventDTO(
                eventService.findAll()
        );
        Collection<AlumniEventDTO> events = new HashSet<AlumniEventDTO>();
        for(AlumniEventDTO ed : allEvents) {
            if(eventService.isStudentInEvent(accessing_student_id, eventService.findById(ed.getId()))){
                events.add(ed);
            }
        }
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "Find all alumni groups available to specific student that they have not yet joined")
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
    @GetMapping("displayAvailableEvents")
    public ResponseEntity displayAvailableEvents(String accessing_student_id) {
        Collection<AlumniEventDTO> allEvents = eventMapper.alumniEventToAlumniEventDTO(
                eventService.findAll()
        );
        Collection<AlumniEventDTO> events = new HashSet<AlumniEventDTO>();
        for(AlumniEventDTO ed : allEvents) {
            if(!eventService.isStudentInEvent(accessing_student_id, eventService.findById(ed.getId()))){
                events.add(ed);
            }
        }
        return ResponseEntity.ok(events);
    }

    //find event with ID
    @Operation(summary = "Get event with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Event has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "404",
                    description = "Event does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<AlumniEventDTO> getById(@PathVariable int id) {
        AlumniEventDTO event = eventMapper.alumniEventToAlumniEventDTO(
                eventService.findById(id)
        );

        return ResponseEntity.ok(event);
    }

    //find all students in event with ID
    @Operation(summary = "Find students from event with ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Event students has successfully been found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Event not found with supplied ID",
                    content = @Content)
    })
    @GetMapping("/getAllStudentsInEvent/{id}")
    public ResponseEntity getAllStudentsInEvent(@PathVariable int id) {
        if (!eventService.exists(id)) {
            ResponseEntity.badRequest().build();
        }
        AlumniEvent alumniEvent = eventService.findById(id);
        Collection<Student> students = eventService.getAllStudentsInAlumniEvent(alumniEvent);
        return ResponseEntity.ok(students.stream().map(movie -> studentMapper.studentToStudentDTO(movie)));
    }

    // add new event
    @Operation(summary = "Add new event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "event successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))}),
    })
    @PostMapping("{id}/addAlumniEvent")
    public ResponseEntity add(@PathVariable String id, @RequestBody AlumniEvent alumniEvent) {
        AlumniEvent event = eventService.add(alumniEvent);
        event.setCreator_student(studentService.findById(id));

        URI location = URI.create("alumniEvent/" + event.getId());
        return ResponseEntity.created(location).build();
    }

    //update an existing event
    @Operation(summary = "Update event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "event successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody AlumniEventDTO alumniEventDTO, @PathVariable int id) {
        if (id != alumniEventDTO.getId())
            return ResponseEntity.badRequest().build();
        eventService.update(eventMapper.alumniEventDTOToAlumniEvent(alumniEventDTO));
        return ResponseEntity.noContent().build();
    }


    //deleteById - delete a event by id
    @Operation(summary = "Delete a event by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Invite student to event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Student successfully added to event",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Event not found with supplied ID",
                    content = @Content)
    })@PutMapping("invite{id}")
    public ResponseEntity inviteStudent(String invited_student_id, @PathVariable int id) {
        if(!eventService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        eventService.addStudentToEvent(eventService.findById(id), invited_student_id);
        return ResponseEntity.noContent().build();
    }
}