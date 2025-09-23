package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Integer>{

	Long countById(Integer id);

}
