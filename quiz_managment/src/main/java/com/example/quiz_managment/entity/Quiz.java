package com.example.quiz_managment.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false)
    @NotBlank(message = "Quiz title cannot be blank")
    private String title;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();

    // Constructors
    public Quiz() {}

    public Quiz(String title) {
        this.title = title;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        if (questions != null) {
            questions.forEach(q -> q.setQuiz(this));
        }
    }
}