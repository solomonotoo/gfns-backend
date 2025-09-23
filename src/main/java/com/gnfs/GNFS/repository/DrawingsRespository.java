package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.Drawing;

public interface DrawingsRespository extends JpaRepository<Drawing, Integer>{

	Integer countById(Integer id);
	
	

}
