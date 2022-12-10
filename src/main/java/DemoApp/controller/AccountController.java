package DemoApp.controller;


import DemoApp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import DemoApp.service.AccountService;

import Request.AccountRequest;

import Response.ResponseObject;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping("account/changepw")
	public ResponseObject<Object> changepw(@RequestBody AccountRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			int id = accountService.changepw(request);

			if(id>0){
				request.setKeyword(Integer.toString(id));
				List<Account> accounts = accountService.getUserByKeyword(request);
				apiRespo.setObject(accounts.get(0));
				apiRespo.setToastMessage("Đổi mật khẩu thành công");
			}else{

				apiRespo.setToastMessage("Đổi mật khẩu thất bại");
			}
		} catch (Exception e) {
			apiRespo.setError(true);
			apiRespo.setErrorReason(e.toString());
			apiRespo.setObject(e.toString());

		}
		return apiRespo;
	}

	@PostMapping("account/login")
	public ResponseObject<Object> login(@RequestBody AccountRequest request) {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();
		try {
			int id = accountService.login(request);

			if(id>0){
				request.setKeyword(Integer.toString(id));
				List<Account> accounts = accountService.getUserByKeyword(request);
				apiRespo.setObject(accounts.get(0));
				apiRespo.setToastMessage("Đăng nhập thành công");
			}else{

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
