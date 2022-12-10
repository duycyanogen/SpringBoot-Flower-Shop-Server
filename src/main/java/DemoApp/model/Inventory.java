package DemoApp.model;

import java.util.Date;
import java.util.List;

public class Inventory {

	public String getGroupingByKey(Inventory p){
		return p.getItemID()+"-"+p.getLockinventoryid();
		}
	private int inventoryID;
	private int inventoryOrderID;
	private int inventoryTermID;
	private int inventoryAreaID;
	private String productID;
	private int storeID;
	private int inventoryStatusID;
	private double stockQuantity;
	private double inputingQuantity;
	private int checkQuantity;
	private Date checkedDate;
	private String checkedUser;
	private String note;
	private int finishStatus;
	private String createdUser;
	private Date createdDate;
	private String updatedUser;
	private Date updatedDate;
	private double stockQuantityLocation;
	private double totalStockQuantityLogistic;
	private int isDeleted;
	private String deletedUser;
	private Date deletedDate;
	private int checkedStatus;
	private int deletedStatus;
	String lockinventoryid;
	String inventoryidlist;
	private String processedUser;
	private Date processedDate;
	private String productName;
	private String itemID;
	private String itemName;
	private Date inventoryDate;
	private int subGroupID;
	public int getSubGroupID() {
		return subGroupID;
	}

	public void setSubGroupID(int subGroupID) {
		this.subGroupID = subGroupID;
	}

	public String getInventoryidlist() {
		return inventoryidlist;
	}

	public void setInventoryidlist(String inventoryidlist) {
		this.inventoryidlist = inventoryidlist;
	}

	public String getLockinventoryid() {
		return lockinventoryid;
	}

	public void setLockinventoryid(String lockinventoryid) {
		this.lockinventoryid = lockinventoryid;
	}

	public int getInventoryID() {
		return inventoryID;
	}
	
	public String getToStringInventoryID() {
		return Integer.toString(inventoryID);
	}

	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

	public int getInventoryOrderID() {
		return inventoryOrderID;
	}

	public void setInventoryOrderID(int inventoryOrderID) {
		this.inventoryOrderID = inventoryOrderID;
	}

	public int getInventoryTermID() {
		return inventoryTermID;
	}

	public void setInventoryTermID(int inventoryTermID) {
		this.inventoryTermID = inventoryTermID;
	}

	public int getInventoryAreaID() {
		return inventoryAreaID;
	}

	public void setInventoryAreaID(int inventoryAreaID) {
		this.inventoryAreaID = inventoryAreaID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getInventoryStatusID() {
		return inventoryStatusID;
	}

	public void setInventoryStatusID(int inventoryStatusID) {
		this.inventoryStatusID = inventoryStatusID;
	}

	public double getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public double getInputingQuantity() {
		return inputingQuantity;
	}

	public void setInputingQuantity(int inputingQuantity) {
		this.inputingQuantity = inputingQuantity;
	}

	public int getCheckQuantity() {
		return checkQuantity;
	}

	public void setCheckQuantity(int checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

//	public Date getCheckedDate() {
//		return checkedDate;
//	}
//	public void setCheckedDate(Date checkedDate) {
//		this.checkedDate = checkedDate;
//	}

	public String getCheckedUser() {
		return checkedUser;
	}

	public void setCheckedUser(String checkedUser) {
		this.checkedUser = checkedUser;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getFinishStatus() {
		return finishStatus;
	}

	public void setFinishStatus(int finishStatus) {
		this.finishStatus = finishStatus;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getDeletedUser() {
		return deletedUser;
	}

	public void setDeletedUser(String deletedUser) {
		this.deletedUser = deletedUser;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public int getCheckedStatus() {
		return checkedStatus;
	}

	public void setCheckedStatus(int checkedStatus) {
		this.checkedStatus = checkedStatus;
	}

	public int getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(int deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public Date getCheckedDate() {
		return checkedDate;
	}

	public void setCheckedDate(Date checkedDate) {
		this.checkedDate = checkedDate;
	}

	public double getStockQuantityLocation() {
		return stockQuantityLocation;
	}

	public void setStockQuantityLocation(double stockQuantityLocation) {
		this.stockQuantityLocation = stockQuantityLocation;
	}

	public double getTotalStockQuantityLogistic() {
		return totalStockQuantityLogistic;
	}

	public void setTotalStockQuantityLogistic(double totalStockQuantityLogistic) {
		this.totalStockQuantityLogistic = totalStockQuantityLogistic;
	}



	public String getProcessedUser() {
		return processedUser;
	}

	public void setProcessedUser(String processedUser) {
		this.processedUser = processedUser;
	}

	public Date getProcessedDate() {
		return processedDate;
	}

	public void setProcessedDate(Date processedDate) {
		this.processedDate = processedDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}

}
