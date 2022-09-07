package com.marketlogic.survey.web.dto.answer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateAnswerDto {

    @NotNull
    @NotBlank
    private String text;
    private UUID questionId;

    public CreateAnswerDto() {
    }

    public CreateAnswerDto(String text, UUID questionId) {
        this.text = text;
        this.questionId = questionId;
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

    public void setQuestionId(UUID question) {
        this.questionId = question;
    }

}
