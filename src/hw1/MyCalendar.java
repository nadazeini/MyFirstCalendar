package hw1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.Scanner;
/**
 * this class defines underlying data structure to hold events 
 * Java 8.0 API. 
 * @author: Nada El Zeini
 * @return 
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyCalendar {
	static EventsManager eventsManager = new EventsManager();
	/**
	 * returns day number of events in a specific month and year 
	 * @param month
	 * @param year
	 * @return
	 * @throws ParseException
	 */
	public static  ArrayList<Integer> dayOfMonthWithEvent(int month,int year) throws ParseException{
	
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<eventsManager.allEventDates().size();i++) {
			if(eventsManager.allEventDates().get(i).getMonthValue()==month &&
			eventsManager.allEventDates().get(i).getYear()==year)
		
		list.add(eventsManager.allEventDates().get(i).getDayOfMonth());
		
		}
		return list;
		}
	/**
	 * @return month view of date calendar format
	 * @throws ParseException 
	 * 
	 */
	
	public static void displayMonthView(LocalDate cal) throws ParseException {
		System.out.print("    ");
		int year = cal.getYear();
		int month =cal.getMonthValue();
		LocalDate today = LocalDate.now();
		ArrayList<Integer> daysWithEvent = dayOfMonthWithEvent(month,year);
		printTitle(cal);
		System.out.println(" Su Mo Tu We Th Fr Sa");

		int count = 0;
		int first = firstDay(cal);
		for (int i = 0; i < first; i++) {
			System.out.print("   ");

		}

		int daysNo = dayNumbInMonth(cal);
		int[] mCal = new int[daysNo];
		for (int j = 0; j < daysNo; j++) {
			mCal[j] = j;
		}
		
		//brackets between current date
		if(cal.equals(today)){
		for (int i = 1; i <= daysNo; i++) {
		
			if (i == getDay()) {
				System.out.print("[" + i + "]");
				
			}
			else if(daysWithEvent.contains(i)) {
				System.out.print("{"+i+"}");
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
			else
			{for (int i = 1; i <= daysNo; i++) {
					if(daysWithEvent.contains(i)) {
						System.out.print("{"+i+"}");
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

		System.out.println();
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

	public static int firstDay(LocalDate cal) {
		
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

	public static void mainMenu() throws Exception {
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

	public static void eventList() throws Exception {
	
		eventsManager.printEventList();
		mainMenu();
	}

	public static void goTo() throws Exception {
		System.out.println("Enter the date you want to go to in  MM/DD/YYYY format:");
		Scanner input = new Scanner(System.in);
		String date = input.nextLine();
		if(date.length()==10) {
			date = date.substring(0, date.length()-4)+date.substring(8);
			}
			else if (date.length()==8) {
				
					
				date = date.substring(0,4)+date.substring(6);
			}
			else if(date.length()==9){
				if(date.charAt(1)=='/') {
					date = date.substring(0,5)+date.substring(7);
				}
				else
					date=date.substring(0,5)+date.substring(7);
			}
				
			else {
				System.out.println("Date was not entered in the correct format");
				mainMenu();
			}
		eventsManager.printEventsOnDate(date);
		mainMenu();
		
		
	}

	public static void create() throws Exception {
		System.out.println("Enter a title:");
		Scanner input = new Scanner(System.in);
		String title = input.nextLine();
		System.out.println("Enter date in MM/DD/YYYY format");
		String date = input.nextLine();
		if(date.length()==10) {
		date = date.substring(0, date.length()-4)+date.substring(8);
		}
		else if (date.length()==8) {
			
				
			date = date.substring(0,4)+date.substring(6);
		}
		else if(date.length()==9){
			if(date.charAt(1)=='/') {
				date = date.substring(0,5)+date.substring(7);
			}
			else
				date=date.substring(0,5)+date.substring(7);
		}
			
		else {
			System.out.println("Date was not entered in the correct format");
			mainMenu();
		}
	
			
		System.out.println("Enter starting time and ending time: 24 hour clock:");
		String time = input.nextLine();
		String[] token = time.split(" ") ;
		
		/**
		 * check for overlap    
		 */
		ArrayList<LocalDate> eventDates = eventsManager.allEventDates();
		TimeInterval timeInterval = new TimeInterval(eventsManager.stringToTime(token[0]),eventsManager.stringToTime(token[1]));
		if(eventDates.contains(eventsManager.stringToDate(date))||eventDates.contains(eventsManager.stringToDate1(date))){
			ArrayList<Event> eventsOnDate = eventsManager.getEventsOnDate(date);
			
			for(Event e : eventsOnDate) {
				
				if(!timeInterval.noOverlap(e.getTimeInterval(), timeInterval)){
					System.out.println("Event could not be created because it overlaps with this event:");
					EventDate eventOverLap = new EventDate(eventsManager.stringToDate(date),e);
					System.out.println(eventOverLap.add());
					mainMenu();
					
				}
				else {

					
					EventDate eventCreated = new EventDate(eventsManager.stringToDate(date),new Event(title,timeInterval));
					BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt", true)); 
				    writer.newLine(); 
				    writer.write(eventCreated.add());
				    System.out.println("Event added successfully!");
				    writer.close();
				    mainMenu();
				
				}
					
				
				
		}
		}
		else {

			
			EventDate eventCreated = new EventDate(eventsManager.stringToDate(date),new Event(title,timeInterval));
		    BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt", true)); 
		    writer.newLine(); 
		    writer.write(eventCreated.add());
		    System.out.println("Event added successfully!");
		    writer.close();
		    mainMenu();
		}
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
 * prints date from dd/MM/yy format to E, MMMM d, yyyy format
 * @param dateFromFile
 */
	public static void printCompleteDate(String dateFromFile) {
	
	
		LocalDate dateObj= 	eventsManager.stringToDate(dateFromFile);
		  
         

        // To print a calendar in a specified format. 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMMM d, yyyy");
       System.out.println();
		System.out.println(formatter.format(dateObj));
	}

	/**
	 * view: prompts user to decide which view format to display
	 * @throws Exception 
	 */
	public static void view() throws Exception {
		
		Scanner input = new Scanner(System.in);
		char c = 0 ;
		int count = 0;
		LocalDate today = LocalDate.now();
		System.out.println("[D]ay view or [M]view ?");
		char DorM = input.nextLine().charAt(0);
		char prevNext = 0;
		if(DorM=='D'|| DorM=='d') {
			
			printCompleteDate(eventsManager.dateToString(today));
			eventsManager.printEventsOnDate(eventsManager.dateToString(today));

			System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
			
			 prevNext = input.nextLine().charAt(0);
			 do {

				 
					if (prevNext=='P' || prevNext=='p')
					{
							today=today.minusDays(1);
							
							printCompleteDate(eventsManager.dateToString(today));
							eventsManager.printEventsOnDate(eventsManager.dateToString(today));
							System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
							prevNext=input.nextLine().charAt(0);
							
						
						}
						
					if(prevNext=='n'|| prevNext=='N'){
						today=today.plusDays(1);
						
						printCompleteDate(eventsManager.dateToString(today));
						eventsManager.printEventsOnDate(eventsManager.dateToString(today));
					
						System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
						prevNext=input.nextLine().charAt(0);
						
					}
					 if(prevNext=='G'||prevNext=='g') {

							mainMenu();
				
					}
					}
					while(prevNext!='G'||prevNext!='g');
				
			
		}
		else if (DorM=='M'|| DorM=='m') {
			
			displayMonthView(today);
			System.out.println();
		

			System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
			
			 prevNext = input.nextLine().charAt(0);
			do {

	 
			if (prevNext=='P' || prevNext=='p')
			{
					today=today.minusMonths(1);
					displayMonthView(today);
					
					System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
					prevNext=input.nextLine().charAt(0);
					
				
				}
				
			if(prevNext=='n'|| prevNext=='N'){
				today=today.plusMonths(1);
				displayMonthView(today);
				displayMonthView(today);
			
				System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
				prevNext=input.nextLine().charAt(0);
				
			}
			 if(prevNext=='G'||prevNext=='g') {

					mainMenu();
		
			}
			}
			while(prevNext!='G'||prevNext!='g');
		
				
		
	
		
}
	}
}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

