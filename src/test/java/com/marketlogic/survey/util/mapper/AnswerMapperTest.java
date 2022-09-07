package com.marketlogic.survey.util.mapper;

import com.marketlogic.survey.data.entity.Answer;
import com.marketlogic.survey.data.entity.Question;
import com.marketlogic.survey.service.QuestionService;
import com.marketlogic.survey.web.dto.answer.AnswerDto;
import com.marketlogic.survey.web.dto.answer.CreateAnswerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerMapperTest {

    @InjectMocks
    AnswerMapper answerMapper;

    @Mock
    QuestionService questionService;

    private final UUID questionUUID = UUID.fromString("bf3a4a94-5642-4190-8caa-8f5192ec0c3d");
    private final Question question = new Question(questionUUID, "Test", null, null);

    @Test
    public void testMapCreateAnswer() {
        CreateAnswerDto mockedAnswer = new CreateAnswerDto("text 1", questionUUID);
        when(questionService.getQuestionFromCreateAnswerDto(any(CreateAnswerDto.class)))
                .thenReturn(question);
        Answer answer = answerMapper.map(mockedAnswer);
        assertThat(answer.getId()).isNull();
        assertThat(answer.getQuestion().getId()).isEqualTo(mockedAnswer.getQuestionId());
        assertThat(answer.getText()).isEqualTo(mockedAnswer.getText());
    }

    @Test
    public void testMapAnswerDto() {
        UUID answerUUID = UUID.fromString("73f2c025-434d-4d94-a520-ef983eb3d120");
        Answer mockedAnswer = new Answer(answerUUID, "text 1", null);
        AnswerDto answer = answerMapper.map(mockedAnswer);

        assertThat(answer.getId()).isEqualTo(answerUUID);
    }

    @Test
    public void testMapAnswerDtoQuestionNotNull() {
        UUID answerUUID = UUID.fromString("73f2c025-434d-4d94-a520-ef983eb3d120");
        Answer mockedAnswer = new Answer(answerUUID, "text 1", question);
        AnswerDto answer = answerMapper.map(mockedAnswer);

        assertThat(answer.getQuestionId()).isEqualTo(questionUUID);
    }

}