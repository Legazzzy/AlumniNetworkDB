package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.AlumniGroupMapper;
import com.example.alumninetworkcase.mappers.StudentMapper;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniEventDTO;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.Student;
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
@RequestMapping(path = "api/v1/alumnigroup")
public class AlumniGroupController {
    private final AlumniGroupService alumniGroupService;
    private final AlumniGroupMapper alumniGroupMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    public AlumniGroupController(AlumniGroupService alumniGroupService, AlumniGroupMapper alumniGroupMapper, StudentMapper studentMapper, StudentService studentService) {
        this.alumniGroupService = alumniGroupService;
        this.alumniGroupMapper = alumniGroupMapper;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }


    @Operation(summary = "Find all alumni groups")
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
        Collection<AlumniGroupDTO> events = alumniGroupMapper.AlumniGroupToAlumniGroupDTO(
                alumniGroupService.findAll()
        );
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "Find all alumni groups viewable by specific student")
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
    @GetMapping("displayJoinedGroups") // GET: localhost:8080/api/v1/alumnigroup/getStudentGroups
    public ResponseEntity displayJoinedGroups(int accessing_student_id) {
        Collection<AlumniGroupDTO> allEvents = alumniGroupMapper.AlumniGroupToAlumniGroupDTO(
                alumniGroupService.findAll()
        );
        Collection<AlumniGroupDTO> events = new HashSet<AlumniGroupDTO>();
        for(AlumniGroupDTO ad : allEvents) {
            if(!ad.getStudents().contains(studentService.findById(accessing_student_id))){
                events.add(ad);
            }
        }
        return ResponseEntity.ok(events);
    }

    //find alumnigroup by ID
    @Operation(summary = "Get alumni group with specific ID, taking in the ID of the accessing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Alumni group has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "400",
                    description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Alumni group does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<AlumniGroupDTO> getById(@PathVariable int id, int accessing_student_id) {
        AlumniGroupDTO group = alumniGroupMapper.AlumniGroupToAlumniGroupDTO(
                alumniGroupService.findById(id)
        );

        if(group.is_private() && !alumniGroupService.isStudentInGroup(accessing_student_id, alumniGroupService.findById(id))){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(group);
    }


    //invite member to alumni group
    //TODO: does not actually add student
    @Operation(summary = "Lets a user invite another user to a group")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "User successfully added to group",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping("invite/{id}")
    public ResponseEntity inviteMember(@PathVariable int id, int invited_student_id, int host_student_id) {
        if(!alumniGroupService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        AlumniGroup group = alumniGroupService.findById(id);
        if(group.get_private() && !alumniGroupService.isStudentInGroup(host_student_id, group)){
            return ResponseEntity.badRequest().build();
        }
        alumniGroupService.addStudentToGroup(group, invited_student_id);
        return ResponseEntity.noContent().build();
    }

    //add new Alumni group
    @Operation(summary = "Add new alumni group")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Alumni group successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping
    public ResponseEntity add(@RequestBody AlumniGroup alumniGroup, int creator_student_id) {
        AlumniGroup group = alumniGroupService.add(alumniGroup);
        group.setAlumnigroup_creator_student(studentService.findById(creator_student_id));
        URI location = URI.create("alumnigroups/" + group.getId());
        return ResponseEntity.created(location).build();
    }

    //update Alumni group with new students
    @Operation(summary = "Update alumni group with new users")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Alumni group successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PutMapping
    public ResponseEntity update(@RequestBody Collection<Integer> studentIds, @PathVariable int id) {
        if(!alumniGroupService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        alumniGroupService.updateStudentsInAlumniGroup(alumniGroupService.findById(id), studentIds);
        return ResponseEntity.noContent().build();
    }
}
