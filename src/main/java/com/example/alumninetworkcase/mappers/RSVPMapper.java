package com.example.alumninetworkcase.mappers;
import com.example.alumninetworkcase.models.*;
import com.example.alumninetworkcase.models.EventDTO.MembershipInviteDTO;
import com.example.alumninetworkcase.models.EventDTO.RSVPDTO;
import com.example.alumninetworkcase.models.EventDTO.StudentDTO;
import com.example.alumninetworkcase.models.EventDTO.TopicDTO;
import com.example.alumninetworkcase.services.alumnievent.AlumniEventService;
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
public abstract class RSVPMapper {

    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AlumniEventService alumniEventService;

    @Mapping(target = "event_student_invitation", source="event_student_invitation.id")
    @Mapping(target = "event_invite", source="event_invite.id")
    public abstract RSVPDTO RSVPToRVSPDTO(RSVP rsvp);

    @Mapping(target = "event_student_invitation", source="event_student_invitation", qualifiedByName = "studentIdToStudent")
    @Mapping(target = "event_invite", source="event_invite", qualifiedByName = "alumniEventIdToAlumniEvent")
    public abstract RSVP RSVPDTOToRVSP(RSVPDTO rsvpdto);

    //Collection of Users into a collection of UserDTOs
    public abstract Collection<RSVPDTO> RSVPToRVSPDTO(Collection<RSVP> rsvps);


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

    //Maps id to event
    @Named("alumniEventIdToAlumniEvent")
    AlumniEvent mapIdToAlumniEvent(int id) { return alumniEventService.findById(id);}

    //Maps groups to ids
    @Named("alumniEventsToIds")
    Set<Integer> mapAlumniEventsToIds(Set<AlumniEvent> source) {
        if (source == null)
            return null;
        return source.stream().map(s -> s.getId()).collect(Collectors.toSet());
    }
}
