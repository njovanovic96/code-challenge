package com.marketlogic.survey.service;

import com.marketlogic.survey.data.entity.Question;
import com.marketlogic.survey.data.entity.Survey;
import com.marketlogic.survey.data.repository.SurveyRepository;
import com.marketlogic.survey.util.mapper.SurveyMapper;
import com.marketlogic.survey.web.dto.survey.CreateSurveyDto;
import com.marketlogic.survey.web.dto.survey.SurveyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository, SurveyMapper surveyMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyMapper = surveyMapper;
    }

    @Transactional
    public List<SurveyDto> getSurveys() {
        return surveyRepository.findAll().stream()
                .map(surveyMapper::map)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public SurveyDto createSurvey(CreateSurveyDto createSurveyDto) {
        Survey survey = surveyMapper.map(createSurveyDto);
        addSurveyReference(survey, survey.getQuestions());
        Survey persisted = surveyRepository.save(survey);
        return surveyMapper.map(persisted);
    }

    private void addSurveyReference(Survey survey, List<Question> questions) {
        questions.forEach(question -> {
            addSurveyReferenceToQuestion(survey, question);
        });
    }

    private void addSurveyReferenceToQuestion(Survey survey, Question question) {
        question.setSurvey(survey);
    }

}
