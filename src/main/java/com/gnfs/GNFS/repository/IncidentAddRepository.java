package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.IncidentAdd;

public interface IncidentAddRepository extends JpaRepository<IncidentAdd, Long>{

	Long countById(Long id);
}
