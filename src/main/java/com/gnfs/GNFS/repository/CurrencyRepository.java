package com.gnfs.GNFS.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.gnfs.GNFS.entity.Currency;

public interface CurrencyRepository  extends JpaRepository<Currency, Integer>{

	Integer countById(Integer id);

	
}
