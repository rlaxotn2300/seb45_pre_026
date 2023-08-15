package com.preproject.stackOverflow.answer.repository;

import com.preproject.stackOverflow.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(nativeQuery = true,
            value = "select * from answer where status > 0 and question_id = :questionId"
                    + " order by status asc, vote desc, answer_id desc")
    List<Answer> findAll(@Param("questionId") long questionId);

}

