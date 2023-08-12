package com.preproject.stackOverflow.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Getter
public class MultiResponseDto <T>{
    private List<T> data;
    private PageInfo pageInfo;


    public MultiResponseDto(List<T> data, PageInfo pageInfo){
        this.data = data;
        this.pageInfo = pageInfo;
    }
}
