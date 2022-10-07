package com.example.alumninetworkcase.mappers;


import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.event.EventService;
import com.example.alumninetworkcase.services.group.GroupService;
import com.example.alumninetworkcase.services.post.PostService;
import com.example.alumninetworkcase.services.topic.TopicService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Autowired
    protected AlumniGroupService alumniGroupService;

    @Autowired
    protected EventService eventService;

    @Autowired
    protected TopicService topicService;

    @Autowired
    protected PostService postService;

    //Uses a UserDTO object to attain a User object
    //MIGHT NEED TO REWRITE FOR MANY TO MANY INTERACTIONS
    @Mapping(target = "alumniGroups", source= "alumniGroups", qualifiedByName = "alumniGroupsToIds")
    @Mapping(target = "events", source= "events", qualifiedByName = "eventsToIds")
    @Mapping(target = "createdEvents", source= "createdEvents", qualifiedByName = "eventsToIds")
    @Mapping(target = "topics", source= "topics", qualifiedByName = "topicsToIds")
    @Mapping(target = "posts", source= "posts", qualifiedByName = "postsToIds")
    public abstract StudentDTO studentToStudentDTO(Student student);

    //Uses a User object to attain a UserDTO object
    @Mapping(target="alumniGroups", source= "alumniGroups", qualifiedByName = "alumniGroupIdToAlumniGroup")
    @Mapping(target="events", source= "events", qualifiedByName = "eventIdToEvent")
    @Mapping(target = "createdEvents", source= "createdEvents", qualifiedByName = "eventIdToEvent")
    @Mapping(target="topics", source= "topics", qualifiedByName = "topicIdToTopic")
    @Mapping(target="posts", source= "posts", qualifiedByName = "postIdToPost")
    public abstract Student studentDTOToStudent (StudentDTO student);

    //Collection of Users into a collection of UserDTOs
    public abstract Collection<StudentDTO> studentToStudentDTO(Collection<Student> student);

    //Custom mappings
    //Maps id to group
    @Named("alumniGroupIdToAlumniGroup")
    AlumniGroup mapIdToAlumniGroup(int id) { return alumniGroupService.findById(id);}

    //Maps id to event
    @Named("eventIdToEvent")
    Event mapIdToEvent(int id) { return eventService.findById(id);}

    //Maps id to topic
    @Named("topicIdToTopic")
    Topic mapIdToTopic(int id) { return topicService.findById(id);}

    //Maps id to post
    @Named("postIdToPost")
    Post mapIdToPost(int id) { return postService.findById(id);}

    //Maps groups to ids
    @Named("alumniGroupsToIds")
    Set<Integer> mapAlumniGroupsToIds(Set<AlumniGroup> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps events to ids
    @Named("eventsToIds")
    Set<Integer> mapEventsToIds(Set<Event> source) {
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

    //Maps posts to ids
    @Named("postsToIds")
    Set<Integer> mapPostsToIds(Set<Post> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }
}
