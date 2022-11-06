package Request;

public class FlowerRequest {
	private int id;
	private String keyword;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private double price;
	private String contents;
	private int discount;
	private String listImage;
	private String listColor;
	private String listCategory;
	private int quantityIn;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getListImage() {
		return listImage;
	}
	public void setListImage(String listImage) {
		this.listImage = listImage;
	}
	public String getListColor() {
		return listColor;
	}
	public void setListColor(String listColor) {
		this.listColor = listColor;
	}
	public String getListCategory() {
		return listCategory;
	}
	public void setListCategory(String listCategory) {
		this.listCategory = listCategory;
	}
	public int getQuantityIn() {
		return quantityIn;
	}
	public void setQuantityIn(int quantityIn) {
		this.quantityIn = quantityIn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
