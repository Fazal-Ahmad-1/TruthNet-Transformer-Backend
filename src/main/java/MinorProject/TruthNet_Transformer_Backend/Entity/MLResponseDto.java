package MinorProject.TruthNet_Transformer_Backend.Entity;

import lombok.Data;

import java.util.List;

@Data
public class MLResponseDto {

    private String prediction;
    private Double confidence;

    private String analysis;
    private List<String> indicators;
}