package com.preproject.stackOverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EMAIL_EXISTS(409, "Email already exists"),
    MEMBER_NAME_EXISTS(409, "Name already exists"),
    ANSWER_NOT_FOUND(404, "Answer does not exist"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    TAG_NOT_FOUND(404, "Tag not found"),
    ONLY_AUTHOR(403, "Access denied."),
    UNAUTHORIZED(401,  "Unauthorized");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
