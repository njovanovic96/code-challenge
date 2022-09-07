package com.marketlogic.survey.web.dto.answer;

import java.util.UUID;

public class AnswerDto {

    private UUID id;
    private String text;
    private UUID questionId;

    public AnswerDto() {
    }

    public AnswerDto(UUID id, String text, UUID questionId) {
        this.id = id;
        this.text = text;
        this.questionId = questionId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

}
