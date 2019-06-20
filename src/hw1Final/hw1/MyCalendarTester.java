package hw1Final;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * 
 * @author Nada El Zeini MyCalendarTester is test class to run calendar
 *
 */
public class MyCalendarTester {
	private static HashMap<LocalDate, Event> hashMap = new HashMap<>();
	private static MyCalendar cal = new MyCalendar(hashMap);
	private static Scanner fileScanner;

	public static void main(String[] args) throws Exception {

		LocalDate now = LocalDate.now();
		displayMonthView(now);
		loadEvents();
		System.out.println("Loading is done!");
		mainMenu();

	}

	// to load events
	/* ******************************************************************************************************************************************************************************/

	/**
	 * converts date in string format 'd/M/yyyy' or 'd/M/yyyy' to LocalDate
	 * 
	 * @param dateStr date in string format
	 * @return LocalDate object of the date
	 */
	public static LocalDate stringToDate(String dateStr) {
		String[] split = dateStr.split("/");
		DateTimeFormatter formatter;
		
		if (split[2].length() == 4) {
			formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		} else
			formatter = DateTimeFormatter.ofPattern("M/d/yy");

		LocalDate localDate = LocalDate.parse(dateStr, formatter);
		return localDate;
	}

	/**
	 * gets all dates that have events scheduled on them
	 * 
	 * @return dates that have events scheduled on them
	 * @throws ParseException if anything in file was entered incorrectly
	 */
	public static ArrayList<LocalDate> allDatesWithEvents() throws ParseException {
		try {
			fileScanner = new Scanner(new File("events.txt"));

		} catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");

		}

		ArrayList<LocalDate> allEventDates = new ArrayList<>();
		while (fileScanner.hasNextLine()) {
			String eventName = fileScanner.nextLine();
			String infoLine = fileScanner.nextLine();
			String[] token = infoLine.split(" ");
			if (!isRegular(infoLine)) {

				String oneTime = token[0];
				LocalDate oneTimeDate = stringToDate(oneTime);
				allEventDates.add(oneTimeDate);
			} else {
				String occursOn = token[0];
				String startDate = token[3];
				String endDate = token[4];
				allEventDates.addAll(datesWithEvents(occursOn, datesBetween(startDate, endDate)));

			}
		}
		return allEventDates;

	}

	/**
	 * goes through text file and returns events on specific date
	 * @param dateWanted 
	 * @return ArrayList of the events on the param date
	 * @throws Exception
	 */

	public static ArrayList<Event> getEventsOnDate(LocalDate dateWanted) throws Exception {
		ArrayList<Event> eventsOnDate = new ArrayList<>();

		try {
			fileScanner = new Scanner(new File("events.txt"));

		} catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");

		}
		while (fileScanner.hasNextLine()) {
			String eventName = fileScanner.nextLine();
			String infoLine = fileScanner.nextLine();

			if (!isRegular(infoLine)) {
				int spaceAfterDate = infoLine.indexOf(" ", 0);
				LocalDate date = stringToDate(infoLine.substring(0, spaceAfterDate));
				if (dateWanted.equals(date))
					eventsOnDate.add(getEvent(eventName, infoLine));

			} else {
				String[] token = infoLine.split(" ");
				String occursOn = token[0];
				String startDate = token[3];
				String endDate = token[4];
				if (foundRegularDateEvent(dateWanted, startDate, endDate, occursOn)) {
					eventsOnDate.add(getEvent(eventName, infoLine));

				}

			}

		}

		fileScanner.close();
		return eventsOnDate;

	}

	/**
	 * gets all dates between 2 dates
	 * and put them in ArrayList
	 * @param start
	 * @param end
	 * @return ArrayList<LocalDate>
	 * @throws ParseException
	 */
	public static ArrayList<LocalDate> datesBetween(String start, String end) throws ParseException {
		LocalDate startDate = stringToDate(start);
		LocalDate endDate = stringToDate(end);
		ArrayList<LocalDate> listDatesBetween = new ArrayList<>();

		while (!startDate.isAfter(endDate)) {
			listDatesBetween.add(startDate);
			startDate = startDate.plusDays(1);
		}
		return listDatesBetween;

	}

	/**
	 * get all dates with events happening regularly
	 * 
	 * @param day: occurrence of events such as "MTWRFSSu"
	 * @param totalDates
	 * @return regularDates ArrayList of Dates with regular events
	 */
	public static ArrayList<LocalDate> datesWithEvents(String day, ArrayList<LocalDate> totalDates) {

		ArrayList<LocalDate> regularDates = new ArrayList<LocalDate>();
		for (int i = 0; i < totalDates.size(); i++) {
			if (day.contains("M")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("monday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("T")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("tuesday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("W")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("wednesday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("R")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("thursday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("F")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("friday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if ((day.contains("S") && !day.contains("Su")) || (day.contains("SSu"))) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("saturday")) {
					regularDates.add(totalDates.get(i));
				}
			}
			if (day.contains("Su")) {
				if (totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("sunday")) {
					regularDates.add(totalDates.get(i));
				}
			}
		}

		return regularDates;

	}

	/**
	 * checks if event is a regular or one time event 
	 * based on the format of the line
	 * @param line
	 * @return true if event is regular
	 */
	public static boolean isRegular(String line) {
		return line.length() - line.replaceAll(" ", "").length() >= 4;
	}

	/**
	 * check if date requested has an event
	 * among dates with regular events
	 * @param dateWanted date searching for
	 * @param startDate
	 * @param endDate
	 * @param occursOn days it is repeating on
	 * @return true if found dateWanted
	 * @throws ParseException
	 */
	public static boolean foundRegularDateEvent(LocalDate dateWanted, String startDate, String endDate, String occursOn)
			throws ParseException {

		ArrayList<LocalDate> totalDates = datesBetween(startDate, endDate);
		ArrayList<LocalDate> datesList = datesWithEvents(occursOn, totalDates);
		for (int i = 0; i < datesList.size(); i++) {
			if (datesList.get(i).isEqual(dateWanted)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Creates an IntervalTime object, then an Event object and returns it
	 * for the specified lines 
	 * @param nameLine
	 * @param infoLine
	 * @throws Exception
	 */
	public static Event getEvent(String nameLine, String infoLine) throws Exception {
		Event event = new Event(null, null);
		String occursOn;
		String startingTime;
		String endingTime;
		LocalTime startTime;
		LocalTime endTime;
		String eventName = nameLine;
		String[] element = infoLine.split(" ");
		occursOn = element[0];
		startingTime = element[1];
		endingTime = element[2];
		startTime = LocalTime.parse(startingTime, DateTimeFormatter.ofPattern("H:mm"));
		endTime = LocalTime.parse(endingTime, DateTimeFormatter.ofPattern("H:mm"));
		TimeInterval timeInterval = new TimeInterval(startTime, endTime);
		if (!isRegular(infoLine)) {
			event = new Event(eventName, timeInterval);
			return event;
		} else {
			String startDate = element[3];
			String endDate = element[4];

			ArrayList<LocalDate> totalDates = datesBetween(startDate, endDate);

			ArrayList<LocalDate> regDates = datesWithEvents(occursOn, totalDates);

			for (int i = 0; i < regDates.size(); i++) {
				event = new Event(eventName, timeInterval);
				return event;
			}

		}
		return event;
	}
/**
 * method to load events from text file to the hashmap in MyCalendar
 * @return hashMap of events and dates from input file "events.txt"
 * @throws Exception
 */
	public static HashMap loadEvents() throws Exception {

		for (int i = 0; i < allDatesWithEvents().size(); i++) {

			ArrayList<Event> eventList = getEventsOnDate(allDatesWithEvents().get(i));
			cal.addEvents(allDatesWithEvents().get(i), eventList);
		}
		return cal.allEvents;
	}

	/* ******************************************************************************************************************************************************************************/
	// main menu
	/**
	 * display main menu for the user to choose from
	 * 
	 * @throws Exception
	 */
	public static void mainMenu() throws Exception {
		System.out.println("Select one of the following options:");
		System.out.println("[V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		Scanner input = new Scanner(System.in);
		char option = input.nextLine().toUpperCase().charAt(0);
		
		switch (option) {
		case 'V':
			view();

			break;
		case 'G':
			goTo();

		case 'C':
			create();
			break;
		case 'E':

			printEventList();
			mainMenu();
			break;

		case 'D':
			delete();
			break;

		case 'Q':
			quit();
			break;
		}
	}

	/* ******************************************************************************************************************************************************************************/
//view 
	/**
	 * returns day number of events in a specific month and year
	 * 
	 * @param month
	 * @param year
	 * @return int number of days 
	 * @throws ParseException
	 */
	public static ArrayList<Integer> dayOfMonthWithEvent(int month, int year) throws ParseException {

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < allDatesWithEvents().size(); i++) {
			if (allDatesWithEvents().get(i).getMonthValue() == month && allDatesWithEvents().get(i).getYear() == year)

				list.add(allDatesWithEvents().get(i).getDayOfMonth());

		}
		return list;
	}

	/**
	   print month title and year of the date specified
	 * @param c date to print its title month and year
	 */
	public static void printTitle(LocalDate c) {
		System.out.println(c.getMonth().toString().charAt(0) + c.getMonth().toString().substring(1).toLowerCase() + " "
				+ c.getYear());
	}

	/**checks in year is leap
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
	 * gets number of days in month of a date
	 * @param c date to get the number of days in its month
	 * @return int days
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
	 * get date of the first day of the month of the date parameter
	 * @param cal LocalDate 
	 * @return int value of the first day 
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

	/**
	 * get day of the month of the current date
	 * 
	 * @return current day int
	 */

	public static int getDayToday() {
		LocalDate cal = LocalDate.now();

		return cal.getDayOfMonth();
	}
/**
 * prints  date in monhtly format
 * @param cal
 * @throws ParseException
 */
	public static void displayMonthView(LocalDate cal) throws ParseException {
		System.out.print("    ");
		int year = cal.getYear();
		int month = cal.getMonthValue();
		LocalDate today = LocalDate.now();
		ArrayList<Integer> daysWithEvent = dayOfMonthWithEvent(month, year);
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

		// brackets between current date
		if (cal.equals(today)) {
			for (int i = 1; i <= daysNo; i++) {

				if (i == getDayToday()) {
					System.out.print("[" + i + "]");

				} else if (daysWithEvent.contains(i)) {
					System.out.print("{" + i + "}");
				} else if (i < 10) {
					System.out.print("  " + i);
				} else {
					System.out.print(" " + i);
				}
				first = first + 1;
				if (first % 7 == 0) {
					System.out.println();
				}

			}
		} else {
			for (int i = 1; i <= daysNo; i++) {
				if (daysWithEvent.contains(i)) {
					System.out.print("{" + i + "}");
				} else if (i < 10) {
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
	 * prints date from dd/MM/yy format to E, MMMM d, yyyy format
	 * 
	 * @param dateFromFile
	 */
	public static void printCompleteDate(String dateFromFile) {

		LocalDate dateObj = stringToDate(dateFromFile);

		// To print a calendar in a specified format.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMMM d, yyyy");
		System.out.println();
		System.out.println(formatter.format(dateObj));
	}

	/**
	 * converts LocalDate to string "MM/dd/yy" format
	 * @param date
	 * @return date as LocalDate
	 */
	public static String dateToString(LocalDate date) {
		// if less than 10 parse it differently
		String dateStr = date.format(DateTimeFormatter.ofPattern("MM/dd/yy"));
		return dateStr;
	}

	/**
	 * sort events based on start time then print them
	 * 
	 * @param dateWanted
	 * @throws Exception
	 */
	public static void printEventsOnDate(LocalDate dateWanted) throws Exception {

		ArrayList<Event> eventsToSort = new ArrayList<Event>();
		eventsToSort = getEventsOnDate(dateWanted);
		Collections.sort(eventsToSort);
		for (int i = 0; i < eventsToSort.size(); i++) {
			System.out.println(eventsToSort.get(i).toString());
		}
	}

	/**
	 * view: prompts user to decide which view format to display
	 * 
	 * @throws Exception
	 */
	public static void view() throws Exception {

		Scanner input = new Scanner(System.in);
		char c = 0;
		int count = 0;
		LocalDate today = LocalDate.now();
		System.out.println("[D]ay view or [M]view ?");
		char DorM = input.nextLine().charAt(0);
		char prevNext = 0;
		if (DorM == 'D' || DorM == 'd') {

			printCompleteDate(dateToString(today));
			
			
			
			printEventsOnDate(today);

			System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");

			prevNext = input.nextLine().charAt(0);
			do {

				if (prevNext == 'P' || prevNext == 'p') {
					today = today.minusDays(1);

					printCompleteDate(dateToString(today));
					printEventsOnDate(today);
					System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
					prevNext = input.nextLine().charAt(0);

				}

				if (prevNext == 'n' || prevNext == 'N') {
					today = today.plusDays(1);

					printCompleteDate(dateToString(today));
					printEventsOnDate(today);

					System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
					prevNext = input.nextLine().charAt(0);

				}
				if (prevNext == 'G' || prevNext == 'g') {

					mainMenu();

				}
			} while (prevNext != 'G' || prevNext != 'g');

		} else if (DorM == 'M' || DorM == 'm') {

			displayMonthView(today);
			System.out.println();

			System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");

			prevNext = input.nextLine().charAt(0);
			do {

				if (prevNext == 'P' || prevNext == 'p') {
					today = today.minusMonths(1);
					displayMonthView(today);

					System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
					prevNext = input.nextLine().charAt(0);

				}

				if (prevNext == 'n' || prevNext == 'N') {
					today = today.plusMonths(1);
					displayMonthView(today);

					System.out.println("[P]revious or [N]ext or [G]o back to main menu ? ");
					prevNext = input.nextLine().charAt(0);

				}
				if (prevNext == 'G' || prevNext == 'g') {

					mainMenu();

				}
			} while (prevNext != 'G' || prevNext != 'g');

		}
	}

	/* ******************************************************************************************************************************************************************************/
//go to 
	/**
	 * displays all events scheduled on date requested by user in sorted way
	 * @throws Exception
	 */
	public static void goTo() throws Exception {
		System.out.println("Enter the date you want to go to in  MM/DD/YYYY format:");
		Scanner input = new Scanner(System.in);
		String date = input.nextLine();
		if (date.length() == 10) {
			date = date.substring(0, date.length() - 4) + date.substring(8);
		} else if (date.length() == 8) {

			date = date.substring(0, 4) + date.substring(6);
		} else if (date.length() == 9) {
			if (date.charAt(1) == '/') {
				date = date.substring(0, 5) + date.substring(7);
			} else
				date = date.substring(0, 5) + date.substring(7);
		}

		else {
			System.out.println("Date was not entered in the correct format");
			mainMenu();
		}
		if (cal.allEvents.containsKey(stringToDate(date))) {
			ArrayList<Event> toPrint = cal.allEvents.get(stringToDate(date));
			Collections.sort(toPrint);
			for (Event e : toPrint) {
				System.out.println(e.toString());
			}
		} else {
			System.out.println("No events on that date");
		}
		mainMenu();

	}

	/* ******************************************************************************************************************************************************************************/
//event list
	/**
	 * prints date in letters without year
	 * @param date
	 * @return String date in String 
	 */
	public static String literalDate(LocalDate date) {

		// To print a calendar in a specified format.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMMM d  ");

		return formatter.format(date);

	}
	/**
	 * prints all events 
	 * events are sorted by year, date, then by timeInterval
	 * @throws Exception
	 */

	public static void printEventList() throws Exception {
		ArrayList<LocalDate> eventDates = new ArrayList<>();

		for (Entry<LocalDate, ArrayList> entry : cal.allEvents.entrySet()) {

			eventDates.add(entry.getKey());
		}

		ArrayList<LocalDate> datesSorted = new ArrayList<>();
		Collections.sort(eventDates);
		ArrayList<Event> allEvents = new ArrayList<Event>();

		ArrayList<Integer> years = new ArrayList<>();
		for (LocalDate dateEntry : cal.allEvents.keySet()) {
			datesSorted.add(dateEntry);
			Collections.sort(datesSorted);

		}
		for (int i = 0; i < datesSorted.size(); i++) {
			if (!years.contains(datesSorted.get(i).getYear()))
				years.add(datesSorted.get(i).getYear());

		}
		for (Integer y : years) {
			System.out.println();
			System.out.println(y);
			System.out.println();
			for (int j = 0; j < datesSorted.size(); j++) {

				if (y.equals(datesSorted.get(j).getYear())) {
					ArrayList<Event> toPrint = cal.allEvents.get(datesSorted.get(j));
					Collections.sort(toPrint);

					for (Event e : toPrint) {

						System.out.println(literalDate(datesSorted.get(j)) + " " + e.toString1());
					}

				}
			}
		}
	}

	/* ******************************************************************************************************************************************************************************/
//create event
	/**
	 * gets string of event that was created to match format of those in file
	 * @param event
	 * @param date of event
	 * @return String to be printed
	 */
	public static String printEventAdded(Event event, LocalDate date) {

		String name = event.getEventName() + "\n";
		return name + dateToString(date) + " " + event.getTimeInterval().toString1();
	}

	/**
	 * converts string format of time to LocalTime Object
	 * @param timeStr
	 * @return LocalTime 
	 */
	public static LocalTime stringToTime(String timeStr) {
		String[] split = timeStr.split(":");
		if (Integer.parseInt(split[0]) < 10) {
			timeStr = "0" + timeStr.substring(0);
			LocalTime localTime = LocalTime.parse(timeStr);
			return localTime;
		} else {
			LocalTime localTime = LocalTime.parse(timeStr);

			return localTime;
		}
	}
	/**
	 * prompts user to create an event and add it to hashMap of MyCalendar cal.allEvents
	 * checks for any timeInterval overlap if date has events already scheduled
	 * 
	 * @throws Exception
	 */

	public static void create() throws Exception {
		System.out.println("Enter a title:");
		Scanner input = new Scanner(System.in);
		String title = input.nextLine();
		System.out.println("Enter date in MM/DD/YYYY format");
		String date = input.nextLine();
		if (date.length() == 10) {
			date = date.substring(0, date.length() - 4) + date.substring(8);
		} else if (date.length() == 8) {

			date = date.substring(0, 4) + date.substring(6);
		} else if (date.length() == 9) {
			if (date.charAt(1) == '/') {
				date = date.substring(0, 5) + date.substring(7);
			} else
				date = date.substring(0, 5) + date.substring(7);
		}

		else {
			System.out.println("Date was not entered in the correct format");
			mainMenu();
		}

		LocalDate dateEntered = stringToDate(date);
		System.out.println("Enter starting time and ending time: 24 hour clock:");
		String time = input.nextLine();
		String[] token = time.split(" ");

		/**
		 * checking for overlap
		 */

		// go through hashmap

		ArrayList<Event> eventsOnThatDate = new ArrayList<Event>();
		TimeInterval timeInterval = new TimeInterval(stringToTime(token[0]), stringToTime(token[1]));
		
		if (cal.allEvents.containsKey(dateEntered)) { // check for overlap
eventsOnThatDate = cal.allEvents.get(dateEntered); // get events on that date
			for (Event e : eventsOnThatDate) { // check time for each
				// if there is an overlap
				if (e.getTimeInterval().noOverlap(timeInterval)) {
					Event eventCreated = new Event(title, timeInterval);
					
					//BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt", true));
					//writer.newLine();
					//writer.write(printEventAdded(eventCreated, dateEntered));
					System.out.println("Event added successfully!!");
					eventsOnThatDate.add(eventCreated);
					cal.allEvents.put(dateEntered, eventsOnThatDate);
					//writer.close();

				}

				else {

					System.out.println("Event could not be added because of conflict with this event:");
					System.out.println(e.toString());
					break;

				}

			}
		}

		else {
			Event eventCreated = new Event(title, timeInterval);
		
			System.out.println("Event added successfully!!");
			eventsOnThatDate.add(eventCreated);
			cal.allEvents.put(dateEntered, eventsOnThatDate);

		}

		loadEvents();
		mainMenu();
	}

	/* ******************************************************************************************************************************************************************************/
	// delete
/**
 * checks if date is a one time event by going through hashmap of all events scheduled
 * @param dateWanted
 * @return boolean true if one time event
 */
	public static boolean isOneTime(String dateWanted) {
		ArrayList<Event> eventsOndate = cal.allEvents.get(stringToDate(dateWanted));
		boolean one = false;
		if (cal.allEvents.containsKey(stringToDate(dateWanted))) {

			for (Event e : eventsOndate) {
				for (Entry<LocalDate, ArrayList> entry : cal.allEvents.entrySet()) {
					ArrayList<Event> list = entry.getValue();
					for (int i = 0; i < entry.getValue().size(); i++) {
						if (list.contains(e)) {

							one = false;

						} else
							one = true;
					}
				}
			}
		}

		return one;
	}
/**
 * prompts user to enter type of event or date to be deleted
 * @throws Exception
 */
	public static void delete() throws Exception {

		System.out.println("[S]elected or [A]ll or [DR]egular?");

		Scanner input = new Scanner(System.in);
		String choice = input.nextLine();

		switch (choice) {

		case "S":
			System.out.println("Enter the date [dd/mm/yyyy]");
			String dateEntered = input.nextLine();
			System.out.println();
			printEventsOnDate(stringToDate(dateEntered));
			System.out.println("Enter the name of the event to delete:");
			String eventName = input.nextLine();
			if (isOneTime(dateEntered)) {
				for (Entry<LocalDate, ArrayList> entry : cal.allEvents.entrySet()) {
					for (int i = 0; i < entry.getValue().size(); i++) {

						ArrayList<Event> list = entry.getValue();

						if (list.get(i).getEventName().equals(eventName)) {
							list.remove(i);
							cal.allEvents.put(stringToDate(dateEntered), list);
							System.out.println("Event was deleted");
						}

					}
				}
			} else {
				System.out.println("must be one time event");
			}
			System.out.println("Here are the current scheduled events: ");
			printEventList();

			mainMenu();
			break;
		case "A":
			System.out.println("Enter a date of one time event to delete MM/DD/YYYY");
			String dateWanted = input.nextLine();
			if (isOneTime(dateWanted)) {
				if (!cal.allEvents.containsKey(dateWanted)) {
					cal.allEvents.remove(stringToDate(dateWanted));
					System.out.println("Events were deleted");
					System.out.println("Here are the current scheduled events: ");
					printEventList();
				}

			}

			else {
				System.out.println("must be a one time event");
				mainMenu();
			}
		case "DR":
			System.out.println("Enter the name of a regular event:");
			String name = input.nextLine();

			for (Entry<LocalDate, ArrayList> entry : cal.allEvents.entrySet()) {
				for (int i = 0; i < entry.getValue().size(); i++) {

					ArrayList<Event> list = entry.getValue();

					if (list.get(i).getEventName().equals(name)) {
						list.remove(i);

						cal.allEvents.put(entry.getKey(), list);

					}

				}
			}
			System.out.println("Here are the current scheduled events: ");
			printEventList();
			mainMenu();
		}

	}
	/* ******************************************************************************************************************************************************************************/
//quit
/**
 * quits the calendar program and outputs currently scheduled events to output.txt
 * calls printEventList() for clear scheduling
 * @throws Exception 
 */
	public static void quit() throws Exception {

		PrintStream o = new PrintStream(new File("output.txt"));
		PrintStream console = System.out;
		System.setOut(o);
		printEventList();

	}

}
