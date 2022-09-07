package com.marketlogic.survey.util.mapper;

import com.marketlogic.survey.data.entity.Question;
import com.marketlogic.survey.data.entity.Survey;
import com.marketlogic.survey.service.QuestionService;
import com.marketlogic.survey.web.dto.survey.CreateSurveyDto;
import com.marketlogic.survey.web.dto.survey.SurveyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SurveyMapper {

    private final QuestionService questionService;

    @Autowired
    public SurveyMapper(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Survey map(CreateSurveyDto createSurveyDto) {
        List<Question> questions = questionService.getAllQuestionsById(createSurveyDto);
        return new Survey(UUID.randomUUID(), createSurveyDto.getTitle(), questions);
    }

    public SurveyDto map(Survey persisted) {
        return new SurveyDto(persisted.getId(), persisted.getTitle(), getQuestionUUIDs(persisted));
    }

    private List<UUID> getQuestionUUIDs(Survey persisted) {
        return persisted.getQuestions().stream()
                .map(Question::getId)
                .collect(Collectors.toUnmodifiableList());
    }

}
