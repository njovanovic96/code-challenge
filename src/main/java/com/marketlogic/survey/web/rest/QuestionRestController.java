package com.marketlogic.survey.web.rest;

import com.marketlogic.survey.service.QuestionService;
import com.marketlogic.survey.web.dto.question.CreateQuestionDto;
import com.marketlogic.survey.web.dto.question.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.marketlogic.survey.util.Constants.REST_API_PREFIX;

@RestController
@RequestMapping(value = REST_API_PREFIX + "question")
public class QuestionRestController {

    private final QuestionService questionService;

    @Autowired
    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @PostMapping()
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody @Valid @NotNull CreateQuestionDto createQuestionDto) {
        return ResponseEntity.ok(questionService.createQuestion(createQuestionDto));
    }

}
