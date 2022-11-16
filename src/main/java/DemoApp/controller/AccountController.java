package DemoApp.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import DemoApp.service.AccountService;

import Request.AccountRequest;

import Response.ResponseObject;

@RestController
@CrossOrigin("*")
public class AccountController {
	@Autowired
	private AccountService accountService;


	@PostMapping("account/login")
	public ResponseObject<Object> login(@RequestBody AccountRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			int id = accountService.login(request);

			if(id>0){
				apiRespo.setObject(id);
				apiRespo.setToastMessage("Đăng nhập thành công");
			}else{
				apiRespo.setObject(-1);
				apiRespo.setToastMessage("Đăng nhập thất bại");
			}
		} catch (Exception e) {
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());
			System.out.print(e);
			apiRespo.setObject(e.toString());

		}
		return apiRespo;
	}

	@PostMapping("account/add-account")
	public ResponseObject<Object> addAccount(@RequestBody AccountRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			accountService.createAccount(request);
			apiRespo.setObject("Đăng ký thành công");
		} catch (Exception e) {
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());
			System.out.print(e);
			apiRespo.setObject(e.toString());

		}
		return apiRespo;
	}

	

}
