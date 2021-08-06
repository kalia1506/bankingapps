package com.kcpit.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kcpit.Model.AccountDatilsBO;

public interface BankRepository extends JpaRepository<AccountDatilsBO, Serializable> {
	
	@Query(value = "from AccountDatilsBO where accCreateDate between :sd and :ed")
	 public List<AccountDatilsBO> getAllAcountsSelectedDate(@Param("sd") LocalDate sd,@Param("ed")LocalDate ed);

}
