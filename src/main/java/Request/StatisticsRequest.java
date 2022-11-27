package Request;

import DemoApp.model.Transaction;


import java.sql.Date;
import java.util.List;

public class StatisticsRequest extends Transaction{

	public String dateFrom;
	public String dateTo;
	public String role;

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getsType() {
		return role;
	}

	public void setsType(String role) {
		this.role = role;
	}
}
