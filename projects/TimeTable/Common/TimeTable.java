package TimeTable.Common;

import java.io.Serializable;

public class TimeTable {

	private Serializable classid;
	private Serializable darsid;
	private Serializable timeid;
	private String ruz;
	
	private String className;
	private String timeName;
	public Serializable getClassid() {
		return classid;
	}
	public void setClassid(Serializable classid) {
		this.classid = classid;
	}
	public Serializable getDarsid() {
		return darsid;
	}
	public void setDarsid(Serializable darsid) {
		this.darsid = darsid;
	}
	public Serializable getTimeid() {
		return timeid;
	}
	public void setTimeid(Serializable timeid) {
		this.timeid = timeid;
	}
	public String getRuz() {
		return ruz;
	}
	public void setRuz(String ruz) {
		this.ruz = ruz;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTimeName() {
		return timeName;
	}
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}
	
	
	
}
