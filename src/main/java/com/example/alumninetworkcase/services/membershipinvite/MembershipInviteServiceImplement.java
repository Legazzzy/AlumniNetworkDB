package com.example.alumninetworkcase.services.membershipinvite;

import com.example.alumninetworkcase.exceptions.AlumniGroupNotFoundException;
import com.example.alumninetworkcase.exceptions.MembershipInviteNotFoundException;
import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.MembershipInvite;
import com.example.alumninetworkcase.models.Student;
import com.example.alumninetworkcase.repositories.AlumniGroupRepo;
import com.example.alumninetworkcase.repositories.MembershipInviteRepo;
import com.example.alumninetworkcase.repositories.StudentRepo;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Collection;

@Service
public class MembershipInviteServiceImplement implements MembershipInviteService {

    private final MembershipInviteRepo membershipInviteRepo;

    public MembershipInviteServiceImplement(MembershipInviteRepo membershipInviteRepo) {
        this.membershipInviteRepo = membershipInviteRepo;
    }

    @Override
    public MembershipInvite findById(Integer id) {
        return membershipInviteRepo.findById(id)
                .orElseThrow(() -> new MembershipInviteNotFoundException(id));
    }

    @Override
    public Collection<MembershipInvite> findAll() {
        return membershipInviteRepo.findAll();
    }

    @Override
    public MembershipInvite add(MembershipInvite entity) {
        return membershipInviteRepo.save(entity);
    }

    @Override
    public MembershipInvite update(MembershipInvite entity) {
        return membershipInviteRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        membershipInviteRepo.deleteById(id);
    }

    @Override
    public void delete(MembershipInvite entity) {
        membershipInviteRepo.delete(entity);
    }

    @Override
    public boolean exists(Integer id) {
        return membershipInviteRepo.existsById(id);
    }

    @Override
    public MembershipInvite addStudentInvite(MembershipInvite membershipInvite, Student student) {
        membershipInvite.setInvited_student(student);
        return membershipInviteRepo.save(membershipInvite);
    }

    @Override
    public MembershipInvite addGroupInvite(MembershipInvite membershipInvite, AlumniGroup alumniGroup) {
        membershipInvite.setGroup_invite(alumniGroup);
        return membershipInviteRepo.save(membershipInvite);
    }
}
