package com.preproject.stackOverflow.member.mapper;


import com.preproject.stackOverflow.member.dto.MemberDto;
import com.preproject.stackOverflow.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post memberPostDto);
    Member memberPatchToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponseDTO(Member member);
    List<MemberDto.Response> membersToMemberResponses(List<Member> members);
}