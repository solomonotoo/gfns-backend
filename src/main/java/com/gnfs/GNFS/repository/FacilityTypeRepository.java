package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.FacilityType;

public interface FacilityTypeRepository extends JpaRepository<FacilityType, Integer> {

	Integer countById(Integer id);

}
