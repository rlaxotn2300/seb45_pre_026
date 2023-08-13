package com.preproject.stackOverflow.question.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String content;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    @Column
//    private Member member;
//
//    public void setMember(Member member){
//        this.member = member;
//    }
//
//
//
//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<Answer> answerList = new ArrayList<>();



    @Column(nullable = true)

    private int vote;

    @Column(nullable = true)
    private String tag;



    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "QUESTION_TAG_LIST", joinColumns = @JoinColumn(name = "QUESTION_ID"))
    @Column(nullable = true, name = "TAG_LIST")

    private List<String> tags;


    @Column(nullable = false, updatable = false, name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();


    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();





    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_ASKED;





    public enum QuestionStatus {
        QUESTION_ASKED("asked"),
        QUESTION_MODIFIED("modified");

        @Getter
        private String inquireStatus;

        QuestionStatus(String inquireStatus) {
            this.inquireStatus = inquireStatus;
        }
    }
}
