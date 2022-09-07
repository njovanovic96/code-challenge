package com.marketlogic.survey.web.dto.survey;

import java.util.List;
import java.util.UUID;

public class SurveyDto {

    private UUID id;
    private String title;
    private List<UUID> questionUUIDs;

    public SurveyDto() {
    }

    public SurveyDto(UUID id, String title, List<UUID> questionUUIDs) {
        this.id = id;
        this.title = title;
        this.questionUUIDs = questionUUIDs;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UUID> getQuestionUUIDs() {
        return questionUUIDs;
    }

    public void setQuestionUUIDs(List<UUID> questionUUIDs) {
        this.questionUUIDs = questionUUIDs;
    }

}
