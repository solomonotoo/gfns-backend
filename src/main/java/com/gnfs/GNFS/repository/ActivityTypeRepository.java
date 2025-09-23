package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.ActivityType;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, Integer> {

	Long countById(Integer id);

}
