package MinorProject.TruthNet_Transformer_Backend.Controller;

import MinorProject.TruthNet_Transformer_Backend.Entity.LoginRequestDto;
import MinorProject.TruthNet_Transformer_Backend.Entity.User;
import MinorProject.TruthNet_Transformer_Backend.Entity.UserDto;
import MinorProject.TruthNet_Transformer_Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDto loginRequestDto) {

        User user = userService.login(
                loginRequestDto.getUsername(),
                loginRequestDto.getPassword()
        );

        if (user == null) {
            return new ResponseEntity<>(
                    "Invalid username or password",
                    HttpStatus.UNAUTHORIZED
            );
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<?> createUser(
            @RequestBody UserDto userDto) {

        return new ResponseEntity<>(
                userService.createUser(userDto),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id) {

        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(
                    "No User Found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {

        List<User> users =
                userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable Long id) {

        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(
                    "No User Found",
                    HttpStatus.NOT_FOUND
            );
        }

        userService.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}