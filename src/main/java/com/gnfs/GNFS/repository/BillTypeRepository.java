package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.BillType;

public interface BillTypeRepository extends JpaRepository<BillType, Integer>{

	Integer countById(Integer id);

	Long countById(Long id);

}
