package DemoApp.model;

public class TransactionDetail {
	private int id;
	private int transactionID;
	private int idFlower;
	private int quantity;
	private double amount;
	private int isCanceled;
	private String flowerName;
	private String imagesName;
	private int transactionStatus;
	private String transacitionStatusName;
	public String getTransacitionStatusName() {
		return transacitionStatusName;
	}
	public void setTransacitionStatusName(String transacitionStatusName) {
		this.transacitionStatusName = transacitionStatusName;
	}
	private String imageURL;
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(int transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getIdFlower() {
		return idFlower;
	}
	public void setIdFlower(int idFlower) {
		this.idFlower = idFlower;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getIsCanceled() {
		return isCanceled;
	}
	public void setIsCanceled(int isCanceled) {
		this.isCanceled = isCanceled;
	}
	public String getFlowerName() {
		return flowerName;
	}
	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}
	public String getImagesName() {
		return imagesName;
	}
	public void setImagesName(String imagesName) {
		this.imagesName = imagesName;
	}

}
