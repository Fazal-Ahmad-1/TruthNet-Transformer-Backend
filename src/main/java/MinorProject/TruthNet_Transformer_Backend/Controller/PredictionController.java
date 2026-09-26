package MinorProject.TruthNet_Transformer_Backend.Controller;

import MinorProject.TruthNet_Transformer_Backend.Entity.Prediction;
import MinorProject.TruthNet_Transformer_Backend.Entity.PredictionRequestDto;
import MinorProject.TruthNet_Transformer_Backend.Entity.User;
import MinorProject.TruthNet_Transformer_Backend.Service.PredictionService;
import MinorProject.TruthNet_Transformer_Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private UserService userService;


    @PostMapping("/{userId}")
    public ResponseEntity<?> createPrediction(
            @PathVariable Long userId,
            @RequestBody PredictionRequestDto predictionRequestDto) {

        User user = userService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity<>("No User Found", HttpStatus.NOT_FOUND);
        }

        Prediction prediction = new Prediction();
        prediction.setText(predictionRequestDto.getText());
        prediction.setUser(user);

        Prediction savedPrediction =
                predictionService.createPrediction(prediction);

        return new ResponseEntity<>(savedPrediction, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getPredictionById(@PathVariable Long id) {

        Prediction prediction =
                predictionService.getPredictionById(id);

        if (prediction == null) {
            return new ResponseEntity<>(
                    "No Prediction Found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(prediction, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<?> getAllPredictions() {

        List<Prediction> predictions =
                predictionService.getAllPredictions();

        return new ResponseEntity<>(predictions, HttpStatus.OK);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPredictionsByUser(
            @PathVariable Long userId) {

        User user = userService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity<>(
                    "No User Found",
                    HttpStatus.NOT_FOUND
            );
        }

        List<Prediction> predictions =
                predictionService.getPredictionsByUser(user);

        return new ResponseEntity<>(predictions, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePredictionById(@PathVariable Long id) {

        Prediction prediction =
                predictionService.getPredictionById(id);

        if (prediction == null) {
            return new ResponseEntity<>(
                    "No Prediction Found",
                    HttpStatus.NOT_FOUND
            );
        }

        predictionService.deletePredictionById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}