package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.IncidentClassification;

public interface IncidentClassificationRepository extends JpaRepository<IncidentClassification, Integer>{
	Integer countById(Integer id);

	boolean existsByName(String name);
}
