package TimeTable.Common;


import java.io.Serializable;
import java.sql.Date;

public class Celas
 {
	private String nam;
	private int sakhtemanid;
	private Serializable id;
	private String SakhtemanName;
	private boolean isSpecial;
	

	
	
	
	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}

	public Celas(Serializable celasID,String nam) {
this.nam=nam;
this.id=celasID;
	}

	public Celas(Serializable celasID) {
		this.id=celasID;
			}

	
	public Celas() {
		// TODO Auto-generated constructor stub
	}

	public String getSakhtemanName() {
		return SakhtemanName;
	}

	public void setSakhtemanName(String sakhtemanName) {
		SakhtemanName = sakhtemanName;
	}

	@Override
	public String toString() {
		return "کلاس: " + nam + "      ساختمان: " + SakhtemanName + "";
	}
	
	public String toString2() {
		return "کلاس: " + nam + " \n      ساختمان: " + SakhtemanName + "";
	}
	
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public int getSakhtemanid() {
		return sakhtemanid;
	}
	public void setSakhtemanid(int sakhtemanid) {
		this.sakhtemanid = sakhtemanid;
	}
	public Serializable getId() {
		return id;
	}
	public void setId(Serializable id) {
		this.id = id;
	}
	
	
	
	
	
	
	
	
	
	
}
