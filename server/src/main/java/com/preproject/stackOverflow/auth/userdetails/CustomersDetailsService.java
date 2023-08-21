package com.preproject.stackOverflow.auth.userdetails;

import com.preproject.stackOverflow.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomersDetailsService    {
    public static Long getAccountId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberDetailsService.MemberDetails customUserDetails = (MemberDetailsService.MemberDetails) principal;
        return customUserDetails.getMemberId();
    }

}