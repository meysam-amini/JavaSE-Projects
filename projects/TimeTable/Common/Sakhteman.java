package TimeTable.Common;

import java.io.Serializable;

public class Sakhteman {

	
	private Serializable id;
	private String nam;
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return " " + nam + "";
	}
	
	public String toString2() {
		return " " + nam + "";
	}
	
	public Serializable getId() {
		return id;
	}
	public void setId(Serializable id) {
		this.id = id;
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	
	
}
