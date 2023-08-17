package com.preproject.stackOverflow.member.mapper;


import com.preproject.stackOverflow.member.dto.MemberDto;
import com.preproject.stackOverflow.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post memberPostDto);
    Member memberPatchToMember(MemberDto.Patch memberPatchDto);
    MemberDto.Response memberToMemberResponseDTO(Member member);

    default MemberDto.ResponseMyPage memberToMyPage(Member member){
        return MemberDto.ResponseMyPage.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .email(member.getEmail())
//                .authorities(member.getRoles())
//                .answers(getAnswerToMember(member.getAnswers()))
//                .questions(getQuestionToMember(member.getQuestions()))
//                .createdTime(member.getCreatedAt())
//                .modifiedTime(member.getUpdatedAt())
                .build();
    }

}


