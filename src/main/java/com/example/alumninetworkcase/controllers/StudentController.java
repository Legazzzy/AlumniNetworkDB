package com.example.alumninetworkcase.controllers;


import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.AlumniEvent;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniEventDTO;
import com.example.alumninetworkcase.models.EventDTO.PostDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.student.StudentService;
import com.example.alumninetworkcase.services.topic.TopicService;
import com.example.alumninetworkcase.utils.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final AlumniEventService eventService;
    private final AlumniEventMapper eventMapper;
    private final TopicMapper topicMapper;
    private final TopicService topicService;
    private final PostMapper postMapper;
    private final AlumniGroupMapper alumniGroupMapper;
    private final AlumniGroupService alumniGroupService;
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    public StudentController(AlumniEventService eventService, AlumniEventMapper eventMapper, TopicMapper topicMapper, TopicService topicService, PostMapper postMapper, AlumniGroupMapper alumniGroupMapper, AlumniGroupService alumniGroupService, StudentMapper studentMapper, StudentService studentService) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.topicMapper = topicMapper;
        this.topicService = topicService;
        this.postMapper = postMapper;
        this.alumniGroupMapper = alumniGroupMapper;
        this.alumniGroupService = alumniGroupService;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }

    @Operation(summary = "Find all Students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "AlumniGroups successfully found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No alumni groups found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity getAll() {
        Collection<StudentDTO> students = studentMapper.studentToStudentDTO(
                studentService.findAll()
        );
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Get student with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "404",
                    description = "Student does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable String id) {
        StudentDTO student = studentMapper.studentToStudentDTO(
                studentService.findById(id)
        );
        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Get student with token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "404",
                    description = "Student does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })

    //post new student
    @PostMapping
    public ResponseEntity add(@RequestBody Student student) {
        Student s = studentService.add(student);
        URI location = URI.create("student/" + s.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody Student student, @PathVariable String id) {

        studentService.update(student);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Get student with name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "404",
                    description = "Student does not exist with supplied name",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("info")
    public ResponseEntity getLoggedInUserInfo(@AuthenticationPrincipal Jwt principal) {
        Map<String, String> map = new HashMap<>();
        map.put("subject", principal.getClaimAsString("sub"));
        map.put("user_name", principal.getClaimAsString("preferred_username"));
        map.put("email", principal.getClaimAsString("email"));
        map.put("first_name", principal.getClaimAsString("given_name"));
        map.put("last_name", principal.getClaimAsString("family_name"));
        map.put("roles", String.valueOf(principal.getClaimAsStringList("roles")));
        return ResponseEntity.ok(map);
    }

    @GetMapping("principal")
    public ResponseEntity getUser(Principal user){
        return ResponseEntity.ok(user);
    }

    @GetMapping("current")
    public ResponseEntity getCurrentlyLoggedInUser(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(
                studentService.findById(
                        jwt.getClaimAsString("sub")
                )
        );
    }

    //add student to topic
    @Operation(summary = "Add student to existing topic")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Topic successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping("/{id}/addTopicToStudent")
    public ResponseEntity addTopicToStudent(@PathVariable String id, @RequestBody int topic_id) {
        Topic topic = topicService.findById(topic_id);
        Student student = studentService.findById(id);
        Set<Topic> topics = student.getTopics();
        topics.add(topic);
        student.setTopics(topics);
        studentService.update(student);
        return ResponseEntity.noContent().build();
    }

    //add student to group
    @Operation(summary = "Add group to existing student")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping("/{id}/addEventToStudent")
    public ResponseEntity addEventToStudent(@PathVariable String id, @RequestBody int event_id) {
        AlumniEvent event = eventService.findById(event_id);
        Student student = studentService.findById(id);
        Set<AlumniEvent> events = student.getAlumniEvents();
        events.add(event);
        student.setAlumniEvents(events);
        studentService.update(student);
        return ResponseEntity.noContent().build();
    }

    //add student to group
    @Operation(summary = "Add group to existing student")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping("/{id}/addGroupToStudent")
    public ResponseEntity addGroupToStudent(@PathVariable String id, @RequestBody int group_id) {
        AlumniGroup alumniGroup = alumniGroupService.findById(group_id);
        Student student = studentService.findById(id);
        Set<AlumniGroup> groups = student.getAlumniGroups();
        groups.add(alumniGroup);
        student.setAlumniGroups(groups);
        studentService.update(student);
        return ResponseEntity.noContent().build();
    }

    //remove topic from student
    @Operation(summary = "Remove topic to existing student")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Event successfully removed",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping("/{id}/removeTopicFromStudent")
    public ResponseEntity removeTopicFromStudent(@PathVariable String id, @RequestBody int topic_id) {
        Topic topic = topicService.findById(topic_id);
        Student student = studentService.findById(id);
        Set<Topic> topics = student.getTopics();
        Set<Student> students = topic.getStudents();
        topics.remove(topic);
        students.remove(student);
        topic.setStudents(students);
        student.setTopics(topics);
        topicService.update(topic);
        studentService.update(student);

        return ResponseEntity.noContent().build();
    }
    //remove event from student
    @Operation(summary = "Remove event to existing student")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Event successfully removed",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping("/{id}/removeEventFromStudent")
    public ResponseEntity removeEventFromStudent(@PathVariable String id, @RequestBody int event_id) {
        AlumniEvent event = eventService.findById(event_id);
        Student student = studentService.findById(id);
        Set<AlumniEvent> events = student.getAlumniEvents();
        Set<Student> students = event.getStudents();
        events.remove(event);
        students.remove(student);
        event.setStudents(students);
        student.setAlumniEvents(events);
        eventService.update(event);
        studentService.update(student);

        return ResponseEntity.noContent().build();
    }

    //remove student to group
    @Operation(summary = "Remove group to existing student")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully removed",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping("/{id}/removeGroupFromStudent")
    public ResponseEntity removeGroupFromStudent(@PathVariable String id, @RequestBody int group_id) {
        AlumniGroup alumniGroup = alumniGroupService.findById(group_id);
        Student student = studentService.findById(id);
        Set<AlumniGroup> groups = student.getAlumniGroups();
        Set<Student> students = alumniGroup.getStudents();
        groups.remove(alumniGroup);
        students.remove(student);
        alumniGroup.setStudents(students);
        student.setAlumniGroups(groups);
        alumniGroupService.update(alumniGroup);
        studentService.update(student);

        return ResponseEntity.noContent().build();
    }


    @PostMapping("register")
    public ResponseEntity addNewUserFromJwt(@AuthenticationPrincipal Jwt jwt) {
        Student student = studentService.add(jwt.getClaimAsString("sub"));
        URI uri = URI.create("api/v1/student/" + student.getId());
        return ResponseEntity.created(uri).build();
    }

}
