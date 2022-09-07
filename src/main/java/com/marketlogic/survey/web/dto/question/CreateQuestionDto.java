package com.marketlogic.survey.web.dto.question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public class CreateQuestionDto {

    @NotNull
    @NotBlank
    private String text;
    @NotNull
    @Size(min = 2)
    private List<UUID> answerUUIDs;
    private UUID surveyId;

    public CreateQuestionDto() {
    }

    public CreateQuestionDto(String text, List<UUID> answerUUIDs, UUID surveyId) {
        this.text = text;
        this.answerUUIDs = answerUUIDs;
        this.surveyId = surveyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<UUID> getAnswerUUIDs() {
        return answerUUIDs;
    }

    public void setAnswerUUIDs(List<UUID> answerUUIDs) {
        this.answerUUIDs = answerUUIDs;
    }

    public UUID getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(UUID surveyId) {
        this.surveyId = surveyId;
    }

}
