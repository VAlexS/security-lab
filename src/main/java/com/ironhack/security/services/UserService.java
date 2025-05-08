package com.ironhack.security.services;

import com.ironhack.security.models.User;
import com.ironhack.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // el encode convierte la string en algo parecido a esto $2a$10$Fj1RI/RDkPj2wShDY5syye2FvOdCiUx41DE.gV0UoxIZjwb.XhV9u
        return userRepository.save(user);
    }

    public boolean passwordIsValid(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword()); // compara el password hardcodeado "1234" con el encriptado "$2a$....."
    }

    public Optional<User> getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
