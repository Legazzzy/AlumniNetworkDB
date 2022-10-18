package com.example.alumninetworkcase.controllers;

import com.example.alumninetworkcase.mappers.TopicMapper;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.models.EventDTO.TopicDTO;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.models.Topic;
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
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/topics")
public class TopicController {
    private final TopicMapper topicMapper;
    private final TopicService topicService;
    private final StudentService studentService;

    public TopicController(TopicMapper topicMapper, TopicService topicService, StudentService studentService) {
        this.topicMapper = topicMapper;
        this.topicService = topicService;
        this.studentService = studentService;
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
        Collection<TopicDTO> events = topicMapper.topicToTopicDTO(
                topicService.findAll()
        );
        return ResponseEntity.ok(events);
    }

    //find event with ID
    @Operation(summary = "Get alumni group with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Topic has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlumniGroup.class))),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))}),
            @ApiResponse(responseCode = "404",
                    description = "Topic does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<TopicDTO> getById(@PathVariable int id) {
        TopicDTO topic = topicMapper.topicToTopicDTO(
                topicService.findById(id)
        );

        return ResponseEntity.ok(topic);
    }

    @Operation(summary = "Find all topics joined by specific student")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Topics successfully found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No alumni groups found",
                    content = @Content)
    })
    @GetMapping("displayJoinedTopics") // GET: localhost:8080/api/v1/alumnigroup/displayJoinedGroups
    public ResponseEntity displayJoinedTopics(int accessing_student_id) {
        Collection<TopicDTO> allTopics = topicMapper.topicToTopicDTO(
                topicService.findAll()
        );
        Collection<TopicDTO> topics = new HashSet<TopicDTO>();
        for(TopicDTO td : allTopics) {
            if(topicService.isStudentInTopic(accessing_student_id, topicService.findById(td.getId()))){
                topics.add(td);
            }
        }
        return ResponseEntity.ok(topics);
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
    @GetMapping("displayAvailableTopics") // GET: localhost:8080/api/v1/alumnigroup/displayAvailableGroups
    public ResponseEntity displayAvailableTopics(int accessing_student_id) {
        Collection<TopicDTO> allTopics = topicMapper.topicToTopicDTO(
                topicService.findAll()
        );
        Collection<TopicDTO> topics = new HashSet<>();
        for(TopicDTO td : topics) {
            if(!topicService.isStudentInTopic(accessing_student_id, topicService.findById(td.getId()))){
                topics.add(td);
            }
        }
        return ResponseEntity.ok(topics);
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
    @PutMapping("/{id}/addStudentToTopic")
    public ResponseEntity addStudentToTopic(@PathVariable int id, @RequestBody int student_id) {
        Student student = studentService.findById(student_id);
        Topic topic = topicService.findById(id);
        Set<Student> students = topic.getStudents();
        students.add(student);
        topic.setStudents(students);
        topicService.update(topic);
        return ResponseEntity.noContent().build();
    }
}
