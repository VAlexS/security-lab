package com.ironhack.security.repositories;

import com.ironhack.security.models.ERole;
import com.ironhack.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole eRole);
}
