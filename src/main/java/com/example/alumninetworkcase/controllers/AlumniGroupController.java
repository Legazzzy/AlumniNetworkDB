package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.EventDTO.GroupDTO;
import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.event.EventService;
import com.example.alumninetworkcase.services.group.GroupService;
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
@RequestMapping(path = "api/v1/alumnigroup")
public class AlumniGroupController {
}
