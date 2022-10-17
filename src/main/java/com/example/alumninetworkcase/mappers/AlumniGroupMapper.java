package com.example.alumninetworkcase.mappers;

import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.AlumniGroupDTO;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
import com.example.alumninetworkcase.services.membershipinvite.MembershipInviteService;
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
public abstract class AlumniGroupMapper {

    @Autowired
    StudentService studentService;

    @Autowired
    AlumniEventService alumniEventService;

    @Autowired
    PostService postService;

    @Autowired
    MembershipInviteService membershipInviteService;

    //Uses a AlumniGroupDTO object to attain a AlumniGroup object
    @Mapping(target="alumnigroup_creator_student", source="alumnigroup_creator_student.id")
    @Mapping(target="students", source= "students", qualifiedByName = "studentsToIds")
    @Mapping(target="alumniEvents", source= "alumniEvents", qualifiedByName = "alumniEventsToIds")
    @Mapping(target="posts", source= "posts", qualifiedByName = "postsToIds")
    @Mapping(target="groupMembershipInvites", source="groupMembershipInvites", qualifiedByName = "membershipInvitesToIds")
    public abstract AlumniGroupDTO AlumniGroupToAlumniGroupDTO(AlumniGroup alumniGroup);

    //Uses a AlumniGroup object to attain a AlumniGroupDTO object
    @Mapping(target="alumnigroup_creator_student", source="alumnigroup_creator_student", qualifiedByName = "studentIdToStudent")
    @Mapping(target="students", source= "students", qualifiedByName = "studentIdToStudent")
    @Mapping(target="alumniEvents", source= "alumniEvents", qualifiedByName = "alumniEventIdToAlumniEvent")
    @Mapping(target="posts", source= "posts", qualifiedByName = "postIdToPost")
    @Mapping(target="groupMembershipInvites", source="groupMembershipInvites", qualifiedByName = "membershipInviteIdToMembershipInvite")
    public abstract AlumniGroup AlumniGroupDTOToAlumniGroup (AlumniGroupDTO alumniGroupDTO);

    //Collection of Groups into a collection of GroupDTOs
    public abstract Collection<AlumniGroupDTO> AlumniGroupToAlumniGroupDTO(Collection<AlumniGroup> alumniGroup);

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

    //Maps id to post
    @Named("membershipInviteIdToMembershipInvite")
    MembershipInvite mapIdToMembershipInvite(int id) { return membershipInviteService.findById(id);}

    //Maps users to ids
    @Named("studentsToIds")
    Set<Integer> mapUsersToIds(Set<Student> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
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

    //Maps membership to ids
    @Named("membershipInvitesToIds")
    Set<Integer> mapMembershipInvitesToIds(Set<MembershipInvite> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }
}
