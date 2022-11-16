package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DemoApp.dao.AccountDAO;
import DemoApp.dao.FlowerDAO;
import DemoApp.model.Flower;
import Request.AccountRequest;
import Request.FlowerRequest;


@Service
public class AccountService {
	@Autowired
	 private AccountDAO accountDAO;

	public int login(AccountRequest request) throws SQLException, ClassNotFoundException
	{
		try {
			int rs = accountDAO.login(request);
			return rs;
		}
		catch (Exception e) {
			throw e;
		}

	}
	
	public void createAccount(AccountRequest request) throws SQLException, ClassNotFoundException
	{		
		try {
			
			accountDAO.createAccount(request);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
}
