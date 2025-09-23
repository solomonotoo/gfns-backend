package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.BusinessClass;

public interface BusinessClassRepository extends JpaRepository<BusinessClass, Integer>{

	Integer countById(Integer id);

}
