package com.marketlogic.survey.web.rest;

import com.marketlogic.survey.service.AnswerService;
import com.marketlogic.survey.web.dto.answer.AnswerDto;
import com.marketlogic.survey.web.dto.answer.CreateAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.marketlogic.survey.util.Constants.REST_API_PREFIX;

@RestController
@RequestMapping(value = REST_API_PREFIX + "answer")
public class AnswerRestController {

    private final AnswerService answerService;

    @Autowired
    public AnswerRestController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping()
    public ResponseEntity<List<AnswerDto>> getAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @PostMapping()
    public ResponseEntity<AnswerDto> createAnswer(@RequestBody @Valid @NotNull CreateAnswerDto createAnswerDto) {
        return ResponseEntity.ok(answerService.createAnswer(createAnswerDto));
    }

}
