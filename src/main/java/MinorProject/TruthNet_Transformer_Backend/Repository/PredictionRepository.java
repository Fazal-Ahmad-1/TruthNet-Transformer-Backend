package MinorProject.TruthNet_Transformer_Backend.Repository;

import MinorProject.TruthNet_Transformer_Backend.Entity.Prediction;
import MinorProject.TruthNet_Transformer_Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    List<Prediction> findByUser(User user);
}