package TimeTable.DataAcsses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TimeTable.Common.Celas;
import TimeTable.Common.Sakhteman;
import TimeTable.Common.Sakhteman;


public class SakhtemanManager {

	public static int currentSakhtemanID;
	
	public static void Insert(Sakhteman objMember) {

		try {
			PreparedStatement r = DBConnector.getConnection().prepareStatement(
					"insert into sakhteman(nam)"
							+ "values(?);");
			r.setString(1, objMember.getNam());
			
			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<Sakhteman> selectAll()
	{
		List<Sakhteman> Sakhtemans = new ArrayList<Sakhteman>();
		
		try
		{
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from sakhteman;");
			while (rs.next())
			{
				Sakhteman b = new Sakhteman();
				b.setNam(rs.getString("nam"));
				b.setId(rs.getInt("id"));

				
				Sakhtemans.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Sakhtemans;
	}// End of SelectAll

	public static void delete(String s){
		try
		{
			PreparedStatement statement =  DBConnector.getConnection().prepareStatement("delete from Sakhteman where nam="+"'"+s+"'"+";");
			statement.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	public static void Edit(Sakhteman celas) {

		String sakhteman = "";
		int id = 0;
		try {

			celas.setId(currentSakhtemanID);

			


			PreparedStatement statement11 = DBConnector.getConnection().prepareStatement("update Sakhteman set nam =" + "'"
					+ celas.getNam() + "'" + "  where id=" + "'" + celas.getId() + "'" + "");
		
			statement11.execute();

			System.out.println("Edited");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
