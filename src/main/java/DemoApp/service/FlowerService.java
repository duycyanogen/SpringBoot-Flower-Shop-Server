package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DemoApp.dao.FlowerDAO;
import DemoApp.model.Flower;
import Request.FlowerRequest;


@Service
public class FlowerService {
	@Autowired
	 private FlowerDAO flowerDAO;	
	
	public ArrayList<Flower> getAllFlower() throws SQLException, ClassNotFoundException
	{		
		return flowerDAO.getAllFlower();
	}
	
	public ArrayList<Flower> getFlowerById(FlowerRequest request) throws SQLException, ClassNotFoundException
	{		
		return flowerDAO.getFlowerById(request);
	}
	
	public ArrayList<Flower> getFlowerByKey(FlowerRequest request) throws SQLException, ClassNotFoundException
	{		
		return flowerDAO.getFlowerByKey(request);
	}
	
}
