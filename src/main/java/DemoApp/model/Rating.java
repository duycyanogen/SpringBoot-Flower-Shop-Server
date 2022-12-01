package DemoApp.model;

import java.util.Date;
import java.util.List;

public class Rating {
	private int id;
	private Date created;
	private double score;
	private int idFlower;
	private int userID;
	private String comment;
	private String ownerName;
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getIdFlower() {
		return idFlower;
	}
	public void setIdFlower(int idFlower) {
		this.idFlower = idFlower;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
