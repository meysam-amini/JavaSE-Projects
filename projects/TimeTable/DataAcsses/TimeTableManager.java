package TimeTable.DataAcsses;

import java.nio.channels.GatheringByteChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TimeTable.Common.Celas;
import TimeTable.Common.Cours;
import TimeTable.Common.DayTime;
import TimeTable.Common.Olaviat;
import TimeTable.Common.Time;

public class TimeTableManager {

	public static ArrayList<Integer> AllOlaviatGroups;
	private static ArrayList<Cours> otherDarsGroups;

	public static List<Olaviat> AllSelectedOlaviats = new ArrayList<Olaviat>();
	private static ArrayList<Olaviat> DarsOlaviat;
	private static ArrayList<Olaviat> tempSaatOlaviat;

	private static ArrayList<Celas> allClasses;
	private static ArrayList<DayTime> allDayTimes;
	private static ArrayList<String> allDays;
	static int cClass = 0, cTime = 0, cDay = 0;

	private static int tedadeSaattemp;
	private static int tedadeShomaretemp;
	private static Olaviat tempChekingOlaviat;
	private static ArrayList<Olaviat> DifinitOlaviats;
	private static ArrayList<Cours> tempDars;
	private static String days[] = { "شنبه", "یکشنبه", "دوشنبه", "سشنبه", "چهارشنبه", "پنجشنبه", "جمعه" };

	private static int coursCount, classCount, timeCount;
	private static int currentSaat;
	private static int previusSaat;
	private static int currentDarsSaat;
	private static String checkZojFard[];
	public static int ZFCount;
	public static int zc;

	/// private static int classesID[];
	// private static int timesID[];
	// private static int daysID[];
	private static HashMap<Integer, Integer> timesID = new HashMap<>();
	private static HashMap<String, Integer> daysID = new HashMap<>();
	private static HashMap<Integer, Integer> classesID = new HashMap<>();
	// private static HashMap<Integer, Integer> coursID;
	// private static ArrayList<Cours> allCourses;
	private static Olaviat[][][][] Matrix;

	public static void setMatrisLimits() throws SQLException {
		// coursCount = coursmanager.getCuorsCount();
		timeCount = TimeManager.getTimeCount();
		classCount = ClassManager.getClassCount();
		////////////////////
		allClasses = (ArrayList<Celas>) ClassManager.selectAll();

		setDaysList();
		allDayTimes = (ArrayList<DayTime>) DayTimeManager.selectAllBySal(92);
		// allCourses=(ArrayList<Cours>) coursmanager.selectAll();
		//////////////////////////
		// for(int i=0;i<allCourses.size();i++){
		// coursID.put((int) allCourses.get(i).getId(),i );
		// }
		for (int i = 0; i < allDayTimes.size(); i++) {
			timesID.put((int) allDayTimes.get(i).getTimeID(), i);
		}
		for (int i = 0; i < allClasses.size(); i++) {
			classesID.put((int) allClasses.get(i).getId(), i);
		}
		for (int i = 0; i < allDays.size(); i++) {
			daysID.put(days[i], i);
		}
		///////////////////////////////////

		Matrix = new Olaviat[7][allDayTimes.size()][classCount][2];
		ZFCount = coursmanager.getZFCount();
		checkZojFard = new String[ZFCount + 1];
		zc = 0;
		for (int k = 0; k <= ZFCount; k++) {
			checkZojFard[k] = "";
		}
		setDifinitOlaviats();
		startTimeTabling();

	}

	private static void setDaysList() {
		allDays = new ArrayList<String>();
		for (int i = 0; i < 7; i++)
			allDays.add(days[i]);
	}

	// private static int olaviatCount;

	private static void replaceSelectedToFirst(Olaviat o) {
		int i = allDays.indexOf(o.getRuz());
		String temp = allDays.get(i);
		allDays.set(i, allDays.get(0));
		allDays.set(0, temp);
		/////////////////////////////////////
		i = 0;
		while (allClasses.get(i).getId() != o.getCelasID())
			i++;

		Celas temp2 = allClasses.get(i);
		allClasses.set(i, allClasses.get(0));
		allClasses.set(0, temp2);

		////////////////////////////////////////
		i = 0;
		while (allDayTimes.get(i).getDay() != o.getRuz() && allDayTimes.get(i).getTimeID() != o.getTimesID())
			i++;

		DayTime tempe3 = allDayTimes.get(i);
		allDayTimes.set(i, allDayTimes.get(0));
		allDayTimes.set(0, tempe3);

	}

	public static void startTimeTabling() throws SQLException {
		AllOlaviatGroups = (ArrayList<Integer>) OlaviatManager.selectAllOlaviatGroups();
		// AllOlaviats = (ArrayList<Olaviat>) OlaviatManager.selectAll();

		int day;
		finishOlaviatsFlag = 0;
		Olaviat o;
		for (int i = 0; i < AllOlaviatGroups.size(); i++) {

			tedadeSaattemp = OlaviatManager.getTadadSaat(AllOlaviatGroups.get(i));

			currentDarsselectedCount = -1;
			for (int k = 1; k <= tedadeSaattemp; k++) {
				tedadeShomaretemp = OlaviatManager.getTadadShomare(AllOlaviatGroups.get(i), k);

				currentSaat = k;
				for (int c = 1; c <= tedadeShomaretemp; c++) {
					if (check(AllOlaviatGroups.get(i), k, c, "") == true)

					{
						break;

					} else
						continue;
				}
			}

		}
		finishOlaviatsFlag = 1;
		if (AllSelectedOlaviats.size() == 0) {
			InsertFirstDars();
		}
		otherDarsGroups = (ArrayList<Cours>) coursmanager.selectAllNonOlaviat();
		for (int i = 0; i < otherDarsGroups.size(); i++) {
			currentDarsselectedCount = -1;
			for (int j = 1; j <= otherDarsGroups.get(i).getTedadjalasat(); j++) {

				if (check((int) otherDarsGroups.get(i).getId(), j, 1, otherDarsGroups.get(i).getNam()))
					continue;
			}
		}

	}////////////////////// fasele saat & fasele rux...

	private static int finishOlaviatsFlag = 0;
	private static int currentDarsselectedCount = 0;

	private static boolean check(Integer dars, int saat, int shomare, String darsnam) throws SQLException {
		if (test3(dars, saat))
			return true;

		Olaviat o;
		int vorudi = coursmanager.getCourse(dars + "").getTerm();
		setDaysList();
		allDayTimes = (ArrayList<DayTime>) DayTimeManager.selectAllBySal(92);// coursmanager.getCourse(dars+"").getVorudiSal());
		allClasses = (ArrayList<Celas>) ClassManager.selectAll();
		// setDaysList();
		if (finishOlaviatsFlag == 0) {
			o = OlaviatManager.getOlaviat(dars, saat, shomare);

			o.setTedadJalasat(OlaviatManager.getTadadSaat(dars));

			if (o.isRuzp()) {
				allDays.clear();
				allDays.add(o.getRuz());
			}

			if (o.isCelasp()) {
				allClasses.clear();
				allClasses.add(new Celas(o.getCelasID()));
			}

			if (o.isTimesp()) {
				allDayTimes.clear();
				allDayTimes.add(new DayTime(o.getRuz(), o.getTimesID()));
			}

			replaceSelectedToFirst(o);
		} else {
			o = new Olaviat(dars, saat);
			o.setZojFard(OlaviatManager.getZojFard(dars));
			o.setTedadJalasat(OlaviatManager.getTedadJalasat(dars));
			o.setDarsName(darsnam);
			// o.setVorudiid(vorudi);
			o.setTerm(vorudi);
		}

		int f = 0;
		for (int i = 0; i < allClasses.size(); i++) {

			if (allClasses.get(i).isSpecial()) {
				System.out.println("$$$: " + allClasses.get(i).getNam() + ":" + allClasses.get(i).isSpecial());
				if (f == 0) {
					allClasses.remove(i);
					f = 1;
				} else
					allClasses.remove(i - 1);
			}
		}
		if (ClassManager.isSpecialClass(coursmanager.getCourse(dars + "").getSpecialClassId())) {
			allClasses.clear();
			allClasses.add(new Celas(coursmanager.getCourse(dars + "").getSpecialClassId()));
		}

		for (int i = 0; i < allDays.size(); i++) {
			for (int j = 0; j < allClasses.size(); j++) {
				for (int k = 0; k < allDayTimes.size(); k++) {
					if (allDayTimes.get(k).getDay().matches(allDays.get(i))) {
						if (testVorudi((int) allClasses.get(j).getId(), allDays.get(i),
								(int) allDayTimes.get(k).getTimeID(), vorudi, o.isZojFard())) {
							if (!test1((int) allClasses.get(j).getId(), allDays.get(i),
									(int) allDayTimes.get(k).getTimeID(), o.isZojFard(), (int) o.getDarsID())) {
								if (o.isZojFard() > 0)

									if (test2((int) o.getDarsID(), allDayTimes.get(k).getTimeName(), allDays.get(i),
											o.getTedadJalasat()))
										if (testZojFard((int) allClasses.get(j).getId(), allDays.get(i),
												(int) allDayTimes.get(k).getTimeID(), o.isZojFard(),
												(int) o.getDarsID())) {
											Olaviat o2 = new Olaviat((int) allClasses.get(j).getId(), allDays.get(i),
													allDayTimes.get(k).getTimeName(),
													(int) allDayTimes.get(k).getTimeID(), (int) o.getDarsID(), saat,
													o.isZojFard(), vorudi, o.isRuzp(), o.isCelasp(), o.isTimesp());

											Matrix[daysID.get(allDays.get(i))][timesID
													.get(allDayTimes.get(k).getTimeID())][classesID
															.get(allClasses.get(j).getId())][1] = o2;// );
											AllSelectedOlaviats.add(o2);
											// if (finishOlaviatsFlag == 1)
											// currentDarsselectedCount++;
											System.out.println(o.getDarsID() + " ruz: " + allDays.get(i) + " time: "
													+ (int) allDayTimes.get(k).getTimeName() + " saat: " + saat
													+ " class: " + allClasses.get(j).getId() + " zojfard: "
													+ o.isZojFard() + " vorudi:" + vorudi + " added");
											return true;
										}
							} else {

								if (test2((int) o.getDarsID(), allDayTimes.get(k).getTimeName(), allDays.get(i),
										o.getTedadJalasat()))

								{
									Olaviat o2 = new Olaviat((int) allClasses.get(j).getId(), allDays.get(i),
											allDayTimes.get(k).getTimeName(), (int) allDayTimes.get(k).getTimeID(),
											(int) o.getDarsID(), saat, o.isZojFard(), vorudi, o.isRuzp(), o.isCelasp(),
											o.isTimesp());
									Matrix[daysID.get(allDays.get(i))][timesID
											.get(allDayTimes.get(k).getTimeID())][classesID
													.get(allClasses.get(j).getId())][0] = o2;// );
									AllSelectedOlaviats.add(o2);

									System.out.println(o.getDarsID() + " ruz: " + allDays.get(i) + " time: "
											+ (int) allDayTimes.get(k).getTimeName() + " saat: " + saat + " class: "
											+ allClasses.get(j).getId() + " zojfard: " + o.isZojFard() + " vorudi:"
											+ vorudi + " added");
									// if (finishOlaviatsFlag == 1)
									// currentDarsselectedCount++;
									return true;
								}

								//
							}
						}
					}
				}
			}

		}

		// if (shomare == 3) {
		System.err.println(coursmanager.getCourse(dars + ""));
		// }
		return false;

	}

	public static boolean test3(int Dars, int saat) throws SQLException {
		if (AllSelectedOlaviats.size() == 0)
			return true;
		else {
			for (int i = 0; i < AllSelectedOlaviats.size(); i++) {
				if ((int) AllSelectedOlaviats.get(i).getDarsID() == Dars
						&& AllSelectedOlaviats.get(i).getSaat() == saat)
					return true;
			}
			// System.out.print(Dars + " test3 false!");
			return false;

		}
	}

	public static boolean test2(int Dars, int time, String ruz, int tedadJalasat) throws SQLException {
		int faseleSaat, faseleruz;

		if (AllSelectedOlaviats.size() == 0)
			return true;
		else {
			faseleruz = coursmanager.getFaseleRuz(Dars);
			currentDarsSaat = OlaviatManager.getTedadJalasat(Dars);
			int vorudi = coursmanager.getVorudiid(Dars);
			int f = 0;
			if (currentDarsSaat > 1) {

				if (faseleruz < 1) {

					faseleSaat = coursmanager.getFaselesaat(Dars);
					for (int i = AllSelectedOlaviats.size() - 1; i > 0; i--) {
						if ((int) AllSelectedOlaviats.get(i).getDarsID() == Dars) {
							f = 1;

							if (AllSelectedOlaviats.get(i).getRuz().matches(ruz)) {
								{
									if (Math.abs(AllSelectedOlaviats.get(i).getTimeName() - time) >= faseleSaat) {
										continue;
									}

									else {
										return false;
									}
								}
							} else
								return false;

						}
					}
					if (f == 0) {
						if (DayTimeManager.check(ruz, time, tedadJalasat, vorudi)) {

							return true;

						} else {
							return false;
						}
					}

					else {

						return true;
					}

				} else {
					for (int i = AllSelectedOlaviats.size() - 1; i > 0; i--) {
						if ((int) AllSelectedOlaviats.get(i).getDarsID() == Dars) {
							// if (currentDarsselectedCount > -1)
							// i += currentDarsselectedCount;

							if (Math.abs(getDaynumber(ruz)
									- getDaynumber(AllSelectedOlaviats.get(i).getRuz())) >= faseleruz) {
								System.out.println("dars: " + Dars + "_" + ruz + "_" + time + " | "
										+ AllSelectedOlaviats.get(i).getRuz() + "_"
										+ AllSelectedOlaviats.get(i).getSaat());

								continue;
							} else {
								// System.out.print(Dars + " test2 false!");,
								return false;
							}
						}

					}
					return true;
				}

			}

			else
				return true;
		}

	}

	public static boolean testVorudi(int celas, String ruz, int time, int vorudi, int zf) {
		for (int i = 0; i < AllSelectedOlaviats.size(); i++) {
			Olaviat o = AllSelectedOlaviats.get(i);
			if (o.getRuz().matches(ruz) && (int) o.getTimesID() == time) {

				if (o.getTerm() == vorudi)
					if ((zf > 0 && o.isZojFard() > 0))
						return true;
					else
						return false;

			}
		}
		return true;
	}

	public static boolean testZojFard(int celas, String ruz, int time, int zojFard, int dars) throws SQLException {
		Olaviat o = Matrix[daysID.get(ruz)][timesID.get(time)][classesID.get(celas)][0];
		int z = 0;

		if ((o.isZojFard() > 0) && (zojFard > 0)
				&& ((int) o.getDarsID() != dars && tempZojFard.indexOf(o.getDarsID() + "") == -1)) {

			System.out.println(o.getDarsID() + "_" + dars);
			z = 0;
			for (int k = 0; k <= ZFCount; k++) {
				if (checkZojFard[k].matches(celas + ruz + time + "")) {
					z++;

				}
			}
			if (z < 2) {
				System.out.println("class: " + celas + " ruz: " + ruz + " time: " + time + " dars: " + dars);
				checkZojFard[zc++] += celas + ruz + time + "";
				tempZojFard += dars + "." + o.getDarsID() + ".";
				System.out.println(tempZojFard);
				return true;

			}

			else
				return false;

		}

		return false;
	}

	static int flag = 0;
	static String tempZojFard = "";

	public static boolean test1(int celas, String ruz, int time, int zojFard, int dars) throws SQLException {

		Olaviat o = Matrix[daysID.get(ruz)][timesID.get(time)][classesID.get(celas)][0];

		if (o != null) {

			return false;

		} else {

			return true;
		}

	}

	public static void setDifinitOlaviats() {

		AllSelectedOlaviats = (ArrayList<Olaviat>) OlaviatManager.selectAllDifinitOlaviats();

	}

	private static void InsertFirstDars() throws SQLException {
		Cours c = coursmanager.selectAll().get(0);
		int dars = (int) c.getId();

		String darsNam = c.getNam();
		Olaviat o = new Olaviat(dars, 1);
		int vorudi = coursmanager.getVorudiid(dars);
		o.setZojFard(OlaviatManager.getZojFard(dars));
		o.setTedadJalasat(OlaviatManager.getTedadJalasat(dars));
		o.setDarsName(darsNam);
		// o.setVorudiid(vorudi);
		o.setTerm(vorudi);

		AllSelectedOlaviats.add(new Olaviat((int) allClasses.get(0).getId(), allDays.get(0),
				allDayTimes.get(0).getTimeName(), (int) allDayTimes.get(0).getTimeID(), (int) o.getDarsID(), 1,
				o.isZojFard(), vorudi, o.isRuzp(), o.isCelasp(), o.isTimesp()));

	}

	private static int getDaynumber(String s) {
		for (int i = 0; i < 7; i++)
			if (s.matches(days[i]))
				return i;
		return -1;
	}

}
