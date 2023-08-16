package com.preproject.stackOverflow.member.service;

import com.preproject.stackOverflow.auth.utils.CustomAuthorityUtils;
import com.preproject.stackOverflow.exception.BusinessLogicException;
import com.preproject.stackOverflow.exception.ExceptionCode;
import com.preproject.stackOverflow.member.entity.Member;
import com.preproject.stackOverflow.member.mapper.MemberMapper;
import com.preproject.stackOverflow.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;
    private final CustomAuthorityUtils authorityUtils;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, MemberMapper memberMapper, CustomAuthorityUtils authorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.memberMapper = memberMapper;
        this.authorityUtils = authorityUtils;
    }


    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(findMember::setName);

        Optional.ofNullable((member.getPassword()))
                .ifPresent(findMember::setPassword);

        return memberRepository.save(findMember);
    }

    public void deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);
        memberRepository.delete(findMember);
    }

    @Transactional(readOnly = true)
    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        return optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

//    private Member findVerifiedMember(String email) {
//        Optional<Member> optionalMember = memberRepository.findByEmail(email);
//        return optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
//    }

    private void verifyExistsEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EMAIL_EXISTS);
    }
}


