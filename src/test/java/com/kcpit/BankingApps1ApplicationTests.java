package com.kcpit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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


class BankingApps1ApplicationTests {

	
}
