package Request;

public class ShopCartRequest {
	private int id;
	private int userID;
	private int idFlower;
	private int quantity;
	private int amount;
	private int isOrdered;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getIsOrdered() {
		return isOrdered;
	}
	public void setIsOrdered(int isOrdered) {
		this.isOrdered = isOrdered;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	private int isDeleted;
	
}
