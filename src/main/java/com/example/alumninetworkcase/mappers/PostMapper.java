package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.PostDTO;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
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
public abstract class PostMapper {

    @Autowired
    StudentService studentService;

    @Autowired
    AlumniEventService alumniEventService;

    @Autowired
    AlumniGroupService alumniGroupService;

    @Autowired
    TopicService topicService;

    @Autowired
    PostService postService;

    //Uses a PostDTO object to attain a Post object
    @Mapping(target = "sender_student", source = "sender_student.id")
    @Mapping(target = "target_student", source="target_student.id")
    @Mapping(target = "target_alumniGroup", source="target_alumniGroup.id")
    @Mapping(target = "target_alumniEvent", source="target_alumniEvent.id")
    @Mapping(target = "target_topic", source="target_topic.id")
    @Mapping(target = "reply_post", source="reply_post.id")
    @Mapping(target = "replies", source="replies", qualifiedByName = "postsToIds")
    public abstract PostDTO postToPostDTO(Post post);

    //Uses a Post object to attain a PostDTO object
    @Mapping(target="sender_student", source="sender_student", qualifiedByName = "studentIdToStudent")
    @Mapping(target="target_student", source="target_student", qualifiedByName = "studentIdToStudent")
    @Mapping(target="target_alumniGroup", source="target_alumniGroup", qualifiedByName = "alumniGroupIdToAlumniGroup")
    @Mapping(target="target_alumniEvent", source="target_alumniEvent", qualifiedByName = "alumniEventIdToAlumniEvent")
    @Mapping(target="target_topic", source="target_topic", qualifiedByName = "topicIdToTopic")
    @Mapping(target="reply_post", source="reply_post", qualifiedByName = "postIdToPost")
    @Mapping(target="replies", source="replies", qualifiedByName = "postIdToPost")
    public abstract Post PostDTOToPost (PostDTO postDTO);

    //Collection of Posts into a collection of PostDTOs
    public abstract Collection<PostDTO> postToPostDTO(Collection<Post> post);

    //Custom mappings
    //Maps id to user
    @Named("studentIdToStudent")
    Student mapIdToStudent(String id) { return studentService.findById(id);}

    //Maps id to group
    @Named("alumniGroupIdToAlumniGroup")
    AlumniGroup mapIdToAlumniGroup(int id) { return alumniGroupService.findById(id);}

    //Maps id to event
    @Named("alumniEventIdToAlumniEvent")
    AlumniEvent mapIdToAlumniEvent(int id) { return alumniEventService.findById(id);}

    //Maps id to topic
    @Named("topicIdToTopic")
    Topic mapIdToTopic(int id) { return topicService.findById(id);}

    //Maps id to post
    @Named("postIdToPost")
    Post mapIdToPost(int id) { return postService.findById(id);}


    //Maps users to ids
    @Named("studentsToIds")
    Set<String> mapStudentsToIds(Set<Student> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps groups to ids
    @Named("alumniGroupsToIds")
    Set<Integer> mapAlumniGroupsToIds(Set<AlumniGroup> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps events to ids
    @Named("alumniEventsToAlumniIds")
    Set<Integer> mapAlumniEventsToIds(Set<AlumniEvent> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps topics to ids
    @Named("topicToIds")
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
