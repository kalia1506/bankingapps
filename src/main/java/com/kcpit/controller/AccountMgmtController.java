package com.kcpit.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcpit.Model.AccountDatilsBO;
import com.kcpit.dto.AccountDetailsDTO;
import com.kcpit.service.AccountService;

@RestController
public class AccountMgmtController {
	@Autowired
	private AccountService service;

	@PostMapping("/insert")
	public ResponseEntity<String> insertData(@Valid @RequestBody AccountDetailsDTO dto) {
		dto = service.fatchToDataBase(dto);
		String succesMessage = "Account is successfully Created with account ID " + dto.getAccountId();
		return new ResponseEntity<String>(succesMessage, HttpStatus.CREATED);
	}

	@PostMapping("/insert1")
	public ResponseEntity<String> insertData1(@Valid @RequestBody AccountDetailsDTO dto) {
		
		dto = service.fatchToDataBase(dto);
		String succesMessage = "Account is successfully Created with account ID " + dto.getAccountId();
		return new ResponseEntity<String>(succesMessage, HttpStatus.CREATED);
	}

	@GetMapping(value = { "/", "/getAccounts" })
	public ResponseEntity<List<AccountDetailsDTO>> getAllAccountByselectDate(
			@RequestParam("sd") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate sd,
			@RequestParam("ed") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate ed) {

		List<AccountDetailsDTO> accountbySelectDated = service.getAllAccountbySelectDated(sd, ed);

		return new ResponseEntity<List<AccountDetailsDTO>>(accountbySelectDated,HttpStatus.FOUND);

	}

	@PostMapping(value = { "/update" })
	public AccountDatilsBO updatebyAccountId(@RequestBody AccountDetailsDTO dto) {
		AccountDatilsBO upadateAccount = service.upadateAccount(dto);
		return upadateAccount;
	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteByAccountID(@PathVariable Long id) {
		String msg = service.deleteByAccoutID(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);

	}
}
