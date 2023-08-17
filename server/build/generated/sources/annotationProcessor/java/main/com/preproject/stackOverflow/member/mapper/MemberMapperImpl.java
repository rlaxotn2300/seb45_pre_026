package com.preproject.stackOverflow.member.mapper;

import com.preproject.stackOverflow.member.dto.MemberDto.Patch;
import com.preproject.stackOverflow.member.dto.MemberDto.Post;
import com.preproject.stackOverflow.member.dto.MemberDto.Response;
import com.preproject.stackOverflow.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-17T19:43:16+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member memberPostToMember(Post memberPostDto) {
        if ( memberPostDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( memberPostDto.getEmail() );
        member.setPassword( memberPostDto.getPassword() );
        member.setName( memberPostDto.getName() );

        return member;
    }

    @Override
    public Member memberPatchToMember(Patch memberPatchDto) {
        if ( memberPatchDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( memberPatchDto.getMemberId() );
        member.setEmail( memberPatchDto.getEmail() );
        member.setPassword( memberPatchDto.getPassword() );
        member.setName( memberPatchDto.getName() );

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

    @Override
    public List<Response> membersToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<Response> list = new ArrayList<Response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponseDTO( member ) );
        }

        return list;
    }
}
