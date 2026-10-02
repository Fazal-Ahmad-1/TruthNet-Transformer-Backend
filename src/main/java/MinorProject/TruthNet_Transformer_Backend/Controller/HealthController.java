package MinorProject.TruthNet_Transformer_Backend.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @RequestMapping(
            value = "/health",
            method = {RequestMethod.GET, RequestMethod.HEAD}
    )
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}