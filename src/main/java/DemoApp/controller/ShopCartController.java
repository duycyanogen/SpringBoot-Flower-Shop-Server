package DemoApp.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import DemoApp.service.ShopCartService;
import Request.ShopCartRequest;
import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class ShopCartController {
	@Autowired
	private ShopCartService shopCartService;


	

	@PostMapping("shop-cart/add-shop-cart")
	public ResponseObject<Object> addshopCart(@RequestBody ShopCartRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			shopCartService.addShopCart(request);
			apiRespo.setObject("Thêm vào giỏ hàng thành công");
		} catch (Exception e) {
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());
			System.out.print(e);
			apiRespo.setObject(e.toString());

		}
		return apiRespo;
	}
	
	@GetMapping(path = "/shop-cart/get-shop-cart-by-userID")
	public ResponseObject<Object> getShopCartByUserID(@RequestBody ShopCartRequest request) throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		apiRespo.setObject(shopCartService.getShopCartByUserID(request));
		return apiRespo;
	}

	

}
