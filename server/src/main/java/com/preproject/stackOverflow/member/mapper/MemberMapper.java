package com.preproject.stackOverflow.member.mapper;


import com.preproject.stackOverflow.member.dto.MemberDto;
import com.preproject.stackOverflow.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post memberPostDto);
    Member memberPatchToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponseDTO(Member member);

}


