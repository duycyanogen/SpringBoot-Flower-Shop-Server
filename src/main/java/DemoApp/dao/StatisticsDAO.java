package DemoApp.dao;

import Config.SQLConnect;
import DemoApp.model.Account;
import DemoApp.model.Flower;
import DemoApp.model.Statistics;
import Helpers.MappingHelper;
import Request.AccountRequest;
import Request.StatisticsRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class StatisticsDAO {

	public List<Statistics> getRevenue(Date dateFrom, Date dateTo, String sType) throws SQLException, ClassNotFoundException {
		List<Statistics> stats = new ArrayList<>();
		Connection conn = SQLConnect.getConnection();
		conn.setAutoCommit(false);
		String callString = "{? = call dbo.sp_THONG_KE(?,?,?) }";
		int userId = -1;
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);

			proc.setString(2, sType);
			proc.setDate(3, dateFrom);
			proc.setDate(4,dateTo);

			proc.execute();
			ResultSet resultSet = proc.getResultSet();

			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Statistics stat = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Statistics.class);
				stats.add(stat);
			}

			conn.commit();
			return stats;
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
