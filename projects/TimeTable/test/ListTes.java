package TimeTable.test;

import java.util.ArrayList;
import java.util.HashMap;

import TimeTable.Common.Celas;
import TimeTable.DataAcsses.ClassManager;

public class ListTes {

	private static ArrayList<String> allDays;
	private static String days[] = { "شنبه", "یکشنبه", "دوشنبه", "سشنبه", "چهارشنبه", "پنجشنبه", "جمعه" };

	private static HashMap<String, Integer> daysID=new HashMap<>();

	public static void main(String[] args) {
		
		 setDaysList();
		 for(int i=0;i<allDays.size();i++){
			 daysID.put(days[i],i);
		 }
		// System.out.println(allDays.size());

		allDays.add(null);
		allDays.add(null);
		 System.out.println(f(10,10)+"");

//		 for(int i=0;i<allDays.size();i++){
//			 System.out.println(allDays.get(i));
//		 }
	}
	private static void setDaysList() {
		allDays = new ArrayList<String>();
		for (int i = 0; i < 7; i++)
			allDays.add(days[i]);
	}
	
	
	private static long f(long a,long b){
		if(b==0)
			return 1;
		else
			return f(a,b-1)*a;
	}
}
