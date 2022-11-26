package DemoApp.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import DemoApp.service.TransactionService;
import Request.TransactionRequest;
import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class TransactionController {
	@Autowired
	private TransactionService TransactionService;


	

	@PostMapping("transaction/add-transaction")
	public ResponseObject<Object> addTransaction(@RequestBody TransactionRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			TransactionService.addTransaction(request);
			apiRespo.setObject(request);
		} catch (Exception e) {
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());
			System.out.print(e);
			apiRespo.setObject(e.toString());

		}
		return apiRespo;
	}
	
	@GetMapping(path = "/transaction/get-all-transaction")
	public ResponseObject<Object> getAllTransaction() throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		apiRespo.setObject(TransactionService.getAllTransaction());
		return apiRespo;
	}
	
	@GetMapping(path = "/transaction/get-transaction-by-user")
	public ResponseObject<Object> getTransactionByUser(@RequestBody TransactionRequest request) throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		apiRespo.setObject(TransactionService.getTransactionByUser(request));
		return apiRespo;
	}
//
//	

}
