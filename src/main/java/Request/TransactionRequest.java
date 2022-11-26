package Request;

import java.util.List;

import DemoApp.model.Transaction;

public class TransactionRequest extends Transaction{

	public TransactionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	private int shopCartId;
	public int getShopCartId() {
		return shopCartId;
	}

	public void setShopCartId(int shopCartId) {
		this.shopCartId = shopCartId;
	}


	private List<OrderRequest> lstOrderRequest;

	public List<OrderRequest> getLstOrderRequest() {
		return lstOrderRequest;
	}

	public void setLstOrderRequest(List<OrderRequest> lstOrderRequest) {
		this.lstOrderRequest = lstOrderRequest;
	}

}
