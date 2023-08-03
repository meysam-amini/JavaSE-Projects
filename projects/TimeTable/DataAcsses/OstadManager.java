package TimeTable.DataAcsses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TimeTable.Common.Celas;
import TimeTable.Common.Ostad;

public class OstadManager {

	public static int currentOstadID;
	
	public static void Insert(Ostad objMember) {

		try {
			PreparedStatement r = DBConnector.getConnection().prepareStatement(
					"insert into ostad(nam,family,code)"
							+ "values(?,?,?);");
			r.setString(1, objMember.getNam());
			r.setString(2, objMember.getFamily());
			r.setString(3, objMember.getCode());
			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<Ostad> selectAll()
	{
		List<Ostad> Ostads = new ArrayList<Ostad>();
		
		try
		{
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from ostad;");
			while (rs.next())
			{
				Ostad b = new Ostad();
				b.setNam(rs.getString("nam"));
				b.setFamily(rs.getString("family"));
				b.setCode(rs.getString("code"));
				b.setId(rs.getInt("id"));

				
				Ostads.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Ostads;
	}// End of SelectAll

	

	public static void delete(String s){
		try
		{
			
			PreparedStatement statement14 = DBConnector.getConnection().prepareStatement("delete from ostad where code="+"'"+s+"'"+";");
			statement14.execute();
			
					
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void Edit(Ostad ostad) {

		try {

			ostad.setId(currentOstadID);

		


			// Statement statement =
			// DBConnector.getConnection().createStatement();
			PreparedStatement statement11 = DBConnector.getConnection().prepareStatement("update ostad set nam =" + "'"
					+ ostad.getNam() + "'" + "  where id=" + "'" + ostad.getId() + "'" + "");
		
			PreparedStatement statement22 = DBConnector.getConnection().prepareStatement("update ostad set family =" + "'"
					+ ostad.getFamily() + "'" + "  where id=" + "'" + ostad.getId() + "'" + "");
		
			PreparedStatement statement33 = DBConnector.getConnection().prepareStatement("update ostad set code =" + "'"
					+ ostad.getCode() + "'" + "  where id=" + "'" + ostad.getId() + "'" + "");
		
			
			statement11.execute();
			statement22.execute();
			statement33.execute();
		

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
