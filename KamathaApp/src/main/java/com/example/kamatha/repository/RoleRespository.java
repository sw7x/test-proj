package com.example.kamatha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kamatha.model.Role;
/**
 *  2020-11-08
 * @author SHAN
 *
 */
@Repository("roleRepository")
public interface RoleRespository extends JpaRepository<Role, Integer> {

 Role findByRole(String role);
}
