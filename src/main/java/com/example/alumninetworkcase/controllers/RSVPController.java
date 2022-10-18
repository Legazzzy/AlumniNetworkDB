package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.MembershipInviteDTO;
import com.example.alumninetworkcase.models.EventDTO.RSVPDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.MembershipInvite;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.membershipinvite.MembershipInviteService;
import com.example.alumninetworkcase.services.rsvp.RSVPService;
import com.example.alumninetworkcase.services.student.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/rsvp")
public class RSVPController {
    private final RSVPService rsvpService;
    private final RSVPMapper rsvpMapper;
    private final AlumniEventService alumniEventService;
    private final AlumniEventMapper alumniEventMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    public RSVPController(RSVPService rsvpService, RSVPMapper rsvpMapper, AlumniEventService alumniEventService, AlumniEventMapper alumniEventMapper, StudentMapper studentMapper, StudentService studentService) {
        this.rsvpService = rsvpService;
        this.rsvpMapper = rsvpMapper;
        this.alumniEventService = alumniEventService;
        this.alumniEventMapper = alumniEventMapper;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }

    @Operation(summary = "Find all rsvps")
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
    @GetMapping // GET: localhost:8080/api/v1/rsvp
    public ResponseEntity getAll() {
        Collection<RSVPDTO> rsvps = rsvpMapper.RSVPToRVSPDTO(
                rsvpService.findAll()
        );
        return ResponseEntity.ok(rsvps);
    }


}
