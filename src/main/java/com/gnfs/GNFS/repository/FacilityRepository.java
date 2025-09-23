package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Integer>{

	Integer countById(Integer id);

}
