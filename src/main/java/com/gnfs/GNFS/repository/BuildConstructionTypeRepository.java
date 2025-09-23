package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.BuildConstructionType;

public interface BuildConstructionTypeRepository extends JpaRepository<BuildConstructionType, Integer> {

	

	Integer countById(Integer id);

}
