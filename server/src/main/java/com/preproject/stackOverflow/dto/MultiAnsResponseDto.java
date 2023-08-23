package com.preproject.stackOverflow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MultiAnsResponseDto <T> {
   private List<T> vote;
}
