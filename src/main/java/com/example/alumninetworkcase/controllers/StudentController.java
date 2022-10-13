package com.example.alumninetworkcase.controllers;


import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniEventDTO;
import com.example.alumninetworkcase.models.EventDTO.PostDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
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
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final AlumniEventService eventService;
    private final AlumniEventMapper eventMapper;
    private final TopicMapper topicMapper;
    private final PostMapper postMapper;
    private final AlumniGroupMapper alumniGroupMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    public StudentController(AlumniEventService eventService, AlumniEventMapper eventMapper, TopicMapper topicMapper, PostMapper postMapper, AlumniGroupMapper alumniGroupMapper, StudentMapper studentMapper, StudentService studentService) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.topicMapper = topicMapper;
        this.postMapper = postMapper;
        this.alumniGroupMapper = alumniGroupMapper;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }

    @Operation(summary = "Find all Students")
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
    public ResponseEntity<StudentDTO> getById(@PathVariable int id) {
        StudentDTO student = studentMapper.studentToStudentDTO(
                studentService.findById(id)
        );
        return ResponseEntity.ok(student);
    }

    //post new student
    @PostMapping
    public ResponseEntity add(@RequestBody Student student) {
        Student s = studentService.add(student);
        URI location = URI.create("student/" + s.getId());
        return ResponseEntity.created(location).build();
    }
}
