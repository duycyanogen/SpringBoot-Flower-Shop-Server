package DemoApp.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import DemoApp.model.Flower;
import DemoApp.service.FlowerService;
import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class FlowerController {
	@Autowired
	private FlowerService flowerService;

	@GetMapping(path = "/flower/get")
	public ResponseObject<Object> getAllFlower() throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		apiRespo.setObject(flowerService.getAllFlower());
		return apiRespo;
	}

}
