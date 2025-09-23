package com.gnfs.GNFS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer>{

	Long countById(Integer id);

	List<District> findByRegionId(Integer regionId);
}
