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
import DemoApp.model.Flower;
import DemoApp.model.Transaction;
import DemoApp.model.TransactionDetail;
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
	
	public ArrayList<TransactionDetail> getAllTransaction() throws SQLException, ClassNotFoundException {
		try {
			ArrayList<TransactionDetail> listTransactionDetail = TransactionDAO.getAllTransaction();
			for (TransactionDetail objTransactionDetail : listTransactionDetail) {
				if (!objTransactionDetail.getImagesName().isEmpty())
				{
					objTransactionDetail.setImageURL("http://localhost:8080/image/" + objTransactionDetail.getImagesName().split(",")[0]);					
				}
				switch (objTransactionDetail.getTransactionStatus()) {
				case 0:{
					objTransactionDetail.setTransacitionStatusName("Đang chờ xác nhận");
					break;
				}
				case 1:{
					objTransactionDetail.setTransacitionStatusName("Đã xác nhận");
					break;
				}
				case 2:{
					objTransactionDetail.setTransacitionStatusName("Đã bị từ chối");
					break;
				}
				}
					
			}
			return listTransactionDetail;

		} catch (Exception e) {
			throw e;
		}
	}
	
	public ArrayList<TransactionDetail> getTransactionByUser(TransactionRequest request) throws SQLException, ClassNotFoundException {
		try {
			ArrayList<TransactionDetail> listTransactionDetail = TransactionDAO.getTransactionByUser(request);
			for (TransactionDetail objTransactionDetail : listTransactionDetail) {
				if (!objTransactionDetail.getImagesName().isEmpty())
				{
					objTransactionDetail.setImageURL("http://localhost:8080/image/" + objTransactionDetail.getImagesName().split(",")[0]);					
				}
				switch (objTransactionDetail.getTransactionStatus()) {
				case 0:{
					objTransactionDetail.setTransacitionStatusName("Đang chờ xác nhận");
					break;
				}
				case 1:{
					objTransactionDetail.setTransacitionStatusName("Đã xác nhận");
					break;
				}
				case 2:{
					objTransactionDetail.setTransacitionStatusName("Đã bị từ chối");
					break;
				}
				}
					
			}
			return listTransactionDetail;

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
