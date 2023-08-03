
package TimeTable.Common;

import java.io.Serializable;
import java.sql.Date;

public class Time
 {
	private int nam;
	private int startSaat;
	private int endSaat;
	private Serializable id;
	private int startDaghighe;
	private int endDaghighe;
	
	
	
	
	@Override
	public String toString() {
		return "تایم: " + nam + "    شروع: " + startSaat +":"+startDaghighe+ "    اتمام: " + endSaat+":"+endDaghighe ;
	}
	
	
	
	public String toString2() {
		return "تایم: " + nam + "    شروع: " + startSaat +":"+startDaghighe+ "    اتمام: " + endSaat+":"+endDaghighe ;
	}




	public int getNam() {
		return nam;
	}




	public void setNam(int nam) {
		this.nam = nam;
	}




	public int getStartSaat() {
		return startSaat;
	}




	public void setStartSaat(int startSaat) {
		this.startSaat = startSaat;
	}




	public int getEndSaat() {
		return endSaat;
	}




	public void setEndSaat(int endSaat) {
		this.endSaat = endSaat;
	}




	public Serializable getId() {
		return id;
	}




	public void setId(Serializable id) {
		this.id = id;
	}




	public int getStartDaghighe() {
		return startDaghighe;
	}




	public void setStartDaghighe(int startDaghighe) {
		this.startDaghighe = startDaghighe;
	}




	public int getEndDaghighe() {
		return endDaghighe;
	}




	public void setEndDaghighe(int endDaghighe) {
		this.endDaghighe = endDaghighe;
	}

	
	
	
	
	
	
	
	
}
