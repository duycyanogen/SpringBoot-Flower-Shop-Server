package DemoApp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.stereotype.Repository;
import Config.SQLConnect;

import Request.AccountRequest;


@Repository
public class AccountDAO {

//	@Autowired
//	private DataSourceConfig myBeanConnect;
	// @Autowired
	// private SQLConnect connect;

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
