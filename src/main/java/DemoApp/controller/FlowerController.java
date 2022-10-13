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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlowerController {
	@Autowired
	private FlowerService flowerService;

	@GetMapping(path = "/flower/get")
	public ArrayList<Flower> getAllFlower() throws SQLException, ClassNotFoundException {
		return flowerService.getAllFlower();
	}

}
