package MinorProject.TruthNet_Transformer_Backend.Service;

import MinorProject.TruthNet_Transformer_Backend.Entity.MLRequestDto;
import MinorProject.TruthNet_Transformer_Backend.Entity.MLResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TransformerService {

    private final RestClient restClient;
    @Autowired
    public TransformerService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl(System.getenv().getOrDefault(
                        "ML_SERVICE_URL",
                        "http://localhost:5000"
                ))
                .build();
    }

    public MLResponseDto predict(String text) {

        MLRequestDto request = new MLRequestDto();
        request.setText(text);

        return restClient.post()
                .uri("/predict")
                .body(request)
                .retrieve()
                .body(MLResponseDto.class);
    }
}