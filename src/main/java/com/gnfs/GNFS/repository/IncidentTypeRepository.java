package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.IncidentType;

public interface IncidentTypeRepository extends JpaRepository<IncidentType, Integer> {
	Integer countById(Integer id);

	boolean existsByName(String name);
}
