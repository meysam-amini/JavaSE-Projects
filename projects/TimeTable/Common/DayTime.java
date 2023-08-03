package TimeTable.Common;

import java.io.Serializable;

public class DayTime {

	
	private Serializable id;
	private Serializable timeID;
	private int TimeName;
	private String day;
	private int vorudiid;
	private int vorudiSal;
	
	
	
	
	
	
	public int getVorudiid() {
		return vorudiid;
	}
	public void setVorudiid(int vorudiid) {
		this.vorudiid = vorudiid;
	}
	public int getVorudiSal() {
		return vorudiSal;
	}
	public void setVorudiSal(int vorudiSal) {
		this.vorudiSal = vorudiSal;
	}
	public DayTime(String ruz, Serializable timesID) {
		this.day=ruz;
		this.timeID=timesID;
	}
	public DayTime() {
		// TODO Auto-generated constructor stub
	}
	public int getTimeName() {
		return TimeName;
	}
	public void setTimeName(int timeName) {
		TimeName = timeName;
	}
	public Serializable getId() {
		return id;
	}
	public void setId(Serializable id) {
		this.id = id;
	}
	public Serializable getTimeID() {
		return timeID;
	}
	public void setTimeID(Serializable timeID) {
		this.timeID = timeID;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	
	
}
