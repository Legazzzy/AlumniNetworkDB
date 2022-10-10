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
@RequestMapping(path = "api/v1/group")
public class GroupController {

    private final GroupMapper groupMapper;
    private final GroupService groupService;
    private final StudentMapper studentMapper;

    public GroupController(GroupMapper groupMapper, GroupService groupService, StudentMapper studentMapper) {
        this.groupMapper = groupMapper;
        this.groupService = groupService;
        this.studentMapper = studentMapper;
    }

    //find all groups
    @Operation(summary = "Find all groups")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Groups successfully found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No groups found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<Collection<GroupDTO>> getAll() {
        Collection<GroupDTO> groups = groupMapper.groupToGroupDTO(
                groupService.findAll()
        );
        return ResponseEntity.ok(groups);
    }


    //find group with ID
    @Operation(summary = "Get group with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Group has been found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Group.class))),
            @ApiResponse(responseCode = "404",
                    description = "Group does not exist with supplied ID",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))})
    })
    @GetMapping("{id}")
    public ResponseEntity<GroupDTO> getById(@PathVariable int id) {
        GroupDTO group = groupMapper.groupToGroupDTO(
                groupService.findById(id)
        );

        return ResponseEntity.ok(group);
    }

    //find all students in group with ID
    @Operation(summary = "Find students from group with ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group students has successfully been found",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @GetMapping("/getAllStudentsInGroup/{id}")
    public ResponseEntity getAllStudentsInGroup(@PathVariable int id){
        if (!groupService.exists(id)) {
            ResponseEntity.badRequest().build();
        }
        Group group = groupService.findById(id);
        Collection<Student> movies = groupService.getAllStudentsInGroup(group);
        return ResponseEntity.ok(movies.stream().map(movie -> studentMapper.studentToStudentDTO(movie)));
    }

    //add - add new group
    @Operation(summary = "Add new group")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "group successfully added",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
    })
    @PostMapping
    public ResponseEntity add(@RequestBody Group group) {
        Group g = groupService.add(group);
        URI location = URI.create("groups/" + g.getId());
        return ResponseEntity.created(location).build();
    }

    //update - update an existing group
    @Operation(summary = "Update group")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "group successfully updated",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity update(@RequestBody GroupDTO groupDTO, @PathVariable int id) {
        if (id != groupDTO.getId())
            return ResponseEntity.badRequest().build();
        groupService.update(
                groupMapper.GroupDTOToGroup(groupDTO));
        return ResponseEntity.noContent().build();
    }

    //deleteById - delete a franchise by id
    @Operation(summary = "Delete a group by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        groupService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    //delete - delete a franchise
    @Operation(summary = "Delete group with RequestBody")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "Group successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Malformed request",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorAttributeOptions.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found with supplied ID",
                    content = @Content)
    })
    @DeleteMapping()
    public ResponseEntity delete(@RequestBody Group group) {
        groupService.delete(group);
        return ResponseEntity.noContent().build();
    }
}
