package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.AlumniGroupMapper;
import com.example.alumninetworkcase.mappers.MembershipInviteMapper;
import com.example.alumninetworkcase.mappers.StudentMapper;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniEventDTO;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.MembershipInvite;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.membershipinvite.MembershipInviteService;
import com.example.alumninetworkcase.services.student.StudentService;
import com.example.alumninetworkcase.utils.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/alumnigroup")
public class AlumniGroupController {
    private final AlumniGroupService alumniGroupService;
    private final AlumniGroupMapper alumniGroupMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;
    private final MembershipInviteService membershipInviteService;
    private final MembershipInviteMapper membershipInviteMapper;

    public AlumniGroupController(AlumniGroupService alumniGroupService, AlumniGroupMapper alumniGroupMapper, StudentMapper studentMapper, StudentService studentService,
                                 MembershipInviteService membershipInviteService, MembershipInviteMapper membershipInviteMapper) {
        this.alumniGroupService = alumniGroupService;
        this.alumniGroupMapper = alumniGroupMapper;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
        this.membershipInviteService = membershipInviteService;
        this.membershipInviteMapper = membershipInviteMapper;
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
    @GetMapping("displayJoinedGroups") // GET: localhost:8080/api/v1/alumnigroup/displayJoinedGroups
    public ResponseEntity displayJoinedGroups(int accessing_student_id) {
        Collection<AlumniGroupDTO> allEvents = alumniGroupMapper.AlumniGroupToAlumniGroupDTO(
                alumniGroupService.findAll()
        );
        Collection<AlumniGroupDTO> events = new HashSet<AlumniGroupDTO>();
        for(AlumniGroupDTO ad : allEvents) {
            if(alumniGroupService.isStudentInGroup(accessing_student_id, alumniGroupService.findById(ad.getId()))){
                events.add(ad);
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
    @GetMapping("displayAvailableGroups") // GET: localhost:8080/api/v1/alumnigroup/displayAvailableGroups
    public ResponseEntity displayAvailableGroups(int accessing_student_id) {
        Collection<AlumniGroupDTO> allEvents = alumniGroupMapper.AlumniGroupToAlumniGroupDTO(
                alumniGroupService.findAll()
        );
        Collection<AlumniGroupDTO> events = new HashSet<AlumniGroupDTO>();
        for(AlumniGroupDTO ad : allEvents) {
            if(!alumniGroupService.isStudentInGroup(accessing_student_id, alumniGroupService.findById(ad.getId())) && !alumniGroupService.findById(ad.getId()).get_private()){
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

    //add new Alumni group
    @Operation(summary = "Create a new alumni group")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Alumni group successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping("{id}/addAlumniGroup")
    public ResponseEntity addAlumniGroup(@PathVariable int id, @RequestBody AlumniGroup alumniGroup) {
        AlumniGroup group = alumniGroupService.add(alumniGroup);
        Student creator_student = studentService.findById(id);

        //Updates creator student
        //alumniGroupService.addStudentToGroup(group, creator_student);
        alumniGroupService.addCreatorStudentToGroup(group, creator_student.getId());
        alumniGroupService.update(group);
        
        //Creates group
        URI location = URI.create("alumnigroups/" + group.getId());
        return ResponseEntity.created(location).build();
    }

    //update Alumni group with new students
    /*@Operation(summary = "Update alumni group with new users")
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
    public ResponseEntity update(@RequestBody MembershipInvite membershipInvite, @PathVariable int id) {
        if(!alumniGroupService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        if(membershipInvite.getStatus() == "Accepted" || membershipInvite.getStatus() == null) {
            alumniGroupService.addStudentToGroup(membershipInvite.getGroup_invite(), membershipInvite.getInvited_student());
        }
        return ResponseEntity.noContent().build();
    }*/
}
