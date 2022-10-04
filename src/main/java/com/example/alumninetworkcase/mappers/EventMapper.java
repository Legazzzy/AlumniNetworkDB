package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.EventDTO;
import com.example.alumninetworkcase.services.group.GroupService;
import com.example.alumninetworkcase.services.post.PostService;
import com.example.alumninetworkcase.services.topic.TopicService;
import com.example.alumninetworkcase.services.user.UserService;
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
    protected UserService userService;

    @Autowired
    protected GroupService groupService;

    @Autowired
    protected TopicService topicService;

    @Autowired
    protected PostService postService;

    //Uses a EventDTO object to attain a Event object
    @Mapping(target = "users", source="users", qualifiedByName = "usersToIds")
    @Mapping(target = "group", source="group", qualifiedByName = "groupsToIds")
    @Mapping(target = "topic", source="topic", qualifiedByName = "topicsToIds")
    @Mapping(target = "posts", source="posts", qualifiedByName = "postsToIds")
    public abstract EventDTO eventToEventDTO(Event event);

    //Uses a Event object to attain a EventDTO object
    @Mapping(target = "users", source = "users", qualifiedByName = "userIdToUser")
    @Mapping(target = "group", source = "group", qualifiedByName = "groupIdToGroup")
    @Mapping(target = "topic", source = "topic", qualifiedByName = "topicIdToTopic")
    @Mapping(target = "posts", source = "posts", qualifiedByName = "postIdToPost")
    public abstract Event eventDTOToEvent(EventDTO eventDTO);

    //Collection of Events into a collection of EventDTOs
    public abstract Collection<EventDTO> eventToEventDTO(Collection<Event> event);

    //Custom mappings
    //Maps id to user
    @Named("userIdToUser")
    User mapIdToUser(int id) { return userService.findById(id);}

    //Maps id to group
    @Named("groupIdToGroup")
    Group mapIdToGroup(int id) { return groupService.findById(id);}

    //Maps id to topic
    @Named("topicIdToTopic")
    Topic mapIdToTopic(int id) { return topicService.findById(id);}

    //Maps id to event
    @Named("postIdToPost")
    Post mapIdToPost(int id) { return postService.findById(id);}

    //Map users to ids
    @Named("usersToIds")
    Set<Integer> mapUsersToIds(Set<User> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps groups to ids
    @Named("groupsToIds")
    Set<Integer> mapGroupsToIds(Set<Group> source) {
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

    //Map users to ids
    @Named("postsToIds")
    Set<Integer> mapPostsToIds(Set<Post> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

}
