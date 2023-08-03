package TimeTable.Common;

import java.io.Serializable;
import java.sql.SQLException;

import TimeTable.DataAcsses.coursmanager;

public class Olaviat {

	
	private Serializable celasID;
	private Serializable timesID;
	private Serializable darsID;
	private boolean ruzp;
	private boolean celasp;
	private boolean timesp;
	private int saat;
	private String ruz;
	private String celasName;
	private int timeName;
	private int shomare;
	public int getTerm() {
		return term;
	}

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
	




	public void setTerm(int term) {
		this.term = term;
	}
	private int isZojFard;
	private int tedadJalasat;
	private Serializable id;
	private String darsName;
//	private int vorudiid;
//	private int vorudisal;
	private int term;
	
	
	
	
	
	
	
	
	
	
	
	




//	public int getVorudiid() {
//		return vorudiid;
//	}
//
//
//
//
//
//	public void setVorudiid(int vorudiid) {
//		this.vorudiid = vorudiid;
//	}





//	public int getVorudisal() {
//		return vorudisal;
//	}
//
//
//
//
//
//	public void setVorudisal(int vorudisal) {
//		this.vorudisal = vorudisal;
//	}





	public String getDarsName() {
		return darsName;
	}





	public void setDarsName(String darsName) {
		this.darsName = darsName;
	}





	public Serializable getId() {
		return id;
	}





	public void setId(Serializable id) {
		this.id = id;
	}





	public int getTedadJalasat() {
		return tedadJalasat;
	}





	public void setTedadJalasat(int tedadJalasat) {
		this.tedadJalasat = tedadJalasat;
	}





	public int isZojFard() {
		return isZojFard;
	}





	public void setZojFard(int isZojFard) {
		this.isZojFard = isZojFard;
	}





	public Olaviat(int celasID2, String ruz2, int timeNam,int timeId ,int DarsId,int saat,int zojfard,int vorudi,boolean ruzp,boolean classp,boolean timep) {
		this.setCelasID(celasID2);
		this.setRuz(ruz2);
		this.setTimeName(timeNam);
		this.darsID=DarsId;
		this.saat=saat;
		this.timesID=timeId;
		this.setZojFard(zojfard);
		//this.vorudiid=vorudi;
		this.ruzp=ruzp;
		this.celasp=classp;
		this.timesp=timep;
	}





	public Olaviat() {
	}





	public Olaviat(Integer dars, int saat) {

		this.darsID=dars;
		this.saat=saat;
	}





	@Override
	public String toString() {
		try {
			darsName=coursmanager.getName((int)darsID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return "Olaviat [celasID=" + celasID + ", darsName=" + darsName +  ", time=" + timeName +", darsID=" + darsID + ", ruzp=" + ruzp
					+ ", celasp=" + celasp + ", timesp=" + timesp + ", saat=" + saat + ", ruz=" + ruz + ", celasName="
					+ celasName + ", timeName=" + timeName + "]";
	
		
	}
	
	
	
	
	
	public int getShomare() {
		return shomare;
	}





	public void setShomare(int shomare) {
		this.shomare = shomare;
	}





	public Serializable getDarsID() {
		return darsID;
	}
	public void setDarsID(Serializable darsID) {
		this.darsID = darsID;
	}
	public String getCelasName() {
		return celasName;
	}
	public void setCelasName(String celasName) {
		this.celasName = celasName;
	}
	public int getTimeName() {
		return timeName;
	}
	public void setTimeName(int timeName) {
		this.timeName = timeName;
	}
	public Serializable getCelasID() {
		return celasID;
	}
	public void setCelasID(Serializable celasID) {
		this.celasID = celasID;
	}
	public Serializable getTimesID() {
		return timesID;
	}
	public void setTimesID(Serializable timesID) {
		this.timesID = timesID;
	}
	public boolean isRuzp() {
		return ruzp;
	}
	public void setRuzp(boolean ruzp) {
		this.ruzp = ruzp;
	}
	public boolean isCelasp() {
		return celasp;
	}
	public void setCelasp(boolean celasp) {
		this.celasp = celasp;
	}
	public boolean isTimesp() {
		return timesp;
	}
	public void setTimesp(boolean timesp) {
		this.timesp = timesp;
	}
	public int getSaat() {
		return saat;
	}
	public void setSaat(int saat) {
		this.saat = saat;
	}
	public String getRuz() {
		return ruz;
	}
	public void setRuz(String ruz) {
		this.ruz = ruz;
	}
	
	
	
	
	
	

	
	
	
}
