package com.marketlogic.survey.web.dto.survey;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public class CreateSurveyDto {

    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @Size(min = 1)
    private List<UUID> questionUUIDs;

    public CreateSurveyDto() {
    }

    public CreateSurveyDto(String title, List<UUID> questionUUIDs) {
        this.title = title;
        this.questionUUIDs = questionUUIDs;
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
