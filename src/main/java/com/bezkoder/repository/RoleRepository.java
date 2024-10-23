package com.bezkoder.repository;

import com.bezkoder.models.ERole;
import com.bezkoder.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
