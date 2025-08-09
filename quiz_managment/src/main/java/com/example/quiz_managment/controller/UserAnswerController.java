package com.example.quiz_managment.controller;

import com.example.quiz_managment.entity.Choice;
import com.example.quiz_managment.entity.UserAnswer;
import com.example.quiz_managment.service.UserAnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/answers")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @PostMapping("/questions/{questionId}")
    public UserAnswer submitAnswer(@PathVariable Long questionId, @RequestBody Set<Long> choiceIds) {
        return userAnswerService.submitAnswer(questionId, choiceIds);
    }

    @GetMapping("/questions/{questionId}")
    public Set<Choice> getUserAnswer(@PathVariable Long questionId) {
        return userAnswerService.getUserAnswer(questionId);
    }
}
