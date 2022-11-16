package DemoApp.dao;

import java.sql.*;

import org.springframework.stereotype.Repository;
import Config.SQLConnect;

import Request.AccountRequest;


@Repository
public class AccountDAO {

//	@Autowired
//	private DataSourceConfig myBeanConnect;
	// @Autowired
	// private SQLConnect connect;

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

}
