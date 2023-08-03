package TimeTable.DataAcsses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TimeTable.Common.Celas;
import TimeTable.Common.DayTime;

public class DayTimeManager {
	


	public static void Insert(DayTime objMember) {

		int timeName,sal;
		try {
			PreparedStatement r = DBConnector.getConnection().prepareStatement(
					"insert into daytime(day,timeid,vorudiid)"
							+ "values(?,?,?);");
			r.setString(1, objMember.getDay());
			
			sal=objMember.getVorudiSal();
			Statement statement23 = DBConnector.getConnection().createStatement();
			ResultSet rs23 = statement23.executeQuery("select id from vorudi where sal=" + "'" + sal + "'" + "");
			while (rs23.next()) {
				objMember.setVorudiid(rs23.getInt("id"));
			}
			
			timeName = objMember.getTimeName();
			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet rs2 = statement2.executeQuery("select id from times where nam=" + "'" + timeName + "'" + "");
			while (rs2.next()) {
				objMember.setTimeID(rs2.getInt("id"));
			}
			
			r.setInt(2, (int) objMember.getTimeID());
			r.setInt(3, (int) objMember.getVorudiid());
			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<DayTime> selectAllBySal(int sal)
	{

		List<DayTime> Classes = new ArrayList<DayTime>();
		int timeName,vorudiid=0;
		
		try
		{
			
			Statement statement24 = DBConnector.getConnection().createStatement();
			ResultSet	rs24=statement24.executeQuery("select id from vorudi where sal="+sal+"");
			while (rs24.next()){
			vorudiid=(rs24.getInt("id"));}
			
			
		
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement.executeQuery("select * from daytime where vorudiid='"+vorudiid+"';");
			while (rs1.next())
			{
				DayTime b = new DayTime();
				b.setDay(rs1.getString("day"));
				b.setId(rs1.getInt("id"));
                b.setTimeID(rs1.getInt("timeid"));
                b.setVorudiid(rs1.getInt("vorudiid"));
                
            
			
                
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet	rs2=statement2.executeQuery("select nam from times where id="+b.getTimeID()+"");
				while (rs2.next()){
				b.setTimeName(rs2.getInt("nam"));}
				Classes.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Classes;
	
	}
	
	public static List<DayTime> selectAllById(int vorudi)
	{
		List<DayTime> Classes = new ArrayList<DayTime>();
		int timeName;
		try
		{
			
		
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement.executeQuery("select * from daytime where vorudiid='"+vorudi+"';");
			while (rs1.next())
			{
				DayTime b = new DayTime();
				b.setDay(rs1.getString("day"));
				b.setId(rs1.getInt("id"));
                b.setTimeID(rs1.getInt("timeid"));
                b.setVorudiid(rs1.getInt("vorudiid"));
                
            
			
                
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet	rs2=statement2.executeQuery("select nam from times where id="+b.getTimeID()+"");
				while (rs2.next()){
				b.setTimeName(rs2.getInt("nam"));}
				Classes.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Classes;
	}// End of SelectAll


	public static List<DayTime> selectAll()
	{
		List<DayTime> Classes = new ArrayList<DayTime>();
		int timeName,vorudi=0;
		try
		{
			
			
			
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement.executeQuery("select * from daytime ");//where vorudiid='"+vorudi+"';");
			while (rs1.next())
			{
				DayTime b = new DayTime();
				b.setDay(rs1.getString("day"));
				b.setId(rs1.getInt("id"));
                b.setTimeID(rs1.getInt("timeid"));
                b.setVorudiid(rs1.getInt("vorudiid"));
                
            
				///b.setVorudiSal(sal);
                
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet	rs2=statement2.executeQuery("select nam from times where id="+b.getTimeID()+"");
				while (rs2.next()){
				b.setTimeName(rs2.getInt("nam"));}
				Classes.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Classes;
	}// End of SelectAll

	
	public static void delete(DayTime dt){
		try
		{
			
			
			int timeName = dt.getTimeName();
			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet rs2 = statement2.executeQuery("select id from times where nam=" + "'" + timeName + "'" + "");
			while (rs2.next()) {
				dt.setTimeID(rs2.getInt("id"));
			}
		
			PreparedStatement state = DBConnector.getConnection().prepareStatement("delete from daytime where day="+"'"+dt.getDay()+"'"+" and timeid="+"'"+dt.getTimeID()+"'"+";");
		
			state.execute();

			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	
	public static void Edit(DayTime celas) {

		int time = 0,sal;
		int id = 0;
		try {

		//	celas.setId(currentCelasID);

			sal = celas.getVorudiSal();
			Statement statement23 = DBConnector.getConnection().createStatement();
			ResultSet r3 = statement23.executeQuery("select id  from vorudi where sal=" + "'" + sal + "'" + "");
			while (r3.next()) {
				celas.setVorudiid(r3.getInt("id"));
				
			}
			
			time = celas.getTimeName();
			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet r = statement2.executeQuery("select id  from times where nam=" + "'" + time + "'" + "");
			while (r.next()) {
				celas.setTimeID(r.getInt("id"));
				
			}


			// Statement statement =
			// DBConnector.getConnection().createStatement();
			PreparedStatement statement11 = DBConnector.getConnection().prepareStatement("update daytime set timeid =" + "'"
					+ celas.getTimeID() + "'" + "   where vorudiid=" + "'" + celas.getVorudiid()+ "'" + "");
		
			statement11.execute();
			PreparedStatement statement12 = DBConnector.getConnection().prepareStatement("update daytime set day =" + "'"
					+ celas.getDay()+ "'" + "  where vorudiid=" + "'" + celas.getVorudiid() + "'" + "");
		
			statement11.execute();
			statement12.execute();
			

			System.out.println("Edited");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public static void deleteAll(int sal) throws SQLException{
		
		
int vorudiid =0;
		Statement statement23 = DBConnector.getConnection().createStatement();
		ResultSet r3 = statement23.executeQuery("select id  from vorudi where sal=" + "'" + sal + "'" + "");
		while (r3.next()) {
			vorudiid=(r3.getInt("id"));
			
		}
		
		PreparedStatement state = DBConnector.getConnection().prepareStatement("delete from daytime where vorudiid='"+vorudiid+"';");
		
		state.execute();
	}

	public static boolean check(String ruz, int time, int tedadJalasat,int vorudi) {
	int q=0,p=0;
		ArrayList<DayTime> d=(ArrayList<DayTime>) selectAllBySal(92);
		for(int i=0;i<d.size();i++){
			if(d.get(i).getDay().matches(ruz)){
				q++;
				if(d.get(i).getTimeName()==time){
					p=d.get(i).getTimeName();
				}
			}
		
		}
		
		if((q-p)>=tedadJalasat)
			return true;
		else
		return false;
	}
	
}
