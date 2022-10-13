package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DemoApp.dao.FlowerDAO;
import DemoApp.model.Flower;


@Service
public class FlowerService {
	@Autowired
	 private FlowerDAO flowerDAO;	
	
	public ArrayList<Flower> getAllFlower() throws SQLException, ClassNotFoundException
	{		
		return flowerDAO.getAllFlower();
	}
	
}
