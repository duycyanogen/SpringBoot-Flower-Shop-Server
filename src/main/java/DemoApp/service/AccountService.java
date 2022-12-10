package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import DemoApp.model.Account;
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

	public int changepw(AccountRequest request) throws SQLException, ClassNotFoundException
	{
		try {
			int rs = accountDAO.changepw(request);
			return rs;
		}
		catch (Exception e) {
			throw e;
		}

	}

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

	public void updateAccount(AccountRequest request) throws SQLException, ClassNotFoundException
	{
		try {

			accountDAO.updateAccount(request);
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

	public ArrayList<Account> getUserByKeyword(AccountRequest request) throws SQLException, ClassNotFoundException {
		try {
			return accountDAO.getUserByKeyword(request);

		} catch (Exception e) {
			throw e;
		}
	}

}
