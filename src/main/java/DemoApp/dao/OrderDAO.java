package DemoApp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Config.SQLConnect;
import DemoApp.model.*;
import Helpers.MappingHelper;
import Request.AccountRequest;
import Request.OrderRequest;

@Repository
public class OrderDAO {

//	@Autowired
//	private DataSourceConfig myBeanConnect;
	// @Autowired
	// private SQLConnect connect;

	public ArrayList<Order> getAllOrder() throws SQLException, ClassNotFoundException {
		ArrayList<Order> listOrder = new ArrayList<Order>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_Order_GetAll() }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Order objOrder = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Order.class);
				listOrder.add(objOrder);
			}
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return listOrder;
	}

	public ArrayList<Order> getOrderById(OrderRequest request) throws SQLException, ClassNotFoundException {
		ArrayList<Order> listOrder = new ArrayList<Order>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_Order_GetById(?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.setInt(2, request.getId());
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Order objOrder = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Order.class);
				listOrder.add(objOrder);
			}
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return listOrder;
	}

//	public ArrayList<Order> getOrderByKey(OrderRequest request) throws SQLException, ClassNotFoundException {
//		ArrayList<Order> listOrder = new ArrayList<Order>();
//		Connection conn = SQLConnect.getConnection();
//		String callString = "{ ? = call dbo.sp_Order_GetByKey(?) }";
//		try {
//			CallableStatement proc = conn.prepareCall(callString);
//			proc.registerOutParameter(1, Types.OTHER);
//			proc.setString(2, request.getKeyword());
//			proc.execute();
//			ResultSet resultSet = proc.getResultSet();
//			while (resultSet.next()) {
//				ModelMapper modelMapper = new ModelMapper();
//				Order objOrder = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Order.class);
//				listOrder.add(objOrder);
//			}
//			conn.commit();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return listOrder;
//	}
	
	public void addOrder(Connection conn, OrderRequest request) throws SQLException, ClassNotFoundException {
		String callString = "{ call dbo.sp_Order_add(?,?,?,?,?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.setInt(1, request.getTransactionID());
			proc.setInt(2, request.getIdFlower());
			proc.setInt(3, request.getQuantity());
			proc.setDouble(4, request.getAmount());
			proc.registerOutParameter(5, Types.INTEGER);
			proc.execute();
			int id = proc.getInt(5);
			request.setId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
