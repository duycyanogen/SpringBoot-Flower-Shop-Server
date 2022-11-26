package DemoApp.service;

import DemoApp.dao.AccountDAO;
import DemoApp.dao.StatisticsDAO;
import DemoApp.model.Account;
import DemoApp.model.Statistics;
import Request.AccountRequest;
import Request.StatisticsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class StatisticsService {
	@Autowired
	 private StatisticsDAO statDAO;
	
	public List<Statistics> getRevenue(java.sql.Date fromdate, java.sql.Date todate, String stype) throws SQLException, ClassNotFoundException, ParseException {
		try {
			return statDAO.getRevenue(fromdate, todate, stype);
		}
		catch (Exception e) {
			throw e;
		}
	}


}
