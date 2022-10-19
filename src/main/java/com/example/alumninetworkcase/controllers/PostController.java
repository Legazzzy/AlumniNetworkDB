package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.*;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.PostDTO;
import com.example.alumninetworkcase.models.EventDTO.TopicDTO;
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
import java.util.HashSet;

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


    @Operation(summary = "Find all posts")
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

    //find post with ID
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

    @Operation(summary = "Find all posts available to a student")
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
    @GetMapping("displayAllPosts") // GET: localhost:8080/api/v1/alumnigroup/displayAvailableGroups
    public ResponseEntity displayAllPosts(int accessing_student_id) {
        Collection<PostDTO> allPosts = postMapper.postToPostDTO(
                postService.findAll()
        );
        Collection<PostDTO> posts = new HashSet<PostDTO>();
        for(PostDTO pd : allPosts) {
            if(accessing_student_id == postService.findById(pd.getId()).getTarget_student().getId() ||
                    alumniGroupService.isStudentInGroup(accessing_student_id, postService.findById(pd.getId()).getTarget_alumniGroup()) ||
                    topicService.isStudentInTopic(accessing_student_id, postService.findById(pd.getId()).getTarget_topic()) ||
                    eventService.isStudentInEvent(accessing_student_id, postService.findById(pd.getId()).getTarget_alumniEvent())){
                posts.add(pd);
            }
        }
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Find all posts DMed to a student")
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
    @GetMapping("displayDMPosts") // GET: localhost:8080/api/v1/alumnigroup/displayAvailableGroups
    public ResponseEntity displayDMPosts(int accessing_student_id) {
        Collection<PostDTO> allPosts = postMapper.postToPostDTO(
                postService.findAll()
        );
        Collection<PostDTO> posts = new HashSet<PostDTO>();
        for(PostDTO pd : allPosts) {
            if(accessing_student_id == postService.findById(pd.getId()).getTarget_student().getId()){
                posts.add(pd);
            }
        }
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Find all posts in groups joined by a student")
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
    @GetMapping("displayGroupPosts") // GET: localhost:8080/api/v1/alumnigroup/displayAvailableGroups
    public ResponseEntity displayGroupsPosts(int accessing_student_id) {
        Collection<PostDTO> allPosts = postMapper.postToPostDTO(
                postService.findAll()
        );
        Collection<PostDTO> posts = new HashSet<PostDTO>();
        for(PostDTO pd : allPosts) {
            if(alumniGroupService.isStudentInGroup(accessing_student_id, postService.findById(pd.getId()).getTarget_alumniGroup())){
                posts.add(pd);
            }
        }
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Find all posts in topics joined by a student")
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
    @GetMapping("displayTopicsPosts") // GET: localhost:8080/api/v1/alumnigroup/displayAvailableGroups
    public ResponseEntity displayTopicsPosts(int accessing_student_id) {
        Collection<PostDTO> allPosts = postMapper.postToPostDTO(
                postService.findAll()
        );
        Collection<PostDTO> posts = new HashSet<PostDTO>();
        for(PostDTO pd : allPosts) {
            if(topicService.isStudentInTopic(accessing_student_id, postService.findById(pd.getId()).getTarget_topic())){
                posts.add(pd);
            }
        }
        return ResponseEntity.ok(posts);
    }

    @Operation(summary = "Find all posts in events joined by a student")
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
    @GetMapping("displayEventsPosts") // GET: localhost:8080/api/v1/alumnigroup/displayAvailableGroups
    public ResponseEntity displayEventsPosts(int accessing_student_id) {
        Collection<PostDTO> allPosts = postMapper.postToPostDTO(
                postService.findAll()
        );
        Collection<PostDTO> posts = new HashSet<PostDTO>();
        for(PostDTO pd : allPosts) {
            if(eventService.isStudentInEvent(accessing_student_id, postService.findById(pd.getId()).getTarget_alumniEvent())){
                posts.add(pd);
            }
        }
        return ResponseEntity.ok(posts);
    }


    //add new DM post
    @Operation(summary = "Add new DM post")
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

    //add new group post
    @Operation(summary = "Add new group post")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "post successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping("{id}/addGroupPost")
    public ResponseEntity addGroupPost(@PathVariable int id, @RequestBody Post post) {
        Post p = postService.add(post);
        p.setTarget_alumniGroup(alumniGroupService.findById(id));
        postService.update(p);

        URI location = URI.create("posts/" + p.getId());
        return ResponseEntity.created(location).build();
    }

    //add new topic post
    @Operation(summary = "Add new event post")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "post successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping("{id}/addEventPost")
    public ResponseEntity addEventPost(@PathVariable int id, @RequestBody Post post) {
        Post p = postService.add(post);
        p.setTarget_alumniEvent(eventService.findById(id));
        postService.update(p);

        URI location = URI.create("posts/" + p.getId());
        return ResponseEntity.created(location).build();
    }

    //add new topic post
    @Operation(summary = "Add new topic post")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "post successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping("{id}/addTopicPost")
    public ResponseEntity addTopicPost(@PathVariable int id, @RequestBody Post post) {
        Post p = postService.add(post);
        p.setTarget_topic(topicService.findById(id));
        postService.update(p);

        URI location = URI.create("posts/" + p.getId());
        return ResponseEntity.created(location).build();
    }
}
