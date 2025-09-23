package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.BusinessType;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer>{

	Integer countById(Integer id);

}
