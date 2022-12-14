package com.example.alumninetworkcase.mappers;


import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.membershipinvite.MembershipInviteService;
import com.example.alumninetworkcase.services.post.PostService;
import com.example.alumninetworkcase.services.rsvp.RSVPService;
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
    protected AlumniEventService alumniEventService;

    @Autowired
    protected RSVPService rsvpService;

    @Autowired
    protected TopicService topicService;

    @Autowired
    protected PostService postService;

    @Autowired
    protected MembershipInviteService membershipInviteService;

    //Uses a UserDTO object to attain a User object
    //MIGHT NEED TO REWRITE FOR MANY TO MANY INTERACTIONS
    @Mapping(target="alumniGroups", source= "alumniGroups", qualifiedByName = "alumniGroupsToIds")
    @Mapping(target="alumniEvents", source= "alumniEvents", qualifiedByName = "alumniEventsToIds")
    @Mapping(target="createdAlumniEvents", source= "createdAlumniEvents", qualifiedByName = "alumniEventsToIds")
    @Mapping(target="ownedAlumniGroups", source= "ownedAlumniGroups", qualifiedByName = "alumniGroupsToIds")
    @Mapping(target="topics", source= "topics", qualifiedByName = "topicsToIds")
    @Mapping(target="posts", source= "posts", qualifiedByName = "postsToIds")
    @Mapping(target="studentMembershipInvites", source="studentMembershipInvites", qualifiedByName = "membershipInvitesToIds")
    @Mapping(target="studentRSVPs", source="studentRSVPs", qualifiedByName = "RSVPSToIds")
    public abstract StudentDTO studentToStudentDTO(Student student);

    //Uses a User object to attain a UserDTO object
    @Mapping(target="alumniGroups", source= "alumniGroups", qualifiedByName = "alumniGroupIdToAlumniGroup")
    @Mapping(target="alumniEvents", source= "alumniEvents", qualifiedByName = "alumniEventIdToAlumniEvent")
    @Mapping(target="createdAlumniEvents", source= "createdAlumniEvents", qualifiedByName = "alumniEventIdToAlumniEvent")
    @Mapping(target="ownedAlumniGroups", source= "ownedAlumniGroups", qualifiedByName = "alumniGroupIdToAlumniGroup")
    @Mapping(target="topics", source= "topics", qualifiedByName = "topicIdToTopic")
    @Mapping(target="posts", source= "posts", qualifiedByName = "postIdToPost")
    @Mapping(target="studentMembershipInvites", source="studentMembershipInvites", qualifiedByName = "membershipInviteIdToMembershipInvite")
    @Mapping(target="studentRSVPs", source="studentRSVPs", qualifiedByName = "RSVPIdToRSVP")
    public abstract Student studentDTOToStudent (StudentDTO student);

    //Collection of Users into a collection of UserDTOs
    public abstract Collection<StudentDTO> studentToStudentDTO(Collection<Student> student);

    //Custom mappings
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

    //Maps id to membership
    @Named("membershipInviteIdToMembershipInvite")
    MembershipInvite mapIdToMembershipInvite(int id) { return membershipInviteService.findById(id);}

    //Maps id to RSVP
    @Named("RSVPIdToRSVP")
    RSVP mapIdToRSVP(int id) { return rsvpService.findById(id);}

    //Maps groups to ids
    @Named("alumniGroupsToIds")
    Set<Integer> mapAlumniGroupsToIds(Set<AlumniGroup> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps events to ids
    @Named("alumniEventsToIds")
    Set<Integer> mapAlumniEventsToIds(Set<AlumniEvent> source) {
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

    //Maps membership to ids
    @Named("membershipInvitesToIds")
    Set<Integer> mapMembershipInvitesToIds(Set<MembershipInvite> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps RSVP to ids
    @Named("RSVPSToIds")
    Set<Integer> mapRSVPToIds(Set<RSVP> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }
}
