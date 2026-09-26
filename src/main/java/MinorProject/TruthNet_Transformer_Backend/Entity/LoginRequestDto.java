package MinorProject.TruthNet_Transformer_Backend.Entity;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;
    private String password;
}