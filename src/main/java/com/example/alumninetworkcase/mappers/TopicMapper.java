package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.Event;
import com.example.alumninetworkcase.models.EventDTO.TopicDTO;
import com.example.alumninetworkcase.models.Post;
import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.models.User;
import com.example.alumninetworkcase.services.event.EventService;
import com.example.alumninetworkcase.services.post.PostService;
import com.example.alumninetworkcase.services.user.UserService;
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
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    PostService postService;

    //Many to many?
    @Mapping(target = "users", source="users", qualifiedByName = "usersToIds")
    @Mapping(target = "event", source="event", qualifiedByName = "eventsToIds")
    @Mapping(target = "posts", source="posts", qualifiedByName = "postsToIds")
    public abstract TopicDTO topicToTopicDTO(Topic topic);


    @Mapping(target = "users", source = "users", qualifiedByName = "userIdToUser")
    @Mapping(target= "event", source = "event", qualifiedByName = "eventIdToEvent")
    @Mapping(target = "posts", source = "posts", qualifiedByName = "postIdToPost")
    public abstract Topic topicDTOToTopic(TopicDTO topic);

    //Collection of Topics into a collection of TopicDTOs
    public abstract Collection<TopicDTO> topicToTopicDTO(Collection<Topic> topic);

    //Custom mappings
    //Maps id to user
    @Named("userIdToUser")
    User mapIdToUser(int id) { return userService.findById(id);}

    //Maps id to event
    @Named("eventIdToEvent")
    Event mapIdToEvent(int id) { return eventService.findById(id);}

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