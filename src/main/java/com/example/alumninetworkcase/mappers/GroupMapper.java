package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.Event;
import com.example.alumninetworkcase.models.EventDTO.GroupDTO;
import com.example.alumninetworkcase.models.Group;
import com.example.alumninetworkcase.models.Post;
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
public abstract class GroupMapper {

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    PostService postService;

    //Uses a GroupDTO object to attain a Group object
    @Mapping(target = "users", source="users", qualifiedByName = "usersToIds")
    @Mapping(target = "event", source="event", qualifiedByName = "eventsToIds")
    @Mapping(target = "posts", source="posts", qualifiedByName = "postsToIds")
    public abstract GroupDTO groupToGroupDTO(Group group);

    //Uses a Group object to attain a GroupDTO object
    @Mapping(target="users", source="users", qualifiedByName = "userIdToUser")
    @Mapping(target="event", source="event", qualifiedByName = "eventIdToEvent")
    @Mapping(target="posts", source="posts", qualifiedByName = "postIdToPost")
    public abstract Group GroupDTOToGroup (GroupDTO groupDTO);

    //Collection of Groups into a collection of GroupDTOs
    public abstract Collection<GroupDTO> groupToGroupDTO(Collection<Group> group);

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

    //Maps users to ids
    @Named("usersToIds")
    Set<Integer> mapUsersToIds(Set<User> source) {
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
