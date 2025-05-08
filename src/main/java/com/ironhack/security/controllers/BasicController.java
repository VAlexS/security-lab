package com.ironhack.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicController {

    @GetMapping("/cosas-confidenciales")
    public ResponseEntity<String> cosasImportantes(){
        return ResponseEntity.ok("Aquí está todo lo confidencial");
    }

    @GetMapping("/public/users")
    public ResponseEntity<String> publicUsers(){
        return ResponseEntity.ok("Esto lo puede ver cualquiera");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminRoute(){
        return ResponseEntity.ok("Esto solo esta disponible para el administrador");
    }
}
