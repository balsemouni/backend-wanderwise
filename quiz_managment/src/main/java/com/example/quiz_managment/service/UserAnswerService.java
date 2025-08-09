package com.example.quiz_managment.service;

import com.example.quiz_managment.entity.Choice;
import com.example.quiz_managment.entity.Question;
import com.example.quiz_managment.entity.Quiz;
import com.example.quiz_managment.entity.UserAnswer;
import com.example.quiz_managment.repository.ChoiceRepository;
import com.example.quiz_managment.repository.QuestionRepository;
import com.example.quiz_managment.repository.ChoiceRepository;

import com.example.quiz_managment.repository.UserAnswerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


// UserAnswerService.java
@Service
@Transactional
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    public UserAnswerService(UserAnswerRepository userAnswerRepository,
                             QuestionRepository questionRepository,
                             ChoiceRepository choiceRepository) {
        this.userAnswerRepository = userAnswerRepository;
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
    }

    public UserAnswer submitAnswer(Long questionId, Set<Long> choiceIds) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Set<Choice> selectedChoices = choiceRepository.findAllById(choiceIds)
                .stream()
                .filter(choice -> choice.getQuestion().equals(question))
                .collect(Collectors.toSet());

        UserAnswer userAnswer = userAnswerRepository.findByQuestion(question)
                .orElse(new UserAnswer());

        userAnswer.setQuestion(question);
        userAnswer.setSelectedChoices(selectedChoices);

        return userAnswerRepository.save(userAnswer);
    }

    public Set<Choice> getUserAnswer(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        return userAnswerRepository.findByQuestion(question)
                .map(UserAnswer::getSelectedChoices)
                .orElse(Collections.emptySet());
    }
}
