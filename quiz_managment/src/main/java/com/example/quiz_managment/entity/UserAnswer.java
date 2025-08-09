package com.example.quiz_managment.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    @ManyToMany
    private Set<Choice> selectedChoices = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Choice> getSelectedChoices() {
        return selectedChoices;
    }

    public void setSelectedChoices(Set<Choice> selectedChoices) {
        this.selectedChoices = selectedChoices;
    }

    public UserAnswer(Long id, Question question, Set<Choice> selectedChoices) {
        this.id = id;
        this.question = question;
        this.selectedChoices = selectedChoices;
    }

    public UserAnswer(Question question, Set<Choice> selectedChoices) {
        this.question = question;
        this.selectedChoices = selectedChoices;
    }

    public UserAnswer() {
    }
}