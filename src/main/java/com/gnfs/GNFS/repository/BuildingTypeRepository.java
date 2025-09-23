package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.BuildingType;

public interface BuildingTypeRepository extends JpaRepository<BuildingType, Integer>{

	Integer countById(Integer id);

}
