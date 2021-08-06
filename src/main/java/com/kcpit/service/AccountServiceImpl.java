package com.kcpit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcpit.Model.AccountDatilsBO;
import com.kcpit.dto.AccountDetailsDTO;
import com.kcpit.exceptionAdvice.InputDateException;
import com.kcpit.repositories.BankRepository;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private BankRepository bankRepo;

	@Override
	public List<AccountDetailsDTO> getAllAccountbySelectDated(LocalDate sd, LocalDate ed) {
		List<AccountDetailsDTO> listDto = new ArrayList<>();
		List<AccountDatilsBO> allAccountByselectdate = bankRepo.getAllAcountsSelectedDate(sd, ed);
		System.out.println(allAccountByselectdate);
		if (allAccountByselectdate.isEmpty()) {
			throw new InputDateException("data not present in the given date");
		}
		// List<AccountDatilsBO> allAccountByselectdate = bankRepo.findAll();
		allAccountByselectdate.forEach(bo -> {
			AccountDetailsDTO dto = new AccountDetailsDTO();
			BeanUtils.copyProperties(bo, dto);
			dto.setAccCreateDate(bo.getAccCreateDate().format(DateTimeFormatter.ISO_DATE).toString());
			listDto.add(dto);
		});
		return listDto;
	}

	@Override
	public AccountDetailsDTO fatchToDataBase(AccountDetailsDTO dto) {
		LocalDate accCreateDate1 = LocalDate.parse(dto.getAccCreateDate());
		AccountDatilsBO bo = new AccountDatilsBO();
		bo.setAccCreateDate(accCreateDate1);
		BeanUtils.copyProperties(dto, bo);
		bo = bankRepo.save(bo);
		BeanUtils.copyProperties(bo, dto);
		return dto;
	}

	@Override
	public String deleteByAccoutID(Long accountID) {
		String msg = null;
		if (accountID == null) {
			msg = " please provide valid accountId";
		}
		AccountDatilsBO datilsBO = bankRepo.findById(accountID).get();
		if (datilsBO != null) {
			bankRepo.deleteById(accountID);
		} else {
			msg = " data not found Given Account Id";
		}
		return msg;
	}

	@Override
	public AccountDatilsBO upadateAccount(AccountDetailsDTO dto) {
		AccountDatilsBO datilsBO = null;

		if (dto != null && dto.getAccountId() != null) {
			Optional<AccountDatilsBO> optional = bankRepo.findById(dto.getAccountId());
			if (optional.isPresent()) {
				datilsBO = optional.get();
				datilsBO.setAccCreateDate(LocalDate.parse(dto.getAccCreateDate()));
				datilsBO.setFullName(dto.getFullName());
				datilsBO = bankRepo.save(datilsBO);
			}

		} else {
			throw new InputDateException("data not present in the given ID");
		}
		return datilsBO;
	}

}
