package com.preproject.stackOverflow.question.repository;

import com.preproject.stackOverflow.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> deleteByTagList(long questionId);

    Page<Question>findAllByTagContaining(String tag, PageRequest page);



}