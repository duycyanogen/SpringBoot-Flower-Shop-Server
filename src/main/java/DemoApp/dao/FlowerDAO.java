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
import Request.FlowerRequest;

@Repository
public class FlowerDAO {

//	@Autowired
//	private DataSourceConfig myBeanConnect;
	// @Autowired
	// private SQLConnect connect;

	public ArrayList<Flower> getAllFlower() throws SQLException, ClassNotFoundException {
		ArrayList<Flower> listFlower = new ArrayList<Flower>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_Flower_GetAll() }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Flower objFLower = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Flower.class);
				listFlower.add(objFLower);
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
		return listFlower;
	}

	public ArrayList<Flower> getFlowerById(FlowerRequest request) throws SQLException, ClassNotFoundException {
		ArrayList<Flower> listFlower = new ArrayList<Flower>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_Flower_GetById(?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.setInt(2, request.getId());
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Flower objFLower = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Flower.class);
				listFlower.add(objFLower);
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
		return listFlower;
	}

	public ArrayList<Flower> getFlowerByKey(FlowerRequest request) throws SQLException, ClassNotFoundException {
		ArrayList<Flower> listFlower = new ArrayList<Flower>();
		Connection conn = SQLConnect.getConnection();
		String callString = "{ ? = call dbo.sp_Flower_GetByKey(?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			proc.registerOutParameter(1, Types.OTHER);
			proc.setString(2, request.getKeyword());
			proc.execute();
			ResultSet resultSet = proc.getResultSet();
			while (resultSet.next()) {
				ModelMapper modelMapper = new ModelMapper();
				Flower objFLower = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Flower.class);
				listFlower.add(objFLower);
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
		return listFlower;
	}
}
