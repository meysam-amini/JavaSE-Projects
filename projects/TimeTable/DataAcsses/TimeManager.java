package TimeTable.DataAcsses;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TimeTable.Common.Ostad;
import TimeTable.Common.Time;


public class TimeManager {

	
	public static int currentTimeID;
	
	public static void Insert(Time objMember) {

		try {
			PreparedStatement r = DBConnector.getConnection().prepareStatement(
					"insert into times(nam,startsaat,startdaghighe,endsaat,enddaghighe)"
							+ "values(?,?,?,?,?);");
			r.setInt(1, objMember.getNam());
			r.setInt(2, objMember.getStartSaat());
			r.setInt(3, objMember.getStartDaghighe());
			r.setInt(4, objMember.getEndSaat());
			r.setInt(5, objMember.getEndDaghighe());
			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<Time> selectAll()
	{
		List<Time> Times = new ArrayList<Time>();
		
		try
		{
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from times order by nam;");
			while (rs.next())
			{
				Time b = new Time();
				b.setNam(rs.getInt("nam"));
				b.setStartSaat(rs.getInt("startsaat"));
				b.setStartDaghighe(rs.getInt("startdaghighe"));
				b.setEndSaat(rs.getInt("endsaat"));
				b.setEndDaghighe(rs.getInt("enddaghighe"));
				b.setId(rs.getInt("id"));

				
				Times.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Times;
	}// End of SelectAll

	public static void delete(String s){
		try
		{
			int timeID = 0;
			
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select id from times where nam="+"'"+s+"'"+";");
			
			while(rs.next()){
				timeID=rs.getInt("id");
			}System.out.println("mnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
			
			PreparedStatement statement11 = DBConnector.getConnection().prepareStatement("delete from daytime where timeid="+"'"+timeID+"'"+";");

			PreparedStatement statement12 = DBConnector.getConnection().prepareStatement("delete from Times where nam="+"'"+s+"'"+";");
			
			PreparedStatement statement13 = DBConnector.getConnection().prepareStatement("delete from olaviat where timesid="+"'"+timeID+"'"+";");
			
			statement13.execute();
			statement11.execute();
			statement12.execute();
			
			System.out.println("Deleted");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void Edit(Time time) {

		try {

		

		


			
			PreparedStatement statement22 = DBConnector.getConnection().prepareStatement("update times set startsaat =" + "'"
					+ time.getStartSaat() + "'" + "  where nam=" + "'" + time.getNam() + "'" + "");
		
			PreparedStatement statement33 = DBConnector.getConnection().prepareStatement("update times set startdaghighe =" + "'"
					+ time.getStartDaghighe() + "'" + "   where nam=" + "'" + time.getNam() + "'" + "");
		
			PreparedStatement statement44 = DBConnector.getConnection().prepareStatement("update times set endsaat =" + "'"
					+ time.getEndSaat() + "'" + "   where nam=" + "'" + time.getNam() + "'" + "");
		
			PreparedStatement statement55 = DBConnector.getConnection().prepareStatement("update times set enddaghighe =" + "'"
					+ time.getEndDaghighe() + "'" + "   where nam=" + "'" + time.getNam() + "'" + "");
		
			
		
			
			statement22.execute();
			statement33.execute();
			statement44.execute();
			statement55.execute();
		

			System.out.println("Edited");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int getTimeCount() throws SQLException{
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select count(*) from times;");
		int c=0;
		while (r.next()) 
			c=r.getInt("count");
			
		return c;
		}
	
}
