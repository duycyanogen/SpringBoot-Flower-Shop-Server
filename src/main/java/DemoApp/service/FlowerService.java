package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DemoApp.dao.FlowerDAO;
import DemoApp.model.Flower;
import Request.AccountRequest;
import Request.FlowerRequest;

@Service
public class FlowerService {
	@Autowired
	private FlowerDAO flowerDAO;

	public ArrayList<Flower> getAllFlower() throws SQLException, ClassNotFoundException {
		try {
			ArrayList<Flower> listFlower = flowerDAO.getAllFlower();
			for (Flower flower : listFlower) {
				flower.setImageURL("http://localhost:8080/image/" + flower.getImagesName());
			}
			return listFlower;

		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Flower> getFlowerById(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {

			return flowerDAO.getFlowerById(request);
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Flower> getFlowerByKey(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {
			return flowerDAO.getFlowerByKey(request);

		} catch (Exception e) {
			throw e;
		}
	}

	public void addFlower(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {

			flowerDAO.addFlower(request);
		} catch (Exception e) {
			throw e;
		}
	}

}
