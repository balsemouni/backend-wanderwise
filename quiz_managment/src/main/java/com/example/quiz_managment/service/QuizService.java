package com.example.quiz_managment.service;

import com.example.quiz_managment.entity.*;
import com.example.quiz_managment.repository.QuizRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Transactional
    public Quiz createQuiz(Quiz quiz) {
        // Ensure all relationships are properly set
        if (quiz.getQuestions() != null) {
            quiz.getQuestions().forEach(question -> {
                question.setQuiz(quiz);
                if (question.getChoices() != null) {
                    question.getChoices().forEach(choice -> choice.setQuestion(question));
                }
            });
        }
        return quizRepository.save(quiz);
    }

    @Transactional
    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        return quizRepository.findById(id)
                .map(existingQuiz -> {
                    existingQuiz.setTitle(quizDetails.getTitle());

                    // Handle questions update
                    if (quizDetails.getQuestions() != null) {
                        // Clear existing questions to avoid duplicates
                        existingQuiz.getQuestions().clear();

                        // Add all new questions with proper relationships
                        quizDetails.getQuestions().forEach(question -> {
                            question.setQuiz(existingQuiz);
                            if (question.getChoices() != null) {
                                question.getChoices().forEach(choice -> choice.setQuestion(question));
                            }
                            existingQuiz.getQuestions().add(question);
                        });
                    }

                    return quizRepository.save(existingQuiz);
                })
                .orElseThrow(() -> new ValidationException("Quiz not found with id: " + id));
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuiz(Long id) {
        return quizRepository.findById(id);
    }

    @Transactional
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}