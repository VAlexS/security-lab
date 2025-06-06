package com.ironhack.security.controllers;

import com.ironhack.security.models.ERole;
import com.ironhack.security.models.User;
import com.ironhack.security.services.JwtService;
import com.ironhack.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> optionalUser = userService.getByUserName(user.getUsername());

        // miramos que el user existe
        if (optionalUser.isPresent()) {
            // extraemos el objeto User de dentro del Optional
            User foundUser = optionalUser.get();

            // comprobamos si el password es correcto
            if (userService.passwordIsValid(foundUser, user.getPassword())) {
                // generar un listado con el formato correcto de roles

                // generamos un nuevo array usando el método MAP para devolver en cada elemento del array solamente el nombre del ROLE
                List<ERole> roleNames = foundUser.getRoles().stream()
                        .map(role -> role.getName())
                        .collect(Collectors.toList());


                // una vez vemos que el usuario es correcto, generamos el token
                String token = jwtService.generateToken(foundUser.getUsername(), roleNames.toString());

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login incorrecto");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario no encontrado");
        }

    }

}
