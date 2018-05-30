package rpc1.mongo.core;

import org.springframework.data.annotation.Id;

public class Media {
	@Id
	private int id;
	
	private int itemId;
	private String fileName;
	private String filePath;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItem_Id() {
		return itemId;
	}
	public void setItem_Id(int item_id) {
		this.itemId = item_id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void copy(Media m) {
		this.setId(m.getId());
		this.setItem_Id(m.getItem_Id());
		this.setFileName(m.getFileName());
		this.setFilePath(m.getFilePath());
	}
	
}
