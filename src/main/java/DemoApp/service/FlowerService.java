package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				if (!flower.getImagesName().isEmpty())
				{
					flower.setImageURL("http://localhost:8080/image/" + flower.getImagesName().split(",")[0]);					
				}
					
			}
			return listFlower;

		} catch (Exception e) {
			throw e;
		}
	}

	public Flower getFlowerById(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {
			Flower result = null;
			ArrayList<Flower> listFlower = flowerDAO.getFlowerById(request);
			if (listFlower.size() > 0) {
				result = listFlower.get(0);
				String[] listImageNames = result.getImagesName().split(",");
				List<String> listImageURL = new ArrayList<String>();
				for (String imageName : listImageNames) {
					listImageURL.add("http://localhost:8080/image/" + imageName);
				}
				result.setListImageURL(listImageURL);
				
			}
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<Flower> getFlowerByKey(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {
			ArrayList<Flower> listFlower = flowerDAO.getFlowerByKey(request);
			for (Flower flower : listFlower) {
				if (!flower.getImagesName().isEmpty())
				{
					flower.setImageURL("http://localhost:8080/image/" + flower.getImagesName().split(",")[0]);					
				}
					
			}
			return listFlower;

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
	
	public void updateFlower(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {

			flowerDAO.updateFlower(request);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void updateViews(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {

			flowerDAO.updateViews(request);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void deleteFlower(FlowerRequest request) throws SQLException, ClassNotFoundException {
		try {

			flowerDAO.deleteFlower(request);
		} catch (Exception e) {
			throw e;
		}
	}

}
