package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.EquipEscapeMeans;

public interface EquipEscapeMeansRepository extends JpaRepository<EquipEscapeMeans, Integer> {

	Integer countById(Integer id);

}
