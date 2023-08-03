package TimeTable.DataAcsses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import TimeTable.Common.Cours;
import TimeTable.Panels.course;
import TimeTable.exception.gException;

public class coursmanager {

	public static int currentCoursID;

	public static void Insert(Cours objMember) {
		String ostad = "";
		int vorudi;
		try {

			PreparedStatement r = DBConnector.getConnection().prepareStatement(
					"insert into dars(nam,code,faselesaat,tedadjalasat,faseleruz,zojfard,ostadid,goruh,term,specialclass)"
							+ "values(?,?,?,?,?,?,?,?,?,?);");
			r.setString(1, objMember.getNam());
			r.setString(2, objMember.getCode());
			r.setDouble(3, objMember.getFaselesaat());
			r.setDouble(4, objMember.getTedadjalasat());
			r.setDouble(5, objMember.getFaseleruz());
			r.setInt(6, objMember.getZojfard());
r.setInt(9, objMember.getTerm());
			int spacialClassID=0;
			Statement statement23 = DBConnector.getConnection().createStatement();
			ResultSet r3 = statement23
					.executeQuery("select id  from class where nam=" + "'" + objMember.getSpecialClass() + "'" + "");
			while (r3.next()) {
				spacialClassID = (r3.getInt("id"));

			}
		r.setInt(10, spacialClassID);
//			Statement statement23 = DBConnector.getConnection().createStatement();
//			ResultSet r3 = statement23
//					.executeQuery("select id  from vorudi where sal=" + "'" + objMember.getVorudiSal() + "'" + "");
//			while (r3.next()) {
	//			vorudi = (r3.getInt("id"));

		//	}
			r.setInt(7, objMember.getTerm());

			ostad = objMember.getOstadFamily();
			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet rs2 = statement2.executeQuery("select id from ostad where family=" + "'" + ostad + "'" + "");
			while (rs2.next()) {
				objMember.setOstadId(rs2.getInt("id"));
			}

			r.setInt(7, (int) objMember.getOstadId());
			r.setInt(8, objMember.getGoruh());

			r.execute();

			System.out.println("Inserted");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static List<Cours> selectAllNonOlaviat() {
		List<Cours> Cours = new ArrayList<Cours>();

		int OstadId, celasId;

		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from dars where id not in(select darsid from olaviat);");
			while (rs.next()) {
				Cours b = new Cours();
				b.setNam(rs.getString("nam"));
				b.setFaseleruz(rs.getDouble("faseleruz"));
				b.setFaselesaat(rs.getDouble("faselesaat"));
				b.setTedadjalasat(rs.getDouble("tedadjalasat"));
				b.setZojfard(rs.getInt("zojfard"));
				b.setId(rs.getInt("id"));
				b.setCode(rs.getString("code"));
				b.setGoruh(rs.getInt("goruh"));
				b.setTerm(rs.getInt("term"));

				OstadId = rs.getInt("ostadid");
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet rs2 = statement2.executeQuery("select nam,family from ostad where id=" + OstadId + "");
				while (rs2.next()) {
					b.setOstadName(rs2.getString("nam"));
					b.setOstadFamily(rs2.getString("family"));
				}

				Cours.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Cours;

	}

	public static List<Cours> selectAll() {
		List<Cours> Cours = new ArrayList<Cours>();

		int OstadId, celasId, sal;

		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from dars;");
			while (rs.next()) {
				Cours b = new Cours();
				b.setNam(rs.getString("nam"));
				b.setFaseleruz(rs.getDouble("faseleruz"));
				b.setFaselesaat(rs.getDouble("faselesaat"));
				b.setTedadjalasat(rs.getDouble("tedadjalasat"));
				b.setZojfard(rs.getInt("zojfard"));
				b.setId(rs.getInt("id"));
				b.setCode(rs.getString("code"));
				b.setGoruh(rs.getInt("goruh"));
b.setSpecialClassId(rs.getInt("specialclass"));
				OstadId = rs.getInt("ostadid");
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet rs2 = statement2.executeQuery("select nam,family from ostad where id=" + OstadId + "");
				while (rs2.next()) {
					b.setOstadName(rs2.getString("nam"));
					b.setOstadFamily(rs2.getString("family"));
				}
				
				String spacialClass="";
				Statement statement23 = DBConnector.getConnection().createStatement();
				ResultSet r3 = statement23
						.executeQuery("select nam  from class where id=" + "'" + b.getSpecialClassId() + "'" + "");
				while (r3.next()) {
					spacialClass = (r3.getString("nam"));

				}
b.setSpecialClass(spacialClass);
//				sal = rs.getInt("vorudiid");
//				Statement statement23 = DBConnector.getConnection().createStatement();
//				ResultSet rs23 = statement23.executeQuery("select sal from vorudi where id=" + sal + "");
//				while (rs23.next()) {
//					b.setVorudiSal(rs23.getInt("sal"));
//
//				}

				b.setTerm(rs.getInt("term"));
				Cours.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Cours;
	}

	public static void delete(String code, String nam, int goruh) {
		try {

			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet r = statement2.executeQuery("select id  from dars  where code=" + "'" + code + "'" + " and nam='"
					+ nam + "'and goruh=" + "'" + goruh + "'" + ";");
			int darsid = 0;
			while (r.next()) {
				darsid = r.getInt("id");

			}
			PreparedStatement statement12 = DBConnector.getConnection()
					.prepareStatement("delete from olaviat where darsid=" + "'" + darsid + "';");

			statement12.execute();

			PreparedStatement statement11 = DBConnector.getConnection().prepareStatement("delete from dars where code="
					+ "'" + code + "'" + " and nam='" + nam + "'and goruh=" + "'" + goruh + "'" + ";");

			statement11.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void Edit(Cours cours) {

		String celas = "", ostad = "";
		int id = 0, vorudi = 0;
		try {

			cours.setId(currentCoursID);

			ostad = cours.getOstadFamily();
			celas = cours.getClassName();
			Statement statement2 = DBConnector.getConnection().createStatement();
			ResultSet r = statement2.executeQuery("select id , nam from ostad where family=" + "'" + ostad + "'" + "");
			while (r.next()) {
				cours.setOstadId(r.getInt("id"));
				cours.setOstadName(r.getString("nam"));
			}

//			Statement statement23 = DBConnector.getConnection().createStatement();
//			ResultSet r3 = statement23
//					.executeQuery("select id  from vorudi where sal=" + "'" + cours.getVorudiSal() + "'" + "");
//			while (r3.next()) {
//				vorudi = (r3.getInt("id"));
//
//			}
			int spacialClassID=0;
			Statement statement23 = DBConnector.getConnection().createStatement();
			ResultSet r3 = statement23
					.executeQuery("select id  from class where nam=" + "'" + cours.getSpecialClass() + "'" + "");
			while (r3.next()) {
				spacialClassID = (r3.getInt("id"));

			}

			// Statement statement =
			// DBConnector.getConnection().createStatement();
			PreparedStatement statement11 = DBConnector.getConnection().prepareStatement("update dars set nam =" + "'"
					+ cours.getNam() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");
			PreparedStatement statement22 = DBConnector.getConnection().prepareStatement("update dars set code =" + "'"
					+ cours.getCode() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");
			PreparedStatement statement33 = DBConnector.getConnection().prepareStatement("update dars set faselesaat ="
					+ "'" + cours.getFaselesaat() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");
			PreparedStatement statement44 = DBConnector.getConnection()
					.prepareStatement("update dars set tedadjalasat =" + "'" + cours.getTedadjalasat() + "'"
							+ "  where id=" + "'" + cours.getId() + "'" + "");
			PreparedStatement statement55 = DBConnector.getConnection().prepareStatement("update dars set faseleruz ="
					+ "'" + cours.getFaseleruz() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");
			PreparedStatement statement66 = DBConnector.getConnection().prepareStatement("update dars set zojfard ="
					+ "'" + cours.getZojfard() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");
			PreparedStatement statement77 = DBConnector.getConnection().prepareStatement("update dars set ostadid ="
					+ "'" + cours.getOstadId() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");
			PreparedStatement statement88 = DBConnector.getConnection().prepareStatement("update dars set goruh =" + "'"
					+ cours.getGoruh() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");

			PreparedStatement statement333 = DBConnector.getConnection().prepareStatement("update dars set specialclass =" + "'"
					+ spacialClassID + "'" + "  where id=" + "'" + cours.getId() + "'" + "");

			
			PreparedStatement statement99 = DBConnector.getConnection().prepareStatement(
					"update dars set term =" + "'" + cours.getTerm() + "'" + "  where id=" + "'" + cours.getId() + "'" + "");

			statement11.execute();
			statement22.execute();
			statement33.execute();
			statement44.execute();
			statement55.execute();
			statement66.execute();
			statement77.execute();
			statement88.execute();
			statement99.execute();
			statement333.execute();

			System.out.println("Edited");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int getCuorsCount() throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select count(*) from dars;");
		int c = 0;
		while (r.next())
			c = r.getInt("count");

		return c;
	}

	public static int getFaselesaat(int darsid) throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select faselesaat from dars where id='" + darsid + "';");
		int c = 0;
		while (r.next())
			c = r.getInt("faselesaat");

		return c;
	}

	public static int getFaseleRuz(int darsid) throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select faseleruz from dars where id='" + darsid + "';");
		int c = 0;
		while (r.next())
			c = r.getInt("faseleruz");

		return c;
	}

	public static int getZFCount() throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select count(*) from dars where zojfard <>'0';");
		int c = 0;
		while (r.next())
			c = r.getInt("count");

		return c;
	}

	public static String getName(int dars) throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select nam from dars where id='" + dars + "';");
		String c = "";
		while (r.next())
			c = r.getString("nam");

		return c;
	}

	public static int getGoruh(int dars) throws SQLException {
		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select goruh from dars where id='" + dars + "';");
		int c = 0;
		while (r.next())
			c = r.getInt("goruh");

		return c;
	}

	public static int getVorudiid(int dars) throws SQLException {
		int c = 0;

		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select term from dars where id='" + dars + "';");
		while (r.next())
			c = r.getInt("term");

		return c;
	}

	
	public static int getVorudiSal(int dars) throws SQLException {
		int id = 0,sal=0;

		Statement statement2 = DBConnector.getConnection().createStatement();
		ResultSet r = statement2.executeQuery("select vorudiid from dars where id='" + dars + "';");
		while (r.next())
			id = r.getInt("vorudiid");

		Statement statement23 = DBConnector.getConnection().createStatement();
		ResultSet r3 = statement23.executeQuery("select sal from vorudi where id='" + id + "';");
		while (r3.next())
			sal = r3.getInt("sal");

		
		
		return sal;
	}
	
	public static Cours getCourse(String cDars) {

		Cours b = new Cours();
		int OstadId, celasId, sal = 0;

		try {
			Statement statement = DBConnector.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select * from dars where id ='" + cDars + "';");
			while (rs.next()) {

				b.setNam(rs.getString("nam"));
				b.setFaseleruz(rs.getDouble("faseleruz"));
				b.setFaselesaat(rs.getDouble("faselesaat"));
				b.setTedadjalasat(rs.getDouble("tedadjalasat"));
				b.setZojfard(rs.getInt("zojfard"));
				b.setId(rs.getInt("id"));
				b.setCode(rs.getString("code"));
				b.setGoruh(rs.getInt("goruh"));
				b.setSpecialClassId(rs.getInt("specialclass"));

			//	b.setVorudiid(rs.getInt("vorudiid"));

//				sal = rs.getInt("vorudiid");
//				Statement statement23 = DBConnector.getConnection().createStatement();
//				ResultSet rs23 = statement23.executeQuery("select sal from vorudi where id=" + sal + "");
//				while (rs23.next()) {
//					b.setVorudiSal(rs23.getInt("sal"));
//
//				}
				
				
				String spacialClass="";
				Statement statement23 = DBConnector.getConnection().createStatement();
				ResultSet r3 = statement23
						.executeQuery("select nam  from class where id=" + "'" + b.getSpecialClassId() + "'" + "");
				while (r3.next()) {
					spacialClass = (r3.getString("nam"));

				}

				

				b.setTerm(rs.getInt("term"));
				OstadId = rs.getInt("ostadid");
				Statement statement2 = DBConnector.getConnection().createStatement();
				ResultSet rs2 = statement2.executeQuery("select nam,family from ostad where id=" + OstadId + "");
				while (rs2.next()) {
					b.setOstadName(rs2.getString("nam"));
					b.setOstadFamily(rs2.getString("family"));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;

	}
}
