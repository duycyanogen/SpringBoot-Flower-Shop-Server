package DemoApp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			ArrayList<ShopCart> lstShopCart = shopCartDAO.getShopCartById(request);
			for (ShopCart objShopCart : lstShopCart) {
				objShopCart.setImageURL("http://localhost:8080/image/" + objShopCart.getImagesName().split(",")[0]);
			}
			return lstShopCart;
		} catch (Exception e) {
			throw e;
		}

	}

}
