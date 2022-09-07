package com.marketlogic.survey.web.rest;

import com.marketlogic.survey.service.AnswerService;
import com.marketlogic.survey.web.dto.answer.AnswerDto;
import com.marketlogic.survey.web.dto.answer.CreateAnswerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnswerRestControllerTest {

    @InjectMocks
    AnswerRestController answerRestController;

    @Mock
    AnswerService answerService;

    private final UUID questionUUID = UUID.fromString("333a6d66-ca7b-49b5-9f78-2856fedff348");

    @Test
    public void testGetAnswersEmptyList() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(answerService.getAllAnswers()).thenReturn(List.of());

        ResponseEntity<List<AnswerDto>> responseEntity = answerRestController.getAnswers();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).size())
                .isEqualTo(0);
    }

    @Test
    public void testGetAnswers() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(answerService.getAllAnswers()).thenReturn(getListOfAnswers());

        ResponseEntity<List<AnswerDto>> responseEntity = answerRestController.getAnswers();

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody())
                .extracting(AnswerDto::getQuestionId)
                .contains(questionUUID);
    }

    @Test
    public void testPostAnswer() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(answerService.createAnswer(any(CreateAnswerDto.class)))
                .thenReturn(new AnswerDto(UUID.randomUUID(), "text 1", questionUUID));

        ResponseEntity<AnswerDto> responseEntity
                = answerRestController.createAnswer(new CreateAnswerDto());

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getId())
                .isNotNull();
    }

    @Test
    public void testPostAnswerValidationWithNull() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CreateAnswerDto>> violations
                = validator.validate(new CreateAnswerDto());
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void testPostAnswerValidation() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<CreateAnswerDto>> violations
                = validator.validate(new CreateAnswerDto("test1", UUID.randomUUID()));
        assertThat(violations).size().isEqualTo(0);
    }

    private List<AnswerDto> getListOfAnswers() {
        return List.of(new AnswerDto(UUID.randomUUID(), "text 1", questionUUID),
                new AnswerDto(UUID.randomUUID(), "text 2", questionUUID));
    }

}