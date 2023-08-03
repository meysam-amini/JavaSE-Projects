package TimeTable.DataAcsses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TimeTable.Common.Cours;
import TimeTable.Common.Olaviat;
import TimeTable.Common.Time;

public class OlaviatManager {

	public static int currentCoursID;

	public static void Insert(Olaviat olaviatOBJ, Cours coursOBJ) {
		try {
			PreparedStatement r = DBConnector.getConnection()
					.prepareStatement("insert into olaviat(darsid,classid,timesid,ruzp,classp,timesp,saat,ruz,shomare)"
							+ "values(?,?,?,?,?,?,?,?,?);");

			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select id from dars where code=" + "'" + coursOBJ.getCode() + "'"
					+ " and nam='" + coursOBJ.getNam() + "'and goruh=" + "'" + coursOBJ.getGoruh() + "'" + ";");

			while (rs.next())
				olaviatOBJ.setDarsID(rs.getInt("id"));

			r.setInt(1, (int) olaviatOBJ.getDarsID());

			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet rs2 = statement2
					.executeQuery("select id from times where nam=" + "'" + olaviatOBJ.getTimeName() + "'" + ";");

			while (rs2.next())
				r.setInt(3, rs2.getInt("id"));

			Statement statement3 = DBConnector.getConnection().createStatement();
			ResultSet rs3 = statement3
					.executeQuery("select id from class where nam=" + "'" + olaviatOBJ.getCelasName() + "'" + ";");

			while (rs3.next())
				olaviatOBJ.setCelasID(rs3.getInt("id"));

			r.setInt(2, (int) olaviatOBJ.getCelasID());

			r.setBoolean(4, olaviatOBJ.isRuzp());
			r.setBoolean(5, olaviatOBJ.isCelasp());
			r.setBoolean(6, olaviatOBJ.isTimesp());
			r.setInt(7, olaviatOBJ.getSaat());
			r.setString(8, olaviatOBJ.getRuz());
			r.setInt(9, olaviatOBJ.getShomare());

			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Olaviat> selectAll() {
		List<Olaviat> olaviat = new ArrayList<Olaviat>();

		int OstadId, celasId;

		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from olaviat;");
			while (rs.next()) {
				Olaviat o = new Olaviat();
				o.setRuz(rs.getString("ruz"));
				o.setCelasp(rs.getBoolean("classp"));
				o.setTimesp(rs.getBoolean("timesp"));
				o.setRuzp(rs.getBoolean("ruzp"));
				o.setSaat(rs.getInt("saat"));
				o.setTimesID(rs.getInt("timesid"));
				o.setCelasID(rs.getInt("classid"));
				o.setDarsID(rs.getInt("darsid"));

			//	o.setVorudiid(coursmanager.getVorudiid((int) o.getDarsID()));
o.setTerm(coursmanager.getVorudiid((int) o.getDarsID()));
				Statement statement3 = DBConnector.getConnection().createStatement();
				ResultSet rs3 = statement3
						.executeQuery("select nam from class where id=" + "'" + o.getCelasID() + "'" + ";");

				while (rs3.next())
					o.setCelasName(rs3.getString("nam"));

				Statement statement4 = DBConnector.getConnection().createStatement();
				ResultSet rs4 = statement4
						.executeQuery("select nam from times where id=" + "'" + o.getTimesID() + "'" + ";");

				while (rs4.next())
					o.setTimeName(rs4.getInt("nam"));

				olaviat.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return olaviat;
	}

	public static void delete(int id) {
		try {

			PreparedStatement statement = DBConnector.getConnection()
					.prepareStatement("delete from olaviat where darsid='"+id+"';");

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void Edit(Cours c, Olaviat o) throws SQLException {

		System.out.println(o);
		String celas = "", ostad = "";
		int darsid = 0, classid = 0, timeid = 0;
System.out.println("00kk");
		c.setId(coursmanager.currentCoursID);
		

		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select id  from class where nam=" + "'" + o.getCelasName() + "'" + "");
		while (r.next()) {
			classid = r.getInt("id");

		}
		System.out.println("0kk");

		Statement statement3 = DBConnector.getConnection().createStatement();
		ResultSet r3 = statement3.executeQuery("select id  from times where nam=" + "'" + o.getTimeName() + "'" + "");
		while (r3.next()) {
			timeid = r3.getInt("id");

		}
		System.out.println("1kk");
		System.out.println(c.getNam()+"  "+o.getRuz());
		PreparedStatement statement11 = DBConnector.getConnection()
				.prepareStatement("update olaviat set classid =" + "'" + classid + "'" + "  where darsid=" + "'"
						+ c.getId() + "'" + "and shomare='" + o.getShomare() + "'" + "and saat='" + o.getSaat() + "'");
		System.out.println("2kk");

		PreparedStatement statement99 = DBConnector.getConnection()
				.prepareStatement("update olaviat set timesid =" + "'" + timeid + "'" + "  where darsid=" + "'"
						+ c.getId() + "'" + "and shomare='" + o.getShomare() + "'" + "and saat='" + o.getSaat() + "'");
		System.out.println("3kk");

		PreparedStatement statement22 = DBConnector.getConnection()
				.prepareStatement("update olaviat set ruzp =" + "'" + o.isRuzp() + "'" + "  where darsid=" + "'"
						+ c.getId() + "'" + "and shomare='" + o.getShomare() + "'" + "and saat='" + o.getSaat() + "'");
		System.out.println("4kk");

		PreparedStatement statement33 = DBConnector.getConnection()
				.prepareStatement("update olaviat set classp =" + "'" + o.isCelasp() + "'" + "  where darsid=" + "'"
						+ c.getId() + "'" + "and shomare='" + o.getShomare() + "'" + "and saat='" + o.getSaat() + "'");
		System.out.println("5kk");

		PreparedStatement statement44 = DBConnector.getConnection()
				.prepareStatement("update olaviat set timesp =" + "'" + o.isTimesp() + "'" + "  where darsid=" + "'"
						+ c.getId() + "'" + "and shomare='" + o.getShomare() + "'" + "and saat='" + o.getSaat() + "'");
		System.out.println("6kk");

		PreparedStatement statement55 = DBConnector.getConnection()
				.prepareStatement("update olaviat set saat =" + "'" + o.getSaat() + "'" + "  where darsid=" + "'"
						+ c.getId() + "'" + "and shomare='" + o.getShomare() + "'" + "and saat='" + o.getSaat() + "'");
		System.out.println("7kk");

		PreparedStatement statement66 = DBConnector.getConnection()
				.prepareStatement("update olaviat set ruz =" + "'" + o.getRuz() + "'" + "  where darsid=" + "'"
						+ c.getId() + "'" + "and shomare='" + o.getShomare() + "'" + "and saat='" + o.getSaat() + "'");
		System.out.println("8kk");

		

		statement66.execute();
		statement55.execute();
		statement44.execute();
		statement33.execute();
		statement22.execute();
		statement11.execute();
		statement99.execute();

		System.out.println("course Edited");

	}

	public static List<Time> getTimes(String day,int vorudisal) throws SQLException {

		ArrayList<Time> t = new ArrayList<Time>();
		int timeid = -1,vorudiid=-1;
		;


		Statement statement22 = DBConnector.getConnection().createStatement();
		ResultSet rs22 = statement22.executeQuery("select id from vorudi where sal='" + vorudisal + "';");
		while (rs22.next()) {
			vorudiid=rs22.getInt("id");
		}
		
		Statement statement = DBConnector.getConnection().createStatement();
		ResultSet rs1 = statement.executeQuery("select timeid from daytime where day='" + day + "' and vorudiid='"+vorudiid+"';");
		while (rs1.next()) {
			timeid = rs1.getInt("timeid");

			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet rs2 = statement2.executeQuery("select * from Times where id='" + timeid + "';");
			while (rs2.next()) {
				Time time = new Time();
				time.setStartSaat(rs2.getInt("startsaat"));
				// time.setStartDaghighe(rs2.getInt("startdaghighe"));
				// time.setEndSaat(rs2.getInt("endsaat"));
				// time.setEndDaghighe(rs2.getInt("enddaghighe"));
				time.setNam(rs2.getInt("nam"));
				t.add(time);

			}
		}

		return t;

	}

	public static List<Olaviat> selectAllDifinitOlaviats() {
		List<Olaviat> olaviat = new ArrayList<Olaviat>();
		int OstadId, celasId;

		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from olaviat where ruzp='" + true + "' and timesp='" + true
					+ "' and classp='" + true + "';");
			while (rs.next()) {
				Olaviat o = new Olaviat();
				o.setRuz(rs.getString("ruz"));
				o.setCelasp(rs.getBoolean("classp"));
				o.setTimesp(rs.getBoolean("timesp"));
				o.setRuzp(rs.getBoolean("ruzp"));
				o.setSaat(rs.getInt("saat"));
				o.setTimesID(rs.getInt("timesid"));
				o.setCelasID(rs.getInt("classid"));
				o.setDarsID(rs.getInt("darsid"));
				o.setShomare(rs.getInt("shomare"));

				//o.setVorudiid(coursmanager.getVorudiid((int) o.getDarsID()));
				o.setTerm(coursmanager.getVorudiid((int) o.getDarsID()));

				Statement statement3 = DBConnector.getConnection().createStatement();
				ResultSet rs3 = statement3
						.executeQuery("select nam from class where id=" + "'" + o.getCelasID() + "'" + ";");

				while (rs3.next())
					o.setCelasName(rs3.getString("nam"));
				
				
				Statement statementf= DBConnector.getConnection().createStatement();
				ResultSet rsf= statementf
						.executeQuery("select nam from dars where id=" + "'" + o.getDarsID() + "'" + ";");

				while (rsf.next())
					o.setDarsName(rsf.getString("nam"));

				Statement statement4 = DBConnector.getConnection().createStatement();
				ResultSet rs4 = statement4
						.executeQuery("select nam from times where id=" + "'" + o.getTimesID() + "'" + ";");

				while (rs4.next())
					o.setTimeName(rs4.getInt("nam"));

				olaviat.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return olaviat;
	}

	public static List<Olaviat> selectAllOlaviats(int id,int saat) {
		List<Olaviat> olaviat = new ArrayList<Olaviat>();
		int OstadId, celasId;

		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from olaviat where darsid='" + id + "' and saat ='"+saat+"';");
			while (rs.next()) {
				Olaviat o = new Olaviat();
				o.setRuz(rs.getString("ruz"));
				o.setCelasp(rs.getBoolean("classp"));
				o.setTimesp(rs.getBoolean("timesp"));
				o.setRuzp(rs.getBoolean("ruzp"));
				o.setSaat(rs.getInt("saat"));
				o.setTimesID(rs.getInt("timesid"));
				o.setCelasID(rs.getInt("classid"));
				o.setDarsID(rs.getInt("darsid"));
				o.setShomare(rs.getInt("shomare"));
				o.setId(rs.getInt("id"));
			//	o.setVorudiid(coursmanager.getVorudiid((int) o.getDarsID()));
				o.setTerm(coursmanager.getVorudiid((int) o.getDarsID()));

				
				Statement statementf= DBConnector.getConnection().createStatement();
				ResultSet rsf= statementf
						.executeQuery("select nam from dars where id=" + "'" + o.getDarsID() + "'" + ";");

				while (rsf.next())
					o.setDarsName(rsf.getString("nam"));
				

				Statement statement3 = DBConnector.getConnection().createStatement();
				ResultSet rs3 = statement3
						.executeQuery("select nam from class where id=" + "'" + o.getCelasID() + "'" + ";");

				while (rs3.next())
					o.setCelasName(rs3.getString("nam"));

				Statement statement4 = DBConnector.getConnection().createStatement();
				ResultSet rs4 = statement4
						.executeQuery("select nam from times where id=" + "'" + o.getTimesID() + "'" + ";");

				while (rs4.next())
					o.setTimeName(rs4.getInt("nam"));

				olaviat.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return olaviat;
	}

	public static List<Integer> selectAllOlaviatGroups() {
		List<Integer> olaviat = new ArrayList<Integer>();

		int OstadId, celasId;

		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select darsid from olaviat group by darsid  ;");
			while (rs.next()) {
				int o;

				o = rs.getInt("darsid");

				olaviat.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return olaviat;
	}

	public static int getTadadSaat(int dars) throws SQLException {

		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select max(saat) from olaviat where darsid='" + dars + "'");
		int c = 0;
		while (r.next())
			c = r.getInt("max");

		return c;
	}

	public static int getTadadShomare(int dars, int saat) throws SQLException {

		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2
				.executeQuery("select max(shomare) from olaviat where darsid='" + dars + "' and saat='" + saat + "'");
		int c = 0;
		while (r.next())
			c = r.getInt("max");

		return c;
	}

	
	public static Olaviat getOlaviat(int dars, int saat,int shomare) throws SQLException {

		Olaviat o = new Olaviat();
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2
				.executeQuery("select * from olaviat where darsid='" + dars + "' and saat='" + saat + "'and shomare='" + shomare + "'");
		
		while (r.next()) {
			
			o.setRuz(r.getString("ruz"));
			o.setCelasp(r.getBoolean("classp"));
			o.setTimesp(r.getBoolean("timesp"));
			o.setRuzp(r.getBoolean("ruzp"));
			o.setSaat(r.getInt("saat"));
			o.setTimesID(r.getInt("timesid"));
			o.setCelasID(r.getInt("classid"));
			o.setDarsID(r.getInt("darsid"));
			o.setShomare(r.getInt("shomare"));

	//		o.setVorudiid(coursmanager.getVorudiid((int) o.getDarsID()));
			o.setTerm(coursmanager.getVorudiid((int) o.getDarsID()));

			Statement statementf= DBConnector.getConnection().createStatement();
			ResultSet rsf= statementf
					.executeQuery("select nam from dars where id=" + "'" + o.getDarsID() + "'" + ";");

			while (rsf.next())
				o.setDarsName(rsf.getString("nam"));
			
			
			Statement statement1 = DBConnector.getConnection().createStatement();
			ResultSet rs1 = statement1
					.executeQuery("select zojfard from dars where id=" + "'" + dars + "'" + ";");

			while (rs1.next())
				o.setZojFard(rs1.getInt("zojfard"));
			
			Statement statement3 = DBConnector.getConnection().createStatement();
			ResultSet rs3 = statement3
					.executeQuery("select nam from class where id=" + "'" + o.getCelasID() + "'" + ";");

			while (rs3.next())
				o.setCelasName(rs3.getString("nam"));

			Statement statement4 = DBConnector.getConnection().createStatement();
			ResultSet rs4 = statement4
					.executeQuery("select nam from times where id=" + "'" + o.getTimesID() + "'" + ";");

			while (rs4.next())
				o.setTimeName(rs4.getInt("nam"));

			
			
		}
		
		return o;
		
	}

	public static int getZojFard(Integer dars) throws SQLException {
int z=0;
		Statement statement1 = DBConnector.getConnection().createStatement();
		ResultSet rs1 = statement1
				.executeQuery("select zojfard from dars where id=" + "'" + dars + "'" + ";");

		while (rs1.next())
			z=(rs1.getInt("zojfard"));
		return z;
	}

	public static int getTedadJalasat(Integer dars) throws SQLException {
		int z=0;
		Statement statement1 = DBConnector.getConnection().createStatement();
		ResultSet rs1 = statement1
				.executeQuery("select tedadjalasat from dars where id=" + "'" + dars + "'" + ";");

		while (rs1.next())
			z=(rs1.getInt("tedadjalasat"));
		return z;}

	
	
}
