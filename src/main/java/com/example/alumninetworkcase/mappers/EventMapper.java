package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.EventDTO;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.group.GroupService;
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
public abstract class EventMapper {
    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AlumniGroupService alumniGroupService;

    @Autowired
    protected TopicService topicService;

    @Autowired
    protected PostService postService;

    //Uses a EventDTO object to attain a Event object

    @Mapping(target = "creator_student", source="creator_student.id")
    @Mapping(target = "alumniGroup", source="alumniGroup.id")
    @Mapping(target = "students", source="students", qualifiedByName = "studentsToIds")
    @Mapping(target = "topics", source="topics", qualifiedByName = "topicsToIds")
    @Mapping(target = "posts", source="posts", qualifiedByName = "postsToIds")
    public abstract EventDTO eventToEventDTO(Event event);

    //Uses a Event object to attain a EventDTO object
    @Mapping(target = "creator_student", source="creator_student", qualifiedByName = "studentIdToStudent")
    @Mapping(target = "students", source = "students", qualifiedByName = "studentIdToStudent")
    @Mapping(target = "alumniGroup", source = "alumniGroup", qualifiedByName = "alumniGroupIdToAlumniGroup")
    @Mapping(target = "topics", source = "topics", qualifiedByName = "topicIdToTopic")
    @Mapping(target = "posts", source = "posts", qualifiedByName = "postIdToPost")
    public abstract Event eventDTOToEvent(EventDTO eventDTO);

    //Collection of Events into a collection of EventDTOs
    public abstract Collection<EventDTO> eventToEventDTO(Collection<Event> event);

    //Custom mappings
    //Maps id to user
    @Named("studentIdToStudent")
    Student mapIdTOStudent(int id) { return studentService.findById(id);}

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
