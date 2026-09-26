package MinorProject.TruthNet_Transformer_Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String prediction;
    private Double confidenceScore;
    private String modelUsed = "DistilRoBERTa";
    private LocalDateTime createdAt=LocalDateTime.now();
    @Lob
    @Column(columnDefinition = "TEXT")
    private String analysis;
    @ElementCollection
    private List<String> indicators;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
