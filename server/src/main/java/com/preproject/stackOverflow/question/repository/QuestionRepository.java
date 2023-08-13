package com.preproject.stackOverflow.question.repository;

import com.preproject.stackOverflow.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> deleteByTagList(String tag);

    Page<Question>findByTagContainingOrderByTagDesc(Pageable pageable, String tag);




}