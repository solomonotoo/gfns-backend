package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.EquipAlarmSystem;

public interface EquipAlarmSystemRepository extends JpaRepository<EquipAlarmSystem, Integer>{

	Integer countById(Integer id);

}
