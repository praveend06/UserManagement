package com.stellantis.od1.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stellantis.od1.user.domain.RoleEntity;

@Repository
public interface RolesRepository extends CrudRepository<RoleEntity, Long> {

	RoleEntity findByRole(String role);

	@Query("select roleEntity from RoleEntity roleEntity where technicalTarget in (:technicalTarget)")
	Optional<List<RoleEntity>> findByTechnicalTargets(List<String> technicalTarget);

	boolean existsByRole(String role);

}
