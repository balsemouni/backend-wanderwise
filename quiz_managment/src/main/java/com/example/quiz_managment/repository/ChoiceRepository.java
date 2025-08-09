package com.example.quiz_managment.repository;

import com.example.quiz_managment.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByQuestionId(Long questionId);
}
