
package TimeTable.DataAcsses;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import TimeTable.Common.Celas;
import TimeTable.Common.Cours;




public class ClassManager {
	
public static int currentCelasID;

	public static void Insert(Celas objMember) {

		String sakhteman;
		try {
			PreparedStatement r = DBConnector.getConnection().prepareStatement(
					"insert into class(nam,sakhtemanid,isspecial)"
							+ "values(?,?,?);");
			r.setString(1, objMember.getNam());
			
			sakhteman = objMember.getSakhtemanName();
			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet rs2 = statement2.executeQuery("select id from sakhteman where nam=" + "'" + sakhteman + "'" + "");
			while (rs2.next()) {
				objMember.setSakhtemanid(rs2.getInt("id"));
			}
			
			r.setInt(2, objMember.getSakhtemanid());
			r.setBoolean(3, objMember.isSpecial());
			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<Celas> selectAll()
	{
		List<Celas> Classes = new ArrayList<Celas>();
		String sakhteman;
		try
		{
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement.executeQuery("select * from class;");
			while (rs1.next())
			{
				Celas b = new Celas();
				b.setNam(rs1.getString("nam"));
				b.setId(rs1.getInt("id"));
b.setSpecial(rs1.getBoolean("isspecial"));
				sakhteman=rs1.getInt("sakhtemanid")+"";
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet	rs2=statement2.executeQuery("select nam from sakhteman where id="+sakhteman+"");
				while (rs2.next()){
				b.setSakhtemanName(rs2.getString("nam"));}
				Classes.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Classes;
	}// End of SelectAll

	
	public static List<Celas> selectAllSpecial()
	{
		List<Celas> Classes = new ArrayList<Celas>();
		String sakhteman;
		try
		{
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement.executeQuery("select * from class where isspecial=true;");
			while (rs1.next())
			{
				Celas b = new Celas();
				b.setNam(rs1.getString("nam"));
				b.setId(rs1.getInt("id"));
b.setSpecial(rs1.getBoolean("isspecial"));
				sakhteman=rs1.getInt("sakhtemanid")+"";
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet	rs2=statement2.executeQuery("select nam from sakhteman where id="+sakhteman+"");
				while (rs2.next()){
				b.setSakhtemanName(rs2.getString("nam"));}
				Classes.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Classes;
	}// End of SelectAll

	
	public static void delete(String s){
		try
		{
			PreparedStatement statement =  DBConnector.getConnection().prepareStatement("delete from class where nam="+"'"+s+"'"+";");
			statement.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void Edit(Celas celas) {

		String sakhteman = "";
		int id = 0;
		try {

			celas.setId(currentCelasID);

			sakhteman = celas.getSakhtemanName();
			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet r = statement2.executeQuery("select id  from sakhteman where nam=" + "'" + sakhteman + "'" + "");
			while (r.next()) {
				celas.setSakhtemanid(r.getInt("id"));
				
			}


			// Statement statement =
			// DBConnector.getConnection().createStatement();
			PreparedStatement statement11 = DBConnector.getConnection().prepareStatement("update class set nam =" + "'"
					+ celas.getNam() + "'" + "  where id=" + "'" + celas.getId() + "'" + "");
			PreparedStatement statement99 = DBConnector.getConnection()
					.prepareStatement("update class set sakhtemanid =" + "'" + celas.getSakhtemanid() + "'"
							+ "  where id=" + "'" + celas.getId() + "'" + "");
			PreparedStatement statement44 = DBConnector.getConnection()
					.prepareStatement("update class set isspecial =" + "'" + celas.isSpecial() + "'"
							+ "  where id=" + "'" + celas.getId() + "'" + "");
			

			statement11.execute();
			statement99.execute();
			statement44.execute();

			System.out.println("Edited");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public static int getClassCount() throws SQLException{
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select count(*) from class;");
		int c=0;
		while (r.next()) 
			c=r.getInt("count");
			
		return c;
		}

	public static int getClassId(String s) throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select id from class where nam='"+s+"';");
		int c=0;
		while (r.next()) 
			c=r.getInt("id");
			
		return c;
	}
	
	public static String getClassName(int s) throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select Nam from class where id='"+s+"';");
		String c="";
		while (r.next()) 
			c=r.getString("nam");
			
		return c;
	}
	
	public static boolean isSpecialClass(int s) throws SQLException {
		if(s==18)
			return false;
		else
			return true;
		
//		Statement statement2 = DBConnector.getConnection().createStatement();
//		ResultSet r = statement2.executeQuery("select isspecial from class where id='"+s+"';");
//		boolean c=false;
//		while (r.next()) 
//			c=r.getBoolean("isspecial");
//			
//		return c;
	}

	public static ArrayList<Celas> selectCurrentClass(String t) {
		List<Celas> Classes = new ArrayList<Celas>();
		
		String sakhteman;
		try
		{
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement.executeQuery("select * from class where nam='"+t+"';");
			while (rs1.next())
			{
				Celas b = new Celas();
				b.setNam(rs1.getString("nam"));
				b.setId(rs1.getInt("id"));

				b.setSpecial(rs1.getBoolean("isspecial"));
				sakhteman=rs1.getInt("sakhtemanid")+"";
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet	rs2=statement2.executeQuery("select nam from sakhteman where id="+sakhteman+"");
				while (rs2.next()){
				b.setSakhtemanName(rs2.getString("nam"));}
				Classes.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return (ArrayList<Celas>) Classes;
	}
	
}
