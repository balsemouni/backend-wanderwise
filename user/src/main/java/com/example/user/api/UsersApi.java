package com.example.user.api;

import com.example.user.model.NewUser;
import com.example.user.service.UserService;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersApi {
    private final UserService userService;

    public UsersApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity <?> createUser (@RequestBody NewUser newUser){
        userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}/send-verification-email")
    public ResponseEntity <?> sendVerificationEmail (@PathVariable String userId) {
        userService.sendverificationemail(userId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    
    }
    @GetMapping("/{userId}")
    public ResponseEntity <?> getUserbyid (@PathVariable String userId) {
//        userService.getUserById(userId);
        return ResponseEntity.ok(userService.getUserById(userId));

    }
    @GetMapping("")
    public ResponseEntity <?> getAllUses () {
        List<UserRepresentation> users=userService.getAllUsers();
        return ResponseEntity.ok(users);

    }
    }


