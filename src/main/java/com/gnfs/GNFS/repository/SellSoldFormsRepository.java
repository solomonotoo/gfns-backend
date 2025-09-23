package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.SellSoldForms;

public interface SellSoldFormsRepository extends JpaRepository<SellSoldForms, Long> {

	Long countById(Long id);
}
