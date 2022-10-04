package com.example.alumninetworkcase.mappers;


import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.UserDTO;
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
public abstract class UserMapper {

    @Autowired
    GroupService groupService;

    @Autowired
    EventService eventService;

    @Autowired
    TopicService topicService;

    @Autowired
    PostService postService;

    //Uses a UserDTO object to attain a User object
    //MIGHT NEED TO REWRITE FOR MANY TO MANY INTERACTIONS
    @Mapping(target = "group", source="group", qualifiedByName = "groupsToIds")
    @Mapping(target = "event", source="event", qualifiedByName = "eventsToIds")
    @Mapping(target = "topic", source="topic", qualifiedByName = "topicsToIds")
    @Mapping(target = "posts", source="posts", qualifiedByName = "postsToIds")
    public abstract UserDTO userToUserDTO(User user);

    //Uses a User object to attain a UserDTO object
    @Mapping(target="group", source="group", qualifiedByName = "groupIdToGroup")
    @Mapping(target="event", source="event", qualifiedByName = "eventIdToEvent")
    @Mapping(target="topic", source="topic", qualifiedByName = "topicIdToTopic")
    @Mapping(target="posts", source="posts", qualifiedByName = "postIdToPost")
    public abstract User userDTOToUser (UserDTO userDTO);

    //Collection of Users into a collection of UserDTOs
    public abstract Collection<UserDTO> userToUserDTO(Collection<User> user);

    //Custom mappings
    //Maps id to group
    @Named("groupIdToGroup")
    Group mapIdToGroup(int id) { return groupService.findById(id);}

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
    @Named("groupsToIds")
    Set<Integer> mapGroupsToIds(Set<Group> source) {
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
