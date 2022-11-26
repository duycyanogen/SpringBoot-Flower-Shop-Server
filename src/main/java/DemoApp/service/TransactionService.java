package DemoApp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Config.SQLConnect;
import DemoApp.dao.TransactionDAO;
import DemoApp.dao.ShopCartDAO;
import DemoApp.dao.OrderDAO;
import DemoApp.model.Transaction;
import Request.OrderRequest;
import Request.ShopCartRequest;
import Request.TransactionRequest;

@Service
public class TransactionService {
	@Autowired
	private TransactionDAO TransactionDAO;

	@Autowired
	private OrderDAO OrderDAO;
	
	@Autowired
	private ShopCartDAO ShopCartDAO;
	
	public ArrayList<Transaction> getAllTransaction() throws SQLException, ClassNotFoundException {
		try {
			ArrayList<Transaction> listTransaction = TransactionDAO.getAllTransaction();
			return listTransaction;

		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<Transaction> getTransactionByUser(TransactionRequest request) throws SQLException, ClassNotFoundException {
		try {
			ArrayList<Transaction> listTransaction = TransactionDAO.getTransactionByUser(request);
			return listTransaction;

		} catch (Exception e) {
			throw e;
		}
	}

	public void addTransaction(TransactionRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		conn.setAutoCommit(false);
		try {
			ShopCartRequest shopCartRequest = new ShopCartRequest();
			shopCartRequest.setId(request.getShopCartId());
			ShopCartDAO.updateIsOrdered(conn, shopCartRequest);
			TransactionDAO.addTransaction(conn, request);	
			for (OrderRequest orderRequest : request.getLstOrderRequest()) {
				orderRequest.setTransactionID(request.getId());
				OrderDAO.addOrder(conn, orderRequest);
			}
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}
		finally {
			conn.close();
		}
	}
	
	public void cancelTransaction(TransactionRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		conn.setAutoCommit(false);
		try {
			
			TransactionDAO.cancelTransaction(conn, request);	
		
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}
		finally {
			conn.close();
		}
	}
	
	public void updateStatus(TransactionRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		conn.setAutoCommit(false);
		try {
			
			TransactionDAO.updateStatus(conn, request);	
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}
		finally {
			conn.close();
		}
	}

}
