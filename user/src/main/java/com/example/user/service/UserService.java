package com.example.user.service;

import com.example.user.model.NewUser;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {
    void  createUser(NewUser newUser);
    void sendverificationemail(String userId);
    void deleteUser(String userId);
    UserRepresentation getUserById (String userId);
    public List<UserRepresentation> getAllUsers() ;

    }
