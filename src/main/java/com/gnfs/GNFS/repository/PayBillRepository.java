package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.PayBill;

public interface PayBillRepository extends JpaRepository<PayBill, Long> {

	Long countById(Long id);
}
