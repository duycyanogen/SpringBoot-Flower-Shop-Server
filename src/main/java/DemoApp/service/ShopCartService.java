package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DemoApp.dao.ShopCartDAO;
import DemoApp.model.ShopCart;
import Request.ShopCartRequest;


@Service
public class ShopCartService {
	@Autowired
	private ShopCartDAO shopCartDAO;

	public void addShopCart(ShopCartRequest request) throws SQLException, ClassNotFoundException {
		try {

			shopCartDAO.addShopCart(request);
		} catch (Exception e) {
			throw e;
		}
	}

	public ArrayList<ShopCart> getShopCartByUserID(ShopCartRequest request) throws SQLException, ClassNotFoundException {
		try {

			return shopCartDAO.getShopCartById(request);
		} catch (Exception e) {
			throw e;
		}

	}

}
