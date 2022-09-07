package com.marketlogic.survey.service;

import com.marketlogic.survey.data.entity.Answer;
import com.marketlogic.survey.data.entity.Question;
import com.marketlogic.survey.data.repository.AnswerRepository;
import com.marketlogic.survey.util.mapper.AnswerMapper;
import com.marketlogic.survey.web.dto.answer.AnswerDto;
import com.marketlogic.survey.web.dto.answer.CreateAnswerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    @InjectMocks
    AnswerService answerService;

    @Mock
    AnswerMapper answerMapper;

    @Mock
    AnswerRepository answerRepository;

    private final UUID questionUUID = UUID.fromString("333a6d66-ca7b-49b5-9f78-2856fedff348");

    @Test
    public void testGetAnswersEmptyList() {
        when(answerRepository.findAll()).thenReturn(List.of());

        List<AnswerDto> answers = answerService.getAllAnswers();

        assertThat(answers.size()).isEqualTo(0);
    }

    @Test
    public void testGetAnswers() {
        when(answerRepository.findAll())
                .thenReturn(getListOfAnswers());
        when(answerMapper.map(any(Answer.class)))
                .thenReturn(new AnswerDto(UUID.randomUUID(), "text", questionUUID));
        List<AnswerDto> answers = answerService.getAllAnswers();

        assertThat(answers)
                .extracting(AnswerDto::getQuestionId)
                .contains(questionUUID);
    }

    @Test
    public void testCreateAnswer() {
        UUID answerUUID = UUID.fromString("73f2c025-434d-4d94-a520-ef983eb3d120");
        Answer mockedPersistent = new Answer(answerUUID, "text 1", null);
        Answer mockedWithoutId = new Answer(null, "text 1", null);
        AnswerDto mockedPersistentDto = new AnswerDto(answerUUID, "text 1", null);
        when(answerMapper.map(any(CreateAnswerDto.class)))
                .thenReturn(mockedWithoutId);
        when(answerRepository.save(any(Answer.class)))
                .thenReturn(mockedPersistent);
        when(answerMapper.map(any(Answer.class)))
                .thenReturn(mockedPersistentDto);
        AnswerDto answer
                = answerService.createAnswer(new CreateAnswerDto());

        assertThat(answer.getId()).isEqualTo(answerUUID);
    }

    private List<Answer> getListOfAnswers() {
        Question mockedQuestion = new Question(questionUUID, null, null, null);
        return List.of(new Answer(UUID.randomUUID(), "text", mockedQuestion),
                new Answer(UUID.randomUUID(), "text", mockedQuestion));
    }

}