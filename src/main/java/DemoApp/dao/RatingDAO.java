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
import Request.RatingRequest;

@Repository
public class RatingDAO {


	public ArrayList<Rating> getRatingByProduct(RatingRequest request) throws SQLException, ClassNotFoundException {
		ArrayList<Rating> listRating = new ArrayList<Rating>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_Rating_GetByProduct(?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.setInt(2, request.getIdFlower());
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Rating objRating = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Rating.class);
				listRating.add(objRating);
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
		return listRating;
	}

	
	public void addRating(RatingRequest request) throws SQLException, ClassNotFoundException {
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_Rating_add(?,?,?,?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.INTEGER);
			proc.setInt(2, request.getUserID());
			proc.setInt(3, request.getIdFlower());
			proc.setString(4, request.getComment());
			proc.setDouble(5, request.getScore());
			proc.execute();
			request.setId(proc.getInt(1)); 
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
