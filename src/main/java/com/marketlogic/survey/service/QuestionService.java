package com.marketlogic.survey.service;

import com.marketlogic.survey.data.entity.Answer;
import com.marketlogic.survey.data.entity.Question;
import com.marketlogic.survey.data.repository.QuestionRepository;
import com.marketlogic.survey.util.mapper.QuestionMapper;
import com.marketlogic.survey.web.dto.answer.CreateAnswerDto;
import com.marketlogic.survey.web.dto.question.CreateQuestionDto;
import com.marketlogic.survey.web.dto.question.QuestionDto;
import com.marketlogic.survey.web.dto.survey.CreateSurveyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository,
                           QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Transactional
    public QuestionDto createQuestion(CreateQuestionDto createQuestionDto) {
        Question question = questionMapper.map(createQuestionDto);
        updateAnswerReferences(question);
        Question persisted = questionRepository.save(question);
        return questionMapper.map(persisted);
    }

    @Transactional
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionMapper::map)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public List<Question> getAllQuestionsById(CreateSurveyDto createSurveyDto) {
        if (createSurveyDto.getQuestionUUIDs().size() == 0) {
            return null;
        }
        List<Question> questions = questionRepository.findAllById(createSurveyDto.getQuestionUUIDs());
        if (questions.size() == 0) {
            return null;
        }
        return questions;
    }

    @Transactional
    public Question getQuestionFromCreateAnswerDto(CreateAnswerDto createAnswerDto) {
        if (createAnswerDto.getQuestionId() == null) {
            return null;
        } else {
            Optional<Question> questionOptional = questionRepository.findById(createAnswerDto.getQuestionId());
            if (questionOptional.isPresent()) {
                return questionOptional.get();
            }
            throw new IllegalArgumentException("QuestionId not found!");
        }
    }

    private void updateAnswerReferences(Question question) {
        question.getAnswers().forEach(answer -> addQuestionReference(question, answer));
    }

    private void addQuestionReference(Question question, Answer answer) {
        answer.setQuestion(question);
    }

}
