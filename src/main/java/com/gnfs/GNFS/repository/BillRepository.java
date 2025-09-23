package com.gnfs.GNFS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer> {

	Integer countById(Integer id);
}
