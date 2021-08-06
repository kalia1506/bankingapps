package com.kcpit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.kcpit.controller.AccountMgmtController;
import com.kcpit.dto.AccountDetailsDTO;
import com.kcpit.service.AccountService;
import com.kcpit.utils.JsonUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountMgmtController.class)
public class BankingApps1AccountRestControllerTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountService service;

	@Test
	public void createUser_whenPostMethod() throws Exception {
		AccountDetailsDTO dto = getDto();
		when(service.fatchToDataBase(dto)).thenReturn(dto);

		MvcResult expect = mockMvc.perform(post("/insert1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(dto))).andExpect(status().isCreated())
				.andReturn();
		int status2 = expect.getResponse().getStatus();
		assertEquals(201, status2);

	}

	@Test
	public void listAllUsers_whenGetMethod() throws Exception {
		LocalDate sd = LocalDate.of(2021, 03, 13);
		LocalDate ed = LocalDate.of(2021, 04, 20);
		List<AccountDetailsDTO> listDTO = new ArrayList<AccountDetailsDTO>();
		listDTO.add(getDto());
		Mockito.when(service.getAllAccountbySelectDated(sd, ed)).thenReturn(listDTO);
		MvcResult expect = mockMvc.perform(get("/getAccounts/" + sd + "/" + ed))
				.andExpect(status().isFound()).andReturn();
		String contentAsString = expect.getResponse().getContentAsString();
		int statusCode = expect.getResponse().getStatus();
		System.out.println(statusCode);
		assertEquals(HttpStatus.FOUND.value(), statusCode);
		
	}

	public AccountDetailsDTO getDto() {
		AccountDetailsDTO dto = new AccountDetailsDTO();
		dto.setAccCreateDate(LocalDate.of(2021, 03, 21).format(DateTimeFormatter.ISO_DATE));
		dto.setAccountId(11l);
		dto.setFullName("kalu");
		return dto;
	}
}
