package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.Activity;

public interface ActivityRespository extends JpaRepository<Activity, Integer> {

	Long countById(Integer id);

}
