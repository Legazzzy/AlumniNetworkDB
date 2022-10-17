package com.example.alumninetworkcase.mappers;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.EventDTO.MembershipInviteDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.EventDTO.TopicDTO;
import com.example.alumninetworkcase.models.MembershipInvite;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.models.Topic;
import com.example.alumninetworkcase.services.alumnigroup.AlumniGroupService;
import com.example.alumninetworkcase.services.student.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MembershipInviteMapper {

    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AlumniGroupService alumniGroupService;

    @Mapping(target = "invited_student", source="invited_student.id")
    @Mapping(target = "group_invite", source="group_invite.id")
    public abstract MembershipInviteDTO membershipInviteDTOToMembershipInvite(MembershipInvite membershipInvite);

    @Mapping(target = "invited_student", source="invited_student", qualifiedByName = "studentIdToStudent")
    @Mapping(target = "group_invite", source="group_invite", qualifiedByName = "alumniGroupIdToAlumniGroup")
    public abstract MembershipInvite membershipInviteToMembershipInviteDTO(MembershipInviteDTO membershipInviteDTO);

    //Collection of Users into a collection of UserDTOs
    public abstract Collection<MembershipInviteDTO> membershipInviteToMembershipInviteDTO(Collection<MembershipInvite> membershipInvites);


    //Custom mappings
    //Maps id to user
    @Named("studentIdToStudent")
    Student mapIdToStudent(int id) { return studentService.findById(id);}

    //Map users to ids
    @Named("studentsToIds")
    Set<Integer> mapStudentsToIds(Set<Student> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

    //Maps id to group
    @Named("alumniGroupIdToAlumniGroup")
    AlumniGroup mapIdToAlumniGroup(int id) { return alumniGroupService.findById(id);}

    //Maps groups to ids
    @Named("alumniGroupsToIds")
    Set<Integer> mapAlumniGroupsToIds(Set<AlumniGroup> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }
}
