package com.kcpit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.kcpit.Model.AccountDatilsBO;
import com.kcpit.dto.AccountDetailsDTO;
import com.kcpit.exceptionAdvice.InputDateException;
import com.kcpit.repositories.BankRepository;
import com.kcpit.service.AccountService;

@SpringBootTest
public class BankingApps1AccountServiceTests {
	@MockBean
	private BankRepository br;
	@Autowired
	private AccountService service;

//	@Disabled
	@Test
	public void saveEnityTest() {
		AccountDatilsBO bo1 = new AccountDatilsBO();
		bo1.setAccCreateDate(LocalDate.now());
		bo1.setAccountId(11111l);
		bo1.setFullName("kalu");

		Mockito.when(br.save(bo1)).thenReturn(bo1);

		AccountDetailsDTO dto = new AccountDetailsDTO();
		BeanUtils.copyProperties(bo1, dto);
		dto.setAccCreateDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

		dto = service.fatchToDataBase(dto);

		AccountDatilsBO bo2 = new AccountDatilsBO();
		BeanUtils.copyProperties(dto, bo2);
		bo2.setAccCreateDate(LocalDate.parse(dto.getAccCreateDate()));
		System.out.println(bo1);
		System.out.println(bo2);
		assertEquals(bo1, bo2);
	}

	@Disabled
	@Test
	public void getAllAccountbySelectDatedTest_01() {
		// First argument - specifies the expected exception.
		// Here it expects that code block will throw NumberFormatException
		// Second argument - is used to pass an executable code block or lambda
		// expression

		InputDateException exception = Assertions.assertThrows(InputDateException.class,
				() -> service.getAllAccountbySelectDated(LocalDate.now(), LocalDate.now()));
		System.out.println(exception.getMessage());
		assertTrue(exception.getMessage().contains("data not present in the given date"));
	}

	@Disabled
	@Test
	public void getAllAccountbySelectDatedTest_02() {

		AccountDatilsBO bo = new AccountDatilsBO();
		bo.setAccCreateDate(LocalDate.of(2021, 03, 21));
		bo.setAccountId(11111l);
		bo.setFullName("kalu");
		AccountDatilsBO bo1 = new AccountDatilsBO();
		bo1.setAccCreateDate(LocalDate.now());
		bo1.setAccountId(22222l);
		bo1.setFullName("juli");
		AccountDatilsBO bo2 = new AccountDatilsBO();
		bo2.setAccCreateDate(LocalDate.of(2021, 03, 22));
		bo2.setAccountId(33333l);
		bo2.setFullName("kalia");
		List<AccountDatilsBO> listbo = new ArrayList<AccountDatilsBO>();
		listbo.add(bo);
		listbo.add(bo1);
		listbo.add(bo2);

		Mockito.when(br.getAllAcountsSelectedDate(LocalDate.of(2021, 03, 20), LocalDate.of(2021, 03, 25)))
				.thenReturn(listbo);

		List<AccountDetailsDTO> listdto2 = service.getAllAccountbySelectDated(LocalDate.of(2021, 03, 20),
				LocalDate.of(2021, 03, 25));
		System.out.println(listbo);
		System.out.println(listdto2);
		List<AccountDatilsBO> listbo1 = new ArrayList<AccountDatilsBO>();
		listdto2.forEach(dto -> {
			AccountDatilsBO b = new AccountDatilsBO();
			b.setAccountId(dto.getAccountId());
			b.setFullName(dto.getFullName());
			b.setAccCreateDate(LocalDate.parse(dto.getAccCreateDate()));
			listbo1.add(b);

		});
		System.out.println(listbo);
		System.out.println(listdto2);
		assertEquals(listbo, listbo1);

	}

	@Disabled
	@Test
	public void deleteByAccoutID() {
		AccountDatilsBO bo = new AccountDatilsBO();
		bo.setAccCreateDate(LocalDate.of(2021, 03, 21));
		bo.setAccountId(11l);
		bo.setFullName("kalu");
		Mockito.when(br.findById(11l)).thenReturn(Optional.of(bo));
		String msg = service.deleteByAccoutID(bo.getAccountId());
		verify(br).deleteById(bo.getAccountId());
	}

	@Disabled
	@Test
	public void updateAccountID() {
		AccountDetailsDTO dto = new AccountDetailsDTO();
		dto.setAccCreateDate(LocalDate.of(2021, 03, 21).format(DateTimeFormatter.ISO_DATE));
		dto.setAccountId(11l);
		dto.setFullName("kalu");
		AccountDatilsBO bo = new AccountDatilsBO();
		bo.setAccCreateDate(LocalDate.of(2021, 03, 21));
		bo.setAccountId(11l);
		bo.setFullName("kalu");
		when(br.save(bo)).thenReturn(bo);
		when(br.findById(bo.getAccountId())).thenReturn(Optional.of(bo));

		AccountDatilsBO bo2 = service.upadateAccount(dto);
		System.out.println(bo + "" + bo2);
		assertEquals(bo, bo2);
	}

	@Disabled
	@Test
	public void updateAccount_02() {
		AccountDetailsDTO dto = null;
		InputDateException exception = Assertions.assertThrows(InputDateException.class,
				() -> service.upadateAccount(dto));
		System.out.println(exception.getMessage());
		assertTrue(exception.getMessage().contains("data not present in the given ID"));
	}
}
