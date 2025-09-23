package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.EquipFireFightingSystem;

public interface EquipFireFightingSystemRepository extends JpaRepository<EquipFireFightingSystem, Integer> {

	Integer countById(Integer id);

}
