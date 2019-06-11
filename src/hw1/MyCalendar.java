package hw1;

import java.time.DayOfWeek;
/**
 * this class defines underlying data structure to hold events 
 * Java 8.0 API. 
 * @author: Nada El Zeini
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class MyCalendar {

	/**
	 * @return current month in calendar format
	 * 
	 */
	public static void displayCurrentMonth() {

		LocalDate cali = LocalDate.now();
		System.out.print("    ");
		printTitle(cali);
		System.out.println("Su Mo Tu We Th Fr Sa");
	//	System.out.println(dayNumbInMonth(cali));
		int count=0;
		for(int i =0;i<=firstDay();i++) {
			System.out.print("  ");
			count ++;
		}
		int daysNo = dayNumbInMonth(cali);
		int [] mCal = new int[daysNo];
		for(int i =1;i<=daysNo;i++) {
			System.out.print("  " +i);
			count++;
			
		}
		while(count%7==0) {
			System.out.println();
		}
		

	}

	/**
	 * print month name and year
	 */
	public static void printTitle(LocalDate c) {
		System.out.println(c.getMonth().toString().charAt(0) + c.getMonth().toString().substring(1).toLowerCase() + " "
				+ c.getYear());
	}
	/**
	 * @return true if current year is leap
	 * checks if year is leap
	 * get the day number of the last day of the year and check
	 * whether it's 365 or 366
	 */
	public static boolean isLeap() {
		LocalDate cal = LocalDate.of(Calendar.YEAR,12, 31);
		
		int dayOfYear = cal.getDayOfYear(); 
		return dayOfYear>365;
	}

	/**
	 * number of days in a month
	 * 
	 */
	public static int dayNumbInMonth(LocalDate c) {
		String month = c.getMonth().toString();
		if(month.equals("JANUARY")||month.equals("MARCH")|| month.equals("MAY") || 
				month.equals("JULY") || month.equals("AUGUST") || month.equals("OCTOBER")|| month.equals("DECEMBER")) {
			return 31;
		}
		else if(month.equals("FEBRUARY"))
		{
			if(isLeap())
				return 29;
			else return 28;
		}
		return 30;
		
		
	}
	/**
	 * get date of the first day of the current month
	 * 
	 * @return
	 * 
	 */

	public static int firstDay() {
		LocalDate cal = LocalDate.of(Calendar.YEAR, Calendar.MONTH, 1);
		String weekday = cal.getDayOfWeek().toString().toLowerCase();
		int weekdayNum = 0;
		switch (weekday) {
		case "monday":
			weekdayNum = 1;
			break;
		case "tuesday":
			weekdayNum = 2;
			break;
		case "wednesday":
			weekdayNum = 3;
			break;
		case "thursday":
			weekdayNum = 4;
			break;
		case "friday":
			weekdayNum = 5;
			break;
		case "saturday":
			weekdayNum = 6;
			break;
		default:
			weekdayNum = 0;
			break;

		}
		return weekdayNum;
	}

	public static void displayMM() {
		System.out.println("Select one of the following options:");
		System.out.println("[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");

	}

}
