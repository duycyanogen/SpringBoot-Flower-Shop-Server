package DemoApp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import Config.SQLConnect;
import DemoApp.model.*;
import Helpers.MappingHelper;
import Request.ShopCartRequest;


@Repository
public class ShopCartDAO {

//	@Autowired
//	private DataSourceConfig myBeanConnect;
	// @Autowired
	// private SQLConnect connect;

	

	public ArrayList<ShopCart> getShopCartById(ShopCartRequest request) throws SQLException, ClassNotFoundException {
		ArrayList<ShopCart> listShopCart = new ArrayList<ShopCart>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_ShopCart_GetByUser(?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.setInt(2, request.getUserID());
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				ShopCart objShopCart = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), ShopCart.class);
				listShopCart.add(objShopCart);
			}
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return listShopCart;
	}

	public void addShopCart(ShopCartRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		String callString = "{call dbo.sp_ShopCart_add(?,?,?,?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.setInt(1, request.getUserID());
			proc.setInt(2, request.getIdFlower());
			proc.setDouble(3, request.getAmount());
			proc.registerOutParameter(4, Types.INTEGER);
			proc.execute();
			int id = proc.getInt(4);
			request.setId(id);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void updateIsOrdered(Connection conn, ShopCartRequest request) throws SQLException, ClassNotFoundException {
		String callString = "{ call dbo.sp_ShopCart_UpdateIsOrdered(?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.setInt(1, request.getId());
			proc.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} 
	}
	
}
