package com.marketlogic.survey.service;

import com.marketlogic.survey.data.entity.Answer;
import com.marketlogic.survey.data.repository.AnswerRepository;
import com.marketlogic.survey.util.mapper.AnswerMapper;
import com.marketlogic.survey.web.dto.answer.AnswerDto;
import com.marketlogic.survey.web.dto.answer.CreateAnswerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Autowired
    public AnswerService(AnswerRepository answerRepository,
                         AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    @Transactional
    public AnswerDto createAnswer(CreateAnswerDto createAnswerDto) {
        Answer answer = answerMapper.map(createAnswerDto);
        Answer persisted = answerRepository.save(answer);
        return answerMapper.map(persisted);
    }

    @Transactional
    public List<AnswerDto> getAllAnswers() {
        return answerRepository.findAll().stream()
                .map(answerMapper::map)
                .collect(Collectors.toUnmodifiableList());
    }

}
