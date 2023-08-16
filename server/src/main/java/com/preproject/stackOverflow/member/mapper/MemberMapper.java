package com.preproject.stackOverflow.member.mapper;


import com.preproject.stackOverflow.member.dto.MemberDTO;
import com.preproject.stackOverflow.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberDTO.Post memberPostDto);
    Member memberPatchToMember(MemberDTO.Patch memberPatchDto);
    MemberDTO.Response memberToMemberResponseDTO(Member member);

    default MemberDTO.ResponseMyPage memberToMyPage(Member member){
        return MemberDTO.ResponseMyPage.builder()
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


