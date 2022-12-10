package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DemoApp.dao.RatingDAO;
import DemoApp.model.Rating;
import Request.RatingRequest;

@Service
public class RatingService {
	@Autowired
	private RatingDAO RatingDAO;

	public ArrayList<Rating> getRatingByProduct(RatingRequest request) throws SQLException, ClassNotFoundException {
		try {
			ArrayList<Rating> listRating = RatingDAO.getRatingByProduct(request);			
			return listRating;

		} catch (Exception e) {
			throw e;
		}
	}


	public void addRating(RatingRequest request) throws SQLException, ClassNotFoundException {
		try {

			RatingDAO.addRating(request);
		} catch (Exception e) {
			throw e;
		}
	}
	
	

}
