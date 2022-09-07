package com.marketlogic.survey.util.mapper;

import com.marketlogic.survey.data.entity.Answer;
import com.marketlogic.survey.data.entity.Question;
import com.marketlogic.survey.data.entity.Survey;
import com.marketlogic.survey.data.repository.AnswerRepository;
import com.marketlogic.survey.data.repository.SurveyRepository;
import com.marketlogic.survey.web.dto.question.CreateQuestionDto;
import com.marketlogic.survey.web.dto.question.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;

    @Autowired
    public QuestionMapper(AnswerRepository answerRepository,
                          SurveyRepository surveyRepository) {
        this.answerRepository = answerRepository;
        this.surveyRepository = surveyRepository;
    }

    public QuestionDto map(Question question) {
        return new QuestionDto(question.getId(), question.getText(), getAnswerUUIDs(question), getId(question));
    }

    public Question map(CreateQuestionDto createQuestionDto) {
        List<Answer> answers = getAllAnswersById(createQuestionDto);
        Survey survey = getSurveyById(createQuestionDto);
        return new Question(UUID.randomUUID(), createQuestionDto.getText(), answers, survey);
    }

    private UUID getId(Question question) {
        if(question.getSurvey() == null) {
            return null;
        }
        return question.getSurvey().getId();
    }

    private List<UUID> getAnswerUUIDs(Question question) {
        return question.getAnswers().stream().map(Answer::getId).collect(Collectors.toUnmodifiableList());
    }

    private Survey getSurveyById(CreateQuestionDto createQuestionDto) {
        if (createQuestionDto.getSurveyId() == null) {
            return null;
        }
        Optional<Survey> optionalSurvey = surveyRepository.findById(createQuestionDto.getSurveyId());
        return optionalSurvey.orElse(null);
    }

    private List<Answer> getAllAnswersById(CreateQuestionDto createQuestionDto) {
        if (createQuestionDto.getAnswerUUIDs() == null || createQuestionDto.getAnswerUUIDs().size() == 0) {
            return null;
        }
        return answerRepository.findAllById(createQuestionDto.getAnswerUUIDs());
    }

}
