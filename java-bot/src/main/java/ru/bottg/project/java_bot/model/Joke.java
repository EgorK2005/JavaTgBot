package ru.bottg.project.java_bot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "joke")
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joke_seq")
    @SequenceGenerator(name = "joke_seq", sequenceName = "seq_joke_id", allocationSize = 1)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "joke", cascade = CascadeType.ALL)
    private List<JokeRequest> requests;
}