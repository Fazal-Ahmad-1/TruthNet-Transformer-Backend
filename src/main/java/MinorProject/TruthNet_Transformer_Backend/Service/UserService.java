package MinorProject.TruthNet_Transformer_Backend.Service;

import MinorProject.TruthNet_Transformer_Backend.Entity.User;
import MinorProject.TruthNet_Transformer_Backend.Entity.UserDto;
import MinorProject.TruthNet_Transformer_Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public UserDto createUser(UserDto userDTO){
        if(userRepository.existsByUsername(userDTO.getUsername())){
            throw new IllegalArgumentException("Username already exists!");
        }
        User newUser=userDTO.toUser();
        userRepository.save(newUser);
        return userDTO;
    }
    public User getUserById(Long id){
        User user=userRepository.findById(id).orElse(null);
        return user;
    }
    public List<User> getAllUsers(){
        List<User>users=userRepository.findAll();
        return users;
    }
    public void deleteUserById(Long id) {
        User user=userRepository.findById(id).orElse(null);
        if(user!=null)
            userRepository.deleteById(id);
    }
    public User getUserByUsername(String username){
        User user=userRepository.findByUsername(username);
        return user;
    }
}

