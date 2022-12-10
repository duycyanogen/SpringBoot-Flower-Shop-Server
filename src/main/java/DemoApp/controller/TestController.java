package DemoApp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import Request.AccountRequest;
import Response.ResponseObject;
import DemoApp.model.*;

@RestController
@CrossOrigin("*")
public class TestController {

	@PostMapping("test")
	public ResponseObject<Object> test(@RequestBody AccountRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			List<Inventory> lstInventory = new ArrayList<Inventory>();
			Inventory objInventory1 = new Inventory();
			objInventory1.setInventoryID(1);
			objInventory1.setLockinventoryid("1");
			lstInventory.add(objInventory1);

			Inventory objInventory2 = new Inventory();
			objInventory2.setInventoryID(2);
			objInventory2.setLockinventoryid("2");
			lstInventory.add(objInventory2);

			Inventory objInventory3 = new Inventory();
			objInventory3.setInventoryID(1);
			objInventory3.setLockinventoryid("1");
			lstInventory.add(objInventory3);

			Inventory objInventory4 = new Inventory();
			objInventory4.setInventoryID(2);
			objInventory4.setLockinventoryid("2");
			lstInventory.add(objInventory4);
//			Map<String, Map<Inventory, List>> map = lstInventory.stream()
//					  .collect(groupingBy(Inventory::getInventoryID, groupingBy(Inventory::getLockinventoryid)));
		} catch (Exception e) {
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());
			System.out.print(e);
			apiRespo.setObject(e.toString());

		}
		return apiRespo;
	}

}
