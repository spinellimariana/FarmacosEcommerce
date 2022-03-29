package com.farmacos.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacos.ecommerce.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String role);
	
	
}
