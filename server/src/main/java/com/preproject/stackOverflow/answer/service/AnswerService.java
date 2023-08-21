package com.preproject.stackOverflow.answer.service;


import com.preproject.stackOverflow.answer.entity.Answer;
import com.preproject.stackOverflow.answer.mapper.AnswerMapper;
import com.preproject.stackOverflow.exception.BusinessLogicException;
import com.preproject.stackOverflow.exception.ExceptionCode;
import com.preproject.stackOverflow.member.entity.Member;
import com.preproject.stackOverflow.member.repository.MemberRepository;
import com.preproject.stackOverflow.member.service.MemberService;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public AnswerService (AnswerRepository answerRepository,
                          AnswerMapper answerMapper,
                          MemberService memberService,
                          MemberRepository memberRepository) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    public Answer createAnswer(Answer answer, long memberId) {
        Member member = memberService.findMember(memberId);

        if (!member.getMemberId().equals(memberId)) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        answer.setMember(member);

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer, long memberId) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        Member member = findAnswer.getMember();
        Member loggedInMember = memberService.findVerifiedMember(memberId);

        if (member.getMemberId() != loggedInMember.getMemberId()) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }

        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));

        findAnswer.setModifiedAt(LocalDateTime.now()); // 글을 수정할 때만 수정 시간 변경

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId) {

        return findVerifiedAnswer(answerId);
    }

    public List<Answer> findAnswers(long questionId) {
        // 삭제된 답변은 보이지 않음

        return answerRepository.findAll(questionId);
    }

    public void deleteAnswer(long answerId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)
        );

        Answer findAnswer = answerRepository.findById(answerId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND)
        );

        if (findAnswer.getMember().getMemberId() != member.getMemberId()) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }

        findAnswer.setAnswerStatus(Answer.AnswerStatus.ANSWER_DELETE);
        answerRepository.delete(findAnswer);
    }

    // 추천
    public void upVoteAnswer(long answerId, long memberId) {
        memberService.findMember(memberId);

        Answer answer = findAnswer(answerId);
        VoteStatus voteStatus = getMemberVoteStatus(answer, memberId);
        long voteCount = answer.getVote();

        if (voteStatus == VoteStatus.NONE) { //투표가 처음이면 +1카운트
            answer.upVotedMemberId.add(memberId);
            voteCount++;
        } else if (voteStatus == VoteStatus.ALREADY_UP_VOTED) {
            throw new BusinessLogicException(ExceptionCode.ALREADY_UP_VOTED);
        } else if (voteStatus == VoteStatus.ALREADY_DOWN_VOTED) {
            answer.downVotedMemberId.remove(memberId);
            voteCount++;
        }
        answer.setVote(voteCount);

    }

    // 비추천
    public void downVoteAnswer(long answerId, long memberId) {
        memberService.findMember(memberId);

        Answer answer = findAnswer(answerId);
        VoteStatus voteStatus = getMemberVoteStatus(answer, memberId);
        long voteCount = answer.getVote();

        if (voteStatus == VoteStatus.NONE) {
            answer.downVotedMemberId.add(memberId);
            voteCount--;
        } else if (voteStatus == VoteStatus.ALREADY_UP_VOTED) {
            answer.upVotedMemberId.remove(memberId);
            voteCount--;
        } else if (voteStatus == VoteStatus.ALREADY_DOWN_VOTED) {
            throw new BusinessLogicException(ExceptionCode.ALREADY_DOWN_VOTED);
        }
        answer.setVote(voteCount);
    }

    public VoteStatus getMemberVoteStatus(Answer answer, long memberId) {
        if (answer.getUpVotedMemberId().contains(memberId)) {
            return VoteStatus.ALREADY_UP_VOTED;
        } else if (answer.getDownVotedMemberId().contains(memberId)) {
            return VoteStatus.ALREADY_DOWN_VOTED;
        } else {
            return VoteStatus.NONE;
        }
    }

    public long getVote(long answerId) {

        long voteCount = findAnswer(answerId).getVote();
        return voteCount;
    }

    public enum VoteStatus {
        ALREADY_UP_VOTED(1, "already upVoted"),
        NONE(2, "none"),
        ALREADY_DOWN_VOTED(3, "already downVoted");

        @Getter
        private int status;

        @Getter
        private String message;

        VoteStatus(int status, String message) {
            this.status = status;
            this.message = message;
        }
    }

    private Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswer;
    }
}
