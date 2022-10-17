package com.example.alumninetworkcase.repositories;

import com.example.alumninetworkcase.models.AlumniGroup;
import com.example.alumninetworkcase.models.MembershipInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipInviteRepo extends JpaRepository<MembershipInvite, Integer> {
}
