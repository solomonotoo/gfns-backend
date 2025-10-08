package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.StaffRegistration;

public interface StaffRegistrationRepository extends JpaRepository<StaffRegistration, Long> {

	Long CountById(Long id);
}
