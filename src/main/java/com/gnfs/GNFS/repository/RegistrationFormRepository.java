package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.RegistrationForm;

public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {

	Long countById(Long id);

}
