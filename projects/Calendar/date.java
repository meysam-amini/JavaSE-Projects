package Calendar;

import java.util.*;
import java.text.*;
import java.sql.Date;
import javax.swing.JOptionPane;

public class date {
	
	private int pDay;
	private int pMonth;
	private int pYear;
	
	private int GDay;
	private int GMonth;
	private int GYear;
	
	private String GDate;
	private String PDate;
	
	private int StartDay;
	private String MonthName;
	private int MonthNum;
	
	public date(){
		
		this.setPDate();
		this.setMonthDetail();
		this.setGDate();
	
	}
	
	/////////////////////////////////////////////////////////////////////////////setters
	
	public void setMonthDetail(){

	
		int year = this.pYear;
		int b = this.pMonth;
		int s = 0, a = 1393, k = 0, j = 0;
		while ((a - year) > 0) {
			a--;
			if (a % 4 == 3)
				k++;
			else
				j++;
		}
		while ((year - a) > 0) {
			a++;
			if (a % 4 == 3)
				k++;
			else
				j++;
		}
		s = (366 * k) + (j * 365);
		int month = b, DOmonth = 0;
		if (b <= 6)
			DOmonth = 31;
		if (b <= 11 && b > 6)
			DOmonth = 30;
		if (b == 12) {
			if (year % 4 == 3)
				DOmonth = 30;
			else
				DOmonth = 29;
		}
		String m = "";
		if (month == 1)
			m = "farvardin";
		if (month == 2)
			m = "ordibehesht";
		if (month == 3)
			m = "khordad";
		if (month == 4)
			m = "tir";
		if (month == 5)
			m = "mordad";
		if (month == 6)
			m = "shahrivar";
		if (month == 7)
			m = "mehr";
		if (month == 8)
			m = "aban";
		if (month == 9)
			m = "azar";
		if (month == 10)
			m = "dey";
		if (month == 11)
			m = "bahman";
		if (month == 12)
			m = "esfand";
		this.MonthName=m;
		this.MonthNum=DOmonth;
		b--;
		if (b > 6 || b == 6)
			b = (b - 6) * 30 + (b - (b - 6)) * 31;
		else
			b = b * 31;
		if (year < 1393)
			s -= b;
		if (year >= 1393)
			s += b;
		int startday = 0;
		if (year >= 1393)
			startday = (s % 7);
		if (year < 1393)
			startday = 7 - (s % 7);
		this.StartDay=startday;
		
	
	}
	
	
	
	
	public  void setPDate() {

		Date now = new Date(System.currentTimeMillis());
		DateFormat df = DateFormat.getDateInstance();
		DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);

		String s = df.format(now);
		String s1 = df1.format(now);


		String day = "", Month = "", Year = "";
		int i1 = s.length() - 1;
		while (s.charAt(i1) != ' ')
			Year = s.charAt(i1--) + Year;
		i1 -= 2;
		while (s.charAt(i1) != ' ')
			day = s.charAt(i1--) + day;
		i1 = 0;
		while (s1.charAt(i1) != '/')
			Month += s1.charAt(i1++);
		dateConverter d = new dateConverter();
		d.GregorianToPersian(toint(Year), toint(Month), toint(day));
		
		this.pDay=d.getDay();
		this.pMonth=d.getMonth();
		this.pYear=d.getYear();
		this.PDate=pYear+"-"+pMonth+"-"+pDay;
		

	}

	public  void setGDate() {
		Date now = new Date(System.currentTimeMillis());
		DateFormat df = DateFormat.getDateInstance();
		DateFormat df1 = DateFormat.getDateInstance(DateFormat.SHORT);

		String s = df.format(now);
		String s1 = df1.format(now);


		String day = "", Month = "", Year = "";
		int i1 = s.length() - 1;
		while (s.charAt(i1) != ' ')
			Year = s.charAt(i1--) + Year;
		i1 -= 2;
		while (s.charAt(i1) != ' ')
			day = s.charAt(i1--) + day;
		i1 = 0;
		while (s1.charAt(i1) != '/')
			Month += s1.charAt(i1++);
		this.GDay=toint(day);
		this.GMonth=toint(Month);
		this.GYear=toint(Year);

		this.GDate=GYear+"-"+GMonth+"-"+GDay;;
	}
	///////////////////////////////////////////////////////////////////////////getters
	
	public String getMonthName(){
		return MonthName;
	}
	public int getStartDay(){
		return StartDay;
	}
	public int getMonthNum(){
		return MonthNum;
	}
	
	
	//-----------------------------------------//
	public String getGDate(){
		return GDate;
	}
	public int getGday(){
		return GDay;
	}
	public int getGmonth(){
		return GMonth;
	}
	public int getGyear(){
		return GYear;
	}

	
	
	//---------------------------------//
	
	
	public String getPDate(){
		return PDate;
	}
	public int getPday(){
		return pDay;
	}
	public int getPmonth(){
		return pMonth;
	}
	public int getPyear(){
		return pYear;
	}
	
	//---------------------------------//
	private  int toint(String s) {
		int n = 0;
		for (int i = 0; i < s.length(); i++)
			n = n * 10 + (s.charAt(i) - '0');
		return n;
	}
	
	
	public void setNextMonth(){
		this.pMonth++;
		if(pMonth<=6)
			MonthNum=31;
		if(pMonth>6&&pMonth<12)
			MonthNum=30;
		if(pMonth==12){
			if(pYear%4==3)
				MonthNum=30;
			else
				MonthNum=29;}
		if(pMonth>12){
			pYear++;
			pMonth=(pMonth-12)%10;}
		this.setMonthDetail();
		dateConverter d=new dateConverter();
		d.PersianToGregorian(pYear, pMonth, pDay);
		this.GDay=d.getDay();
		this.GMonth=d.getMonth();
		this.GYear=d.getYear();
		
	}
	
	public void setPreviusMonth(){
		this.pMonth--;
		if(pMonth<=6)
			MonthNum=31;
		if(pMonth>6&&pMonth<12)
			MonthNum=30;
		if(pMonth<1){
			pMonth=(12-(-pMonth));
			pYear--;}
		if(pMonth==12){
			if(pYear%4==3)
				MonthNum=30;
			else
				MonthNum=29;}
		
		this.setMonthDetail();
		dateConverter d=new dateConverter();
		d.PersianToGregorian(pYear, pMonth, pDay);
		this.GDay=d.getDay();
		this.GMonth=d.getMonth();
		this.GYear=d.getYear();
		
	}
}