package MinorProject.TruthNet_Transformer_Backend.Entity;

import lombok.Data;

@Data
public class UserDto {
private String username;
private String password;

public User toUser(){
    User user=new User();
    user.setUsername(this.getUsername());
    user.setPassword(this.getPassword());
    return user;
}
}
