package com.example.quiz_managment.controller;

import com.example.quiz_managment.entity.*;
import com.example.quiz_managment.service.QuizService;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createQuiz(@Valid @RequestBody Quiz quiz) {
        try {
            Quiz createdQuiz = quizService.createQuiz(quiz);
            return ResponseEntity.ok(createdQuiz);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuiz(@PathVariable Long id, @Valid @RequestBody Quiz quiz) {
        try {
            Quiz updatedQuiz = quizService.updateQuiz(id, quiz);
            return ResponseEntity.ok(updatedQuiz);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}