package com.example.alumninetworkcase.services.membershipinvite;

import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.MembershipInvite;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.services.CrudService;

public interface MembershipInviteService extends CrudService<MembershipInvite, Integer> {

    MembershipInvite addStudentInvite(MembershipInvite membershipInvite, Student student);

    MembershipInvite addGroupInvite(MembershipInvite membershipInvite, AlumniGroup alumniGroup);

}
