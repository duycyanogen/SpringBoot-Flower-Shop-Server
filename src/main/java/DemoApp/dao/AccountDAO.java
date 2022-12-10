package DemoApp.dao;

import java.sql.*;
import java.util.ArrayList;

import DemoApp.model.Account;
import DemoApp.model.Flower;
import Helpers.MappingHelper;
import Request.FlowerRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import Config.SQLConnect;

import Request.AccountRequest;


@Repository
public class AccountDAO {

//	@Autowired
//	private DataSourceConfig myBeanConnect;
	// @Autowired
	// private SQLConnect connect;

	public int changepw(AccountRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		conn.setAutoCommit(false);
		String callString = "{call dbo.sp_updatePass(?,?) }";
		int userId = -1;
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.setString(1, request.getPassword());
			proc.setInt(2, request.getId());

			proc.registerOutParameter(2, Types.INTEGER);
			proc.execute();
			userId = proc.getInt(2) != 0 ? proc.getInt(2) : userId;

			conn.commit();
			return userId;
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

	public int login(AccountRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		conn.setAutoCommit(false);
		String callString = "{call dbo.sp_Login(?,?,?) }";
		int userId = -1;
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.setString(1, request.getPhone());
			proc.setString(2, request.getPassword());

			proc.registerOutParameter(3, Types.INTEGER);
			proc.execute();
			userId = proc.getInt(3) != 0 ? proc.getInt(3) : userId;

			conn.commit();
			return userId;
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

	public void createAccount(AccountRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		conn.setAutoCommit(false);
		String callString = "{ ? = call dbo.sp_UserAdd(?,?,?,?,?,?,?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.setString(2, request.getName());
			proc.setString(3, request.getEmail());
			proc.setString(4, request.getPhone());
			proc.setString(5, request.getAddress());
			proc.setString(6, request.getPassword());
			proc.setInt(7, request.getIdRole());
			proc.setInt(8, request.getIdRole());
			proc.execute();
			//int id = proc.getInt(1);
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

	public ArrayList<Account> getUserByKeyword(AccountRequest request) throws SQLException, ClassNotFoundException {
		ArrayList<Account> accounts = new ArrayList<Account>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_GetUser(?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.setString(2, request.getKeyword());
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Account account = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Account.class);
				accounts.add(account);
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
		return accounts;
	}

}
