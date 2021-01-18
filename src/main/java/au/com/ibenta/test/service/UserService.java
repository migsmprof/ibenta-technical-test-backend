package au.com.ibenta.test.service;

import java.util.ArrayList;
import java.util.Optional;

import java.util.HashMap;
import au.com.ibenta.test.persistence.*;

public class UserService {
    private UserRepository repository;

    public <S extends UserEntity> S create(String userFirstName, String userLastName, String userEmail, String userPassword) {
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userFirstName);
        newUser.setLastName(userLastName);
        newUser.setEmail(userEmail);
        newUser.setPassword(userPassword);
        return repository.save(newUser);
    }

    public Optional<UserEntity> get(Long userID) {
        return repository.findById(userID);
    }

    public Optional<UserEntity> update(Long userID, HashMap<String, String> updatedValues) {
        Optional<UserEntity> userToUpdate = repository.findById(userID);
        UserEntity foundUser = userToUpdate.orElse(null);
        UserEntity updatedUser = null;
        if (foundUser != null) {
            String getValue = "";
            if (updatedValues.containsKey("firstName")) {
                getValue = updatedValues.get("firstName");
                foundUser.setFirstName(getValue);
            }
            if (updatedValues.containsKey("lastName")) {
                getValue = updatedValues.get("lastName");
                foundUser.setLastName(getValue);
            }
            if (updatedValues.containsKey("password")) {
                getValue = updatedValues.get("password");
                foundUser.setPassword(getValue);
            }
            if (updatedValues.containsKey("email")) {
                getValue = updatedValues.get("email");
                foundUser.setEmail(getValue);
            }
            updatedUser = repository.save(foundUser);
        }
        return Optional.of(updatedUser);
    }

    public void delete(Long userID) {
        repository.deleteById(userID);
    }

    public ArrayList<UserEntity> list() {
        return repository.findAll();
    }
}
