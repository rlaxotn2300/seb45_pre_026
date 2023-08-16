package com.preproject.stackOverflow.member.repository;

import com.preproject.stackOverflow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Member findByMemberId(long memberId);
}


