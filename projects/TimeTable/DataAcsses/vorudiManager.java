
package TimeTable.DataAcsses;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import TimeTable.Common.Vorudi;
import TimeTable.Common.Cours;




public class vorudiManager {
	
public static int currentVorudiID;

	public static void Insert(Vorudi objMember) {

		String sakhteman;
		try {
			PreparedStatement r = DBConnector.getConnection().prepareStatement(
					"insert into vorudi(sal)"
							+ "values(?);");
			r.setInt(1, objMember.getSal());
			
			
			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static List<Vorudi> selectAll()
	{
		List<Vorudi> Classes = new ArrayList<Vorudi>();
	
		try
		{
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement.executeQuery("select * from vorudi;");
			while (rs1.next())
			{
				Vorudi b = new Vorudi();
				b.setSal(rs1.getInt("sal"));
				b.setId(rs1.getInt("id"));

				
				Classes.add(b);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return Classes;
	}// End of SelectAll

	
	public static void delete(int s){
		try
		{
			PreparedStatement statement =  DBConnector.getConnection().prepareStatement("delete from vorudi where sal="+"'"+s+"'"+";");
			statement.execute();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	
}
