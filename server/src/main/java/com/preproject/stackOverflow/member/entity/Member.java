package com.preproject.stackOverflow.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 13, nullable = false)
    private String phonenumber;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Question> questions;

    @JsonManagedReference
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Answer> answers;

    /*
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }
     */

    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}