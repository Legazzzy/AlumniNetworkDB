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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<StudentDTO> getById(@PathVariable int id) {
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
    @GetMapping("/token/{token}")
    public ResponseEntity<StudentDTO> getByToken(@PathVariable String token) {
        StudentDTO student = studentMapper.studentToStudentDTO(
                studentService.getByToken(token)
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
    @GetMapping("name")
    public ResponseEntity<StudentDTO> getByName(@RequestParam String name) {
        StudentDTO student = studentMapper.studentToStudentDTO(
                studentService.getByName(name)
        );
        return ResponseEntity.ok(student);
    }
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
                studentService.getByToken(
                        jwt.getClaimAsString("sub")
                )
        );
    }

    @PostMapping("register")
    public ResponseEntity addNewUserFromJwt(@AuthenticationPrincipal Jwt jwt) {
        Student student = studentService.add(jwt.getClaimAsString("sub"));
        URI uri = URI.create("api/v1/student/" + student.getToken());
        return ResponseEntity.created(uri).build();
    }

}
