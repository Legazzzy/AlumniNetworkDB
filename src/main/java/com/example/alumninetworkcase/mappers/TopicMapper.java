package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.TopicDTO;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
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
public abstract class TopicMapper {

    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AlumniEventService alumniEventService;

    @Autowired
    protected PostService postService;

    //Many to many?
    @Mapping(target = "students", source="students", qualifiedByName = "studentsToIds")
    @Mapping(target = "alumniEvents", source="alumniEvents", qualifiedByName = "alumniEventsToIds")
    @Mapping(target = "posts", source="posts", qualifiedByName = "postsToIds")
    public abstract TopicDTO topicToTopicDTO(Topic topic);


    @Mapping(target = "students", source = "students", qualifiedByName = "studentIdToStudent")
    @Mapping(target = "alumniEvents", source = "alumniEvents", qualifiedByName = "alumniEventIdToAlumniEvent")
    @Mapping(target = "posts", source = "posts", qualifiedByName = "postIdToPost")
    public abstract Topic topicDTOToTopic(TopicDTO topic);

    //Collection of Topics into a collection of TopicDTOs
    public abstract Collection<TopicDTO> topicToTopicDTO(Collection<Topic> topic);

    //Custom mappings
    //Maps id to user
    @Named("studentIdToStudent")
    Student mapIdToStudent(int id) { return studentService.findById(id);}

    //Maps id to event
    @Named("alumniEventIdToAlumniEvent")
    AlumniEvent mapIdToAlumniEvent(int id) { return alumniEventService.findById(id);}

    //Maps id to event
    @Named("postIdToPost")
    Post mapIdToPost(int id) { return postService.findById(id);}

    //Map users to ids
    @Named("studentsToIds")
    Set<Integer> mapStudentsToIds(Set<Student> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Map users to ids
    @Named("alumniEventsToIds")
    Set<Integer> mapAlumniEventsToIds(Set<AlumniEvent> source) {
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