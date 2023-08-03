package TimeTable.Common;

import java.io.Serializable;
import java.sql.Date;


public class Ostad
 {
	private String nam;
	private String family;
	private String code;
	private Serializable id;
	
	
	
	
	@Override
	public String toString() {
		return "نام: " + nam + "   "+"\n"+"   نام خانوادگی: " + family + "      کد: " + code + "";
	}
	
	
	public String toString2() {
		return "نام: " + nam + "    نام خانوادگی: " + family + "      کد: " + code + "";
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
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
	
	
	
	
	
	
	
}
