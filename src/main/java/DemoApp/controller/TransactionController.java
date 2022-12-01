package DemoApp.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import DemoApp.service.TransactionService;
import Helpers.Extensions;
import Request.TransactionRequest;
import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class TransactionController {
	@Autowired
	private TransactionService TransactionService;

	@PostMapping("transaction/add-transaction")
	public ResponseEntity<Object> addTransaction(@RequestBody TransactionRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			TransactionService.addTransaction(request);
			apiRespo.setObject("Yêu cầu thanh toán đơn hàng thành công");
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());

		}
	}

	@PostMapping("transaction/cancel-transaction")
	public ResponseEntity<Object> cancelTransaction(@RequestBody TransactionRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			TransactionService.cancelTransaction(request);
			apiRespo.setObject("Hủy yêu cầu thanh toán đơn hàng thành công");
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());

		}
	}

	@PostMapping(path = "/transaction/get-all-transaction")
	public ResponseEntity<Object> getAllTransaction() throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			apiRespo.setObject(TransactionService.getAllTransaction());
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());
		}

	}

	@PostMapping(path = "/transaction/get-transaction-by-user")
	public ResponseEntity<Object> getTransactionByUser(@RequestBody TransactionRequest request)
			throws SQLException, ClassNotFoundException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			apiRespo.setObject(TransactionService.getTransactionByUser(request));
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());
		}

	}

	@PostMapping("transaction/update-status-transaction")
	public ResponseEntity<Object> updateStatusTransaction(@RequestBody TransactionRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			TransactionService.updateStatus(request);
			apiRespo.setObject("Cập nhật trạng thái đơn hàng thành công");
			return ResponseEntity.ok(apiRespo);
		} catch (Exception e) {
			return Extensions.catchApplicationError(e, apiRespo, e.getMessage());
		}
	}
//
//	

}
