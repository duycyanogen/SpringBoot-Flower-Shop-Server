package DemoApp.controller;


import DemoApp.service.StatisticsService;
import DemoApp.service.TransactionService;
import Request.StatisticsRequest;
import Request.TransactionRequest;
import Response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class StatisticsController {
	@Autowired
	private StatisticsService statisticsService;

	
	@GetMapping(path = "/stat/get-revenue")
	public ResponseObject<Object> getRevenue(@RequestBody StatisticsRequest request) throws SQLException, ClassNotFoundException, ParseException {
		ResponseObject<Object> apiRespo = new ResponseObject<Object>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

		String toDate = request.dateTo;
		String fromDate = request.dateFrom;
		java.util.Date todate = formatter.parse(toDate);
		java.util.Date fromdate = formatter.parse(fromDate);
		java.sql.Date sqltoDate = new java.sql.Date(todate.getTime());
		java.sql.Date sqlfromDate = new java.sql.Date(fromdate.getTime());

		String sType = request.getsType().toLowerCase();

		List<String> stypeApplied = new ArrayList<String>(Arrays.asList("m", "d", "y"));


		if(sqlfromDate.after(sqltoDate)) {
			apiRespo.setToastMessage("Lấy thống kê thất bại.");
			apiRespo.setErrorReason("Ngày bắt đầu không thể lớn hay ngày kết thúc.");
			return apiRespo;
		}

		if(stypeApplied.contains(sType))
		{
			apiRespo.setObject(statisticsService.getRevenue(sqlfromDate, sqltoDate, sType));
			apiRespo.setToastMessage("Lấy thống kê thành công.");
		}
		else{
			apiRespo.setToastMessage("Lấy thống kê thất bại.");
			apiRespo.setErrorReason("Thể loại thống kê không hợp lệ.");
		}


		return apiRespo;
	}



}
