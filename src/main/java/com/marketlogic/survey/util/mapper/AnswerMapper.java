package com.marketlogic.survey.util.mapper;

import com.marketlogic.survey.data.entity.Answer;
import com.marketlogic.survey.data.entity.Question;
import com.marketlogic.survey.service.QuestionService;
import com.marketlogic.survey.web.dto.answer.AnswerDto;
import com.marketlogic.survey.web.dto.answer.CreateAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AnswerMapper {

    private final QuestionService questionService;

    @Autowired
    public AnswerMapper(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Answer map(CreateAnswerDto createAnswerDto) {
        Question question = questionService.getQuestionFromCreateAnswerDto(createAnswerDto);
        return new Answer(null, createAnswerDto.getText(), question);
    }

    public AnswerDto map(Answer answer) {
        return new AnswerDto(answer.getId(), answer.getText(), getQuestionId(answer));
    }

    private UUID getQuestionId(Answer answer) {
        if (answer.getQuestion() == null) {
            return null;
        }
        return answer.getQuestion().getId();
    }

}
