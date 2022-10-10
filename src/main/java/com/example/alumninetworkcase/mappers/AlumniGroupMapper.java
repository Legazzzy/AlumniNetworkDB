package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.services.event.EventService;
import com.example.alumninetworkcase.services.post.PostService;
import com.example.alumninetworkcase.services.student.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class AlumniGroupMapper {

    @Autowired
    StudentService studentService;

    @Autowired
    EventService eventService;

    @Autowired
    PostService postService;

    //Uses a GroupDTO object to attain a Group object
    @Mapping(target = "students", source= "students", qualifiedByName = "studentsToIds")
    @Mapping(target = "events", source= "events", qualifiedByName = "eventsToIds")
    @Mapping(target = "posts", source= "posts", qualifiedByName = "postsToIds")
    public abstract AlumniGroupDTO AlumniGroupToAlumniGroupDTO(AlumniGroup alumniGroup);

    //Uses a Group object to attain a GroupDTO object
    @Mapping(target="students", source= "students", qualifiedByName = "studentIdToStudent")
    @Mapping(target="events", source= "events", qualifiedByName = "eventIdToEvent")
    @Mapping(target="posts", source= "posts", qualifiedByName = "postIdToPost")
    public abstract AlumniGroup AlumniGroupDTOToAlumniGroup (AlumniGroupDTO alumniGroupDTO);

    //Collection of Groups into a collection of GroupDTOs
    public abstract Collection<AlumniGroupDTO> AlumniGroupToAlumniGroupDTO(Collection<AlumniGroup> alumniGroup);

    //Custom mappings
    //Maps id to user
    @Named("studentIdToStudent")
    Student mapIdToStudent(int id) { return studentService.findById(id);}

    //Maps id to event
    @Named("eventIdToEvent")
    Event mapIdToEvent(int id) { return eventService.findById(id);}

    //Maps id to event
    @Named("postIdToPost")
    Post mapIdToPost(int id) { return postService.findById(id);}

    //Maps users to ids
    @Named("studentsToIds")
    Set<Integer> mapUsersToIds(Set<Student> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Map users to ids
    @Named("eventsToIds")
    Set<Integer> mapEventsToIds(Set<Event> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Map users to ids
    @Named("postsToIds")
    Set<Integer> mapPostsToIds(Set<Post> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
