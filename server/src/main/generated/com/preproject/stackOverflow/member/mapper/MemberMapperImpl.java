package com.preproject.stackOverflow.member.mapper;

import com.preproject.stackOverflow.member.dto.MemberDTO.Patch;
import com.preproject.stackOverflow.member.dto.MemberDTO.Post;
import com.preproject.stackOverflow.member.dto.MemberDTO.Response;
import com.preproject.stackOverflow.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T20:36:05+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(Post memberPostDto) {
        if ( memberPostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setPassword( memberPostDto.getPassword() );
        member.setName( memberPostDto.getName() );
        member.setEmail( memberPostDto.getEmail() );

        return member;
    }

    @Override
    public Member memberPatchToMember(Patch memberPatchDto) {
        if ( memberPatchDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( memberPatchDto.getMemberId() );
        member.setPassword( memberPatchDto.getPassword() );
        member.setName( memberPatchDto.getName() );
        member.setEmail( memberPatchDto.getEmail() );

        return member;
    }

    @Override
    public Response memberToMemberResponseDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        Response response = new Response();

        response.setMemberId( member.getMemberId() );
        response.setEmail( member.getEmail() );
        response.setName( member.getName() );

        return response;
    }
}
