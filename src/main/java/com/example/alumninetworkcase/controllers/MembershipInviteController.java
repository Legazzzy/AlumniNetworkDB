package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.AlumniGroupMapper;
import com.example.alumninetworkcase.mappers.MembershipInviteMapper;
import com.example.alumninetworkcase.mappers.StudentMapper;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.MembershipInviteDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.MembershipInvite;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.membershipinvite.MembershipInviteService;
import com.example.alumninetworkcase.services.student.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/membershipinvite")
public class MembershipInviteController {
    private final MembershipInviteService membershipInviteService;
    private final MembershipInviteMapper membershipInviteMapper;
    private final AlumniGroupService alumniGroupService;
    private final AlumniGroupMapper alumniGroupMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    public MembershipInviteController(MembershipInviteService membershipInviteService, MembershipInviteMapper membershipInviteMapper, AlumniGroupService alumniGroupService, AlumniGroupMapper alumniGroupMapper, StudentMapper studentMapper, StudentService studentService) {
        this.membershipInviteService = membershipInviteService;
        this.membershipInviteMapper = membershipInviteMapper;
        this.alumniGroupService = alumniGroupService;
        this.alumniGroupMapper = alumniGroupMapper;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }


    @Operation(summary = "Find all membershipinvites")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Membershipinvites successfully found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No membershipinvites found",
                    content = @Content)
    })
    @GetMapping // GET: localhost:8080/api/v1/membershipinvite
    public ResponseEntity getAll() {
        Collection<MembershipInviteDTO> memberships = membershipInviteMapper.membershipInviteToMembershipInviteDTO(
                membershipInviteService.findAll()
        );
        return ResponseEntity.ok(memberships);
    }
}

