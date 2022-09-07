package com.marketlogic.survey.web.dto.question;

import java.util.List;
import java.util.UUID;

public class QuestionDto {

    private UUID id;
    private String text;
    private List<UUID> answerUUIDs;
    private UUID surveyUUID;

    public QuestionDto() {
    }

    public QuestionDto(UUID id, String text, List<UUID> answerUUIDs, UUID surveyUUID) {
        this.id = id;
        this.text = text;
        this.answerUUIDs = answerUUIDs;
        this.surveyUUID = surveyUUID;
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

    public List<UUID> getAnswerUUIDs() {
        return answerUUIDs;
    }

    public void setAnswerUUIDs(List<UUID> answerUUIDs) {
        this.answerUUIDs = answerUUIDs;
    }

    public UUID getSurveyUUID() {
        return surveyUUID;
    }

    public void setSurveyUUID(UUID surveyUUID) {
        this.surveyUUID = surveyUUID;
    }

}
