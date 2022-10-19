package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.PostDTO;
import com.example.alumninetworkcase.models.Post;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.post.PostService;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/post")
public class PostController {

    private final PostMapper postMapper;
    private final PostService postService;

    private final AlumniGroupService alumniGroupService;
    private final AlumniGroupMapper alumniGroupMapper;
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final AlumniEventService eventService;
    private final AlumniEventMapper eventMapper;

    public PostController(PostMapper postMapper, PostService postService, AlumniGroupService alumniGroupService,
                          AlumniGroupMapper alumniGroupMapper, StudentService studentService, StudentMapper studentMapper,
                          TopicService topicService, TopicMapper topicMapper, AlumniEventService eventService, AlumniEventMapper eventMapper) {
        this.postMapper = postMapper;
        this.postService = postService;
        this.alumniGroupService = alumniGroupService;
        this.alumniGroupMapper = alumniGroupMapper;
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }


    @Operation(summary = "Find all Events")
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
        Collection<PostDTO> posts = postMapper.postToPostDTO(
                postService.findAll()
        );
        return ResponseEntity.ok(posts);
    }

    //find event with ID
    @Operation(summary = "Get a post with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Alumni group has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Alumni group does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable int id) {
        PostDTO post = postMapper.postToPostDTO(
                postService.findById(id)
        );

        return ResponseEntity.ok(post);
    }
    //add - add new event
    @Operation(summary = "Add new post")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "post successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping("{id}/addDMPost")
    public ResponseEntity addDMPost(@PathVariable int id, @RequestBody Post post) {
        Post p = postService.add(post);
        p.setTarget_student(studentService.findById(id));
        postService.update(p);

        URI location = URI.create("posts/" + p.getId());
        return ResponseEntity.created(location).build();
    }
}
