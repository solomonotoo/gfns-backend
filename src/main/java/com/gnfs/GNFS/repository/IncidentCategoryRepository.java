package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.IncidentCategory;

public interface IncidentCategoryRepository extends JpaRepository<IncidentCategory, Integer>{
	Integer countById(Integer id);

	boolean existsByName(String name);
}
