package com.preproject.stackOverflow.auth.userdetails;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CustomersDetailsService    {
    public static Long getAccountId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberDetailsService.MemberDetails customUserDetails = (MemberDetailsService.MemberDetails) principal;
        return customUserDetails.getMemberId();
    }

}

