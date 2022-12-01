package DemoApp.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import DemoApp.service.RatingService;
import Helpers.Extensions;
import Request.RatingRequest;
import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class RatingController {
	@Autowired
	private RatingService RatingService;



	@PostMapping(path = "/Rating/get-by-product")
	public ResponseEntity<Object> getRatingByProduct(@RequestBody RatingRequest request)
			throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			apiRespo.setObject(RatingService.getRatingByProduct(request));
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());
		}
		
	}


	@PostMapping("Rating/add-Rating")
	public ResponseEntity<Object> addRating(@RequestBody RatingRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
				
			RatingService.addRating(request);
			apiRespo.setObject("Thêm mới sản phẩm thành công!");
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());

		}
	}



	

}
