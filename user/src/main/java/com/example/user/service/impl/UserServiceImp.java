package com.example.user.service.impl;

import com.example.user.model.NewUser;
import com.example.user.service.UserService;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImp implements UserService {
    private  final Keycloak keycloak;

    @Value("WiseWander_v1.1")  // Added closing brace
    private String realm;

    public UserServiceImp(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public void createUser(NewUser newUser) {
        UserRepresentation userRepresentation=new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(newUser.first_name());
        userRepresentation.setLastName(newUser.last_name());
        userRepresentation.setEmail(newUser.email());
        userRepresentation.setUsername(newUser.username());
        userRepresentation.setEmailVerified(false);
        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(newUser.password());
        credentialRepresentation.setType(credentialRepresentation.PASSWORD);
        userRepresentation.setCredentials(List.of(credentialRepresentation));
        UsersResource usersResource = getUsersResource();
        Response response=usersResource.create(userRepresentation);
        log.info("Status Code"+response.getStatus());
        if(!Objects.equals(201,response.getStatus())){
            throw  new RuntimeException("status code "+response.getStatus());
        }
                    log.info("New user is created");

    }

    @Override
    public List<UserRepresentation> getAllUsers() {
        try {
            UsersResource usersResource = getUsersResource();
            List<UserRepresentation> users = usersResource.list();

            if (users == null || users.isEmpty()) {
                log.warn("No users found in realm {}", realm);
                return Collections.emptyList();
            }

            log.info("Retrieved {} users from realm {}", users.size(), realm);
            return users;
        } catch (Exception e) {
            log.error("Error fetching users from realm {}: {}", realm, e.getMessage());
            throw new RuntimeException("Error fetching users: " + e.getMessage(), e);
        }
    }
    @Override
    public void sendverificationemail(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(userId);
    }

    @Override
    public UserRepresentation getUserById(String userId) {
        log.debug("Attempting to fetch user with ID: {}", userId);

        try {
            log.debug("Attempting to fetch user with ID: {}", userId);

            UserResource userResource = getUsersResource().get(userId);

            UserRepresentation user = userResource.toRepresentation();

            log.debug("Retrieved user: {} (Username: {}, Email: {})",
                    userId, user.getUsername(), user.getEmail());

            return user;
        } catch (NotFoundException e) {
            log.error("User not found with ID: {}", userId);
            throw new RuntimeException("User with ID " + userId + " not found");
        } catch (Exception e) {
            log.error("Error retrieving user {}: {}", userId, e.getMessage(), e);
            throw new RuntimeException("Error retrieving user: " + e.getMessage());
        }
    }

    private UsersResource getUsersResource(){
        return keycloak.realm(realm).users();
    }
}
