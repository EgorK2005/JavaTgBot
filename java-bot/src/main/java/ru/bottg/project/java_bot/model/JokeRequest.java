package ru.bottg.project.java_bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "joke_request")
public class JokeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "joke_id", nullable = false)
    private Joke joke;

    @Column(name = "request_time", nullable = false)
    private LocalDateTime requestTime;
}