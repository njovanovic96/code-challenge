package com.marketlogic.survey.web.rest;

import com.marketlogic.survey.service.SurveyService;
import com.marketlogic.survey.web.dto.survey.CreateSurveyDto;
import com.marketlogic.survey.web.dto.survey.SurveyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.marketlogic.survey.util.Constants.REST_API_PREFIX;

@RestController
@RequestMapping(value = REST_API_PREFIX + "survey")
public class SurveyRestController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyRestController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping()
    public ResponseEntity<List<SurveyDto>> getSurveys() {
        return ResponseEntity.ok(surveyService.getSurveys());
    }

    @PostMapping()
    public ResponseEntity<SurveyDto> createSurvey(@RequestBody @Valid @NotNull CreateSurveyDto createSurveyDto) {
        return ResponseEntity.ok(surveyService.createSurvey(createSurveyDto));
    }

}
