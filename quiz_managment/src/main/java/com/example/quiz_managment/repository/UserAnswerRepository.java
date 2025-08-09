package com.example.quiz_managment.repository;

import com.example.quiz_managment.entity.Question;
import com.example.quiz_managment.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    Optional<UserAnswer> findByQuestion(Question question);
}