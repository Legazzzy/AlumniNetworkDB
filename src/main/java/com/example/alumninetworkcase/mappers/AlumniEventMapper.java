package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.AlumniEventDTO;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.post.PostService;
import com.example.alumninetworkcase.services.student.StudentService;
import com.example.alumninetworkcase.services.topic.TopicService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AlumniEventMapper {
    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AlumniGroupService alumniGroupService;

    @Autowired
    protected TopicService topicService;

    @Autowired
    protected PostService postService;

    //Uses a AlumniEventDTO object to attain a AlumniEvent object
    @Mapping(target = "creator_student", source="creator_student.id")
    @Mapping(target = "alumniGroup", source="alumniGroup.id")
    @Mapping(target = "students", source="students", qualifiedByName = "studentsToIds")
    @Mapping(target = "topics", source="topics", qualifiedByName = "topicsToIds")
    @Mapping(target = "posts", source="posts", qualifiedByName = "postsToIds")
    public abstract AlumniEventDTO alumniEventToAlumniEventDTO(AlumniEvent alumniEvent);

    //Uses a AlumniEvent object to attain a AlumniEventDTO object
    @Mapping(target = "creator_student", source="creator_student", qualifiedByName = "studentIdToStudent")
    @Mapping(target = "students", source = "students", qualifiedByName = "studentIdToStudent")
    @Mapping(target = "alumniGroup", source = "alumniGroup", qualifiedByName = "alumniGroupIdToAlumniGroup")
    @Mapping(target = "topics", source = "topics", qualifiedByName = "topicIdToTopic")
    @Mapping(target = "posts", source = "posts", qualifiedByName = "postIdToPost")
    public abstract AlumniEvent alumniEventDTOToAlumniEvent(AlumniEventDTO alumniEventDTO);

    //Collection of Events into a collection of EventDTOs
    public abstract Collection<AlumniEventDTO> alumniEventToAlumniEventDTO(Collection<AlumniEvent> alumniEvent);

    //Custom mappings
    //Maps id to user
    @Named("studentIdToStudent")
    Student mapIdToStudent(int id) { return studentService.findById(id);}

    //Maps id to group
    @Named("alumniGroupIdToAlumniGroup")
    AlumniGroup mapIdToAlumniGroup(int id) { return alumniGroupService.findById(id);}

    //Maps id to topic
    @Named("topicIdToTopic")
    Topic mapIdToTopic(int id) { return topicService.findById(id);}

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

    //Maps groups to ids
    @Named("alumniGroupsToIds")
    Set<Integer> mapAlumniGroupsToIds(Set<AlumniGroup> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps topics to ids
    @Named("topicsToIds")
    Set<Integer> mapTopicsToIds(Set<Topic> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
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
