package hw1;

import java.time.DayOfWeek;
import java.util.Scanner;
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
	
	public static void displayMonthView(LocalDate cal) {

		
		System.out.print("    ");
		printTitle(cal);
		System.out.println(" Su Mo Tu We Th Fr Sa");

		int count = 0;
		int first = firstDay();
		for (int i = 0; i < first; i++) {
			System.out.print("   ");

		}

		int daysNo = dayNumbInMonth(cal);
		int[] mCal = new int[daysNo];
		for (int j = 0; j < daysNo; j++) {
			mCal[j] = j;
		}
		for (int i = 1; i <= daysNo; i++) {
			if (i == getDay()) {
				System.out.print("[" + i + "]");
			}

			else if (i < 10) {
				System.out.print("  " + i);
			} else {
				System.out.print(" " + i);
			}
			first = first + 1;
			if (first % 7 == 0) {
				System.out.println();
			}

		}
	}

	public void nextMonthView(LocalDate cal) {
		cal = cal.
	}
	/**
	 * print month name and year
	 */
	public static void printTitle(LocalDate c) {
		System.out.println(c.getMonth().toString().charAt(0) + c.getMonth().toString().substring(1).toLowerCase() + " "
				+ c.getYear());
	}

	/**
	 * @return true if current year is leap checks if year is leap get the day
	 *         number of the last day of the year and check whether it's 365 or 366
	 */
	public static boolean isLeap() {
		LocalDate cal = LocalDate.now();
		LocalDate cal1 = LocalDate.of(cal.getYear(), 12, 31);

		int dayOfYear = cal.getDayOfYear();
		return dayOfYear > 365;
	}

	/**
	 * number of days in a month
	 * 
	 */
	public static int dayNumbInMonth(LocalDate c) {
		String month = c.getMonth().toString();
		if (month.equals("JANUARY") || month.equals("MARCH") || month.equals("MAY") || month.equals("JULY")
				|| month.equals("AUGUST") || month.equals("OCTOBER") || month.equals("DECEMBER")) {
			return 31;
		} else if (month.equals("FEBRUARY")) {
			if (isLeap())
				return 29;
			else
				return 28;
		}
		return 30;

	}

	/**
	 * get date of the first day of the current month
	 * 
	 * @return
	 * 
	 */

	public static int firstDay(cal) {
		LocalDate cal = LocalDate.now();
		LocalDate cal1 = LocalDate.of(cal.getYear(), cal.getMonth(), 1);
		String weekday = cal1.getDayOfWeek().toString().toLowerCase();
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

	public static void displayMM() throws Exception {
		System.out.println("Select one of the following options:");
		System.out.println("[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		Scanner input = new Scanner(System.in);
		char option = input.nextLine().charAt(0);
		switch(option){
			case 'V':
				view();
				break;
			case'C':
				create();
				break;
			case'G':
				goTo();
				break;
			case'E':
				
				eventList();
				break;
			case'D':
				deleteOption();
			case'Q':
				quit();
			
		}

	}
	public static void quit() {
		// TODO Auto-generated method stub
		
	}

	public static void deleteOption() {
		// TODO Auto-generated method stub
		
	}

	public static void eventList() {
		// TODO Auto-generated method stub
		
	}

	public static void goTo() {
		// TODO Auto-generated method stub
		
	}

	public static void create() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * get day of the month of the current date
	 * @return current day int
	 */

	public static int getDay() {
		LocalDate cal = LocalDate.now();

		return cal.getDayOfMonth();
	}
	/**
	 * get today's date
	 */
	public static void printTodayDate() {
		
		LocalDate today = LocalDate.now();
		  
         

        // To print a calendar in a specified format. 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMMM d, yyyy");
        System.out.println(formatter.format(today));
	}

	/**
	 * view: prompts user to decide which view format to display
	 * @throws Exception 
	 */
	public static void view() throws Exception {
		EventsManager eventManager = new EventsManager();
		Scanner input = new Scanner(System.in);
		
		System.out.println("[D]ay view or [M]view ?");
		char DorM = input.nextLine().charAt(0);
		if(DorM=='D') {
			printTodayDate();
			eventManager.printEventsOnDate(eventManager.dateToString(LocalDate.now()));
		}
		else if (DorM=='M') {
			
		}
		else
		{
			
		}
		
		
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

