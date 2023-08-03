package TimeTable.test;

public class celas {

	public int id;
	public String nam;
	public day D[]=new day[6];
	
	public celas() {

		for(int j=0;j<6;j++){
			D[j]=new day();
			
		}
		
		D[0].nam="shanbe";
		D[1].nam="1shanbe";
		D[2].nam="2shanbe";
		D[3].nam="3shanbe";
		D[4].nam="4shanbe";
		D[5].nam="5shanbe";
		D[6].nam="jome";

	}

}
