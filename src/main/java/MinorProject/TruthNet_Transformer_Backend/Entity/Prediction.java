package MinorProject.TruthNet_Transformer_Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String prediction;
    private Double confidenceScore;
    private String modelUsed = "TinyBERT";
    private LocalDateTime createdAt = LocalDateTime.now();

    @Lob
    @Column(columnDefinition = "TEXT")
    private String analysis;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}