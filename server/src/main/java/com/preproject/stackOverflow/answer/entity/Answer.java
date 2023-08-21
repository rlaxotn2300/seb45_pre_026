package com.preproject.stackOverflow.answer.entity;


import com.preproject.stackOverflow.member.entity.Member;
import com.preproject.stackOverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false, name = "status")
    private AnswerStatus answerStatus = AnswerStatus.ANSWER_NORMAL;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private long vote;

    @CreatedDate
    @Column(nullable = false, updatable = false,name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ElementCollection
    public List<Long> upVotedMemberId = new ArrayList<>();

    @ElementCollection
    public List<Long> downVotedMemberId = new ArrayList<>();


    public enum AnswerStatus {
        ANSWER_DELETE(0, "삭제된 답변"),
        ANSWER_BEST(1, "답변 채택"),
        ANSWER_NORMAL(2, "일반 답변");

        @Getter
        private int statusNumber;

        @Getter
        private String statusDescription;

        AnswerStatus(int statusNumber, String statusDescription) {
            this.statusNumber = statusNumber;
            this.statusDescription = statusDescription;
        }
    }


    public Answer(AnswerStatus answerStatus, String content) {

        this.answerStatus = answerStatus;
        this.content = content;
    }

    public  Answer(AnswerStatus answerStatus,
                   Member member,
                   Question question, String content) {
        this.member = member;
        this.answerStatus = answerStatus;
        this.question = question;
        this.content =  content;
    }

    public Answer(Long answerId,
                  Member member,
                  AnswerStatus answerStatus, Question question,
                  String content, long vote, LocalDateTime createdAt,
                  LocalDateTime modifiedAt) {
        this.answerId =  answerId;
        this.member =  member;
        this.question = question;
        this.content =content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public void setVote(long vote) {
        this.vote = vote;
    }


//    public void setQuestion(Question question) {
//        this.question = question;
//
//        if (!this.question.getAnswerList().contains(this)) {
//            this.question.setAnswers(this);
//        }
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//
//        if (!this.member.getAnswers().contains(this)) {
//            this.member.setAnswers(this);
//        }
//    }
}