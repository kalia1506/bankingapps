package com.kcpit.service;

import java.time.LocalDate;
import java.util.List;

import com.kcpit.Model.AccountDatilsBO;
import com.kcpit.dto.AccountDetailsDTO;

public interface AccountService {
	public AccountDetailsDTO fatchToDataBase(AccountDetailsDTO dto);
	public List<AccountDetailsDTO> getAllAccountbySelectDated(LocalDate sd, LocalDate ed);
	public String deleteByAccoutID(Long accountID);
	public AccountDatilsBO upadateAccount(AccountDetailsDTO dto);
}
