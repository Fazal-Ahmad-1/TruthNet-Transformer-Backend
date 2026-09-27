package MinorProject.TruthNet_Transformer_Backend.Service;

import MinorProject.TruthNet_Transformer_Backend.Entity.MLResponseDto;
import MinorProject.TruthNet_Transformer_Backend.Entity.Prediction;
import MinorProject.TruthNet_Transformer_Backend.Entity.User;
import MinorProject.TruthNet_Transformer_Backend.Repository.PredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionService {

    @Autowired
    private PredictionRepository predictionRepository;

    @Autowired
    private TransformerService transformerService;


    public Prediction createPrediction(Prediction prediction) {

        MLResponseDto mlResponse =
                transformerService.predict(prediction.getText());

        prediction.setPrediction(mlResponse.getPrediction());
        prediction.setConfidenceScore(mlResponse.getConfidence());

        prediction.setModelUsed("TinyBERT");

        prediction.setAnalysis(mlResponse.getAnalysis());
        prediction.setIndicators(mlResponse.getIndicators());

        return predictionRepository.save(prediction);
    }


    public Prediction getPredictionById(Long id) {

        Prediction prediction =
                predictionRepository.findById(id).orElse(null);

        return prediction;
    }


    public List<Prediction> getAllPredictions() {

        return predictionRepository.findAll();
    }


    public List<Prediction> getPredictionsByUser(User user) {

        return predictionRepository.findByUser(user);
    }


    public void deletePredictionById(Long id) {

        predictionRepository.deleteById(id);
    }
}