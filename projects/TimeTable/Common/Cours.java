package TimeTable.Common;

import java.io.Serializable;


public class Cours implements Serializable{


	
	
	private String nam;
	private String code;
	private double faselesaat;
	private double tedadjalasat;
	private double faseleruz;
	private int zojfard;
	private Serializable id;
	private Serializable OstadId;
	private String OstadName;
	private String OstadFamily;
	private String className;
	private int goruh;
//	private int vorudiid;

	private int term;
	private String specialClass;
	private int specialClassId;
	
	
	
	
	
	
	

	public String getSpecialClass() {
		return specialClass;
	}

	public void setSpecialClass(String specialClass) {
		this.specialClass = specialClass;
	}

	public int getSpecialClassId() {
		return specialClassId;
	}

	public void setSpecialClassId(int specialClassId) {
		this.specialClassId = specialClassId;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
	
	
	

	

	public Serializable getOstadId() {
		return OstadId;
	}

	public void setOstadId(Serializable ostadId) {
		OstadId = ostadId;
	}

	

	public int getGoruh() {
		return goruh;
	}

	public void setGoruh(int goruh) {
		this.goruh = goruh;
	}

	public String getOstadName() {
		return OstadName;
	}

	public void setOstadName(String ostadName) {
		OstadName = ostadName;
	}

	public String getOstadFamily() {
		return OstadFamily;
	}

	public void setOstadFamily(String ostadFamily) {
		OstadFamily = ostadFamily;
	}

	@Override
	public String toString() {
		return "نام درس: " + nam + "    کد درس: " + code + "    تعداد جلسات: " + tedadjalasat
				+  "    استاد: " + OstadName+"  "+OstadFamily +"    گروه: "+goruh;
	}
	
	public String toString2() {
		return "نام درس: " + nam + "    کد درس: " + code + "    تعداد جلسات: " + tedadjalasat
				+  "    استاد: " + OstadName+"  "+OstadFamily +"    گروه: "+goruh;
	}
	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

	


	public double getFaselesaat() {
		return faselesaat;
	}

	public void setFaselesaat(double faselesaat) {
		this.faselesaat = faselesaat;
	}

	public double getTedadjalasat() {
		return tedadjalasat;
	}

	public void setTedadjalasat(double tedadjalasat) {
		this.tedadjalasat = tedadjalasat;
	}

	public double getFaseleruz() {
		return faseleruz;
	}

	public void setFaseleruz(double faseleruz) {
		this.faseleruz = faseleruz;
	}

	public int getZojfard() {
		return zojfard;
	}

	public void setZojfard(int zojfard) {
		this.zojfard = zojfard;
	}

	

	

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	
}
