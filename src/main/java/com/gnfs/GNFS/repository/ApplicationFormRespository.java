package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.ApplicationForm;

public interface ApplicationFormRespository extends JpaRepository<ApplicationForm, Integer> {

	Integer countById(Integer id);

}
