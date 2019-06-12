package hw1;

import java.util.*;
import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 
 * @author nadazeini
 *
 */
public class Event {

	private static Scanner fileScanner;
	private String eventName;
	private TimeInterval timeInterval;

	/**
	 * constructor
	 * 
	 * @param eventName
	 * @param timeInterval
	 */
	public Event(String eventName, TimeInterval timeInterval) {
		this.eventName = eventName;
		this.timeInterval = timeInterval;
		

	}
	
	
	
	

	/**
	 * finds file and opens it
	 */
	public static void loadEvents() {

		try {
			fileScanner = new Scanner(new File("events.txt"));
			System.out.println();
			System.out.println("Loading is done!");

		} catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");

		}
	}
	/**
	 * 
	 * @param line
	 * @return if event is regular
	 */
	public static boolean isRegular(String line) {
		return line.length() - line.replaceAll(" ", "").length() > 4;
	}

	/**
	 * reads events file
	 */
	public static void readEvents() {
		while (fileScanner.hasNextLine()) { // while the file is not empty
			String eventName = fileScanner.nextLine();
			String info = fileScanner.nextLine();
			if (isRegular(info)) {

				String[] token = info.split(" ");
				String eventDays = token[0];
				String eventStart = token[1]; // change to date/time??
				String eventEnd = token[2];
				String startDate = token[3];
				String endDate = token[4];

				System.out.printf("%s\n", eventName);
				System.out.printf("%s %s %s %s %s\n", eventDays, eventStart, eventEnd, startDate, endDate);
			} else {
				String[] token = info.split(" ");
				String eventDate = token[0];
				String eventStart = token[1];
				String eventEnd = token[2];
				System.out.printf("%s\n", eventName);
				System.out.printf("%s %s %s\n", eventDate, eventStart, eventEnd);

			}

		}
		System.out.println();
	}

	

	/**
	 * to close scanner after readinf file
	 */
	public static void closeEvents() {
		fileScanner.close();
	}

	/**
	 * to print just read and then close needed??
	 */
	public static void printEventsFile() {

		readEvents();
		closeEvents();

	}
	
	
	public static void printTodayEvents()   {
		loadEvents();
		LocalDate todayDate = LocalDate.now();
	
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat formatTime = new SimpleDateFormat("HH:MM");
		
		while (fileScanner.hasNextLine()) {
			String eventName = fileScanner.nextLine();
			String info = fileScanner.nextLine();
			if (isRegular(info)) {

				String[] token = info.split(" ");
				String eventDays = token[0];
				String eventStart = token[1]; // change to date/time??
				String eventEnd = token[2];
				String startDate = token[3];
				String endDate = token[4];
				
				Date currentTime = new Date();
				String now = format.format(currentTime);
				try {
				Date  today = format.parse(now);
				Date start = format.parse(startDate);
				Date end = format.parse(endDate);
				Date startTime = formatTime.parse(eventStart);
				Date endTime = formatTime.parse(eventEnd);
				if(today.equals(start) || today.equals(end) || (today.after(start) &&
						today.before(end) )) {
					System.out.println(eventName+" : "+startTime+"-"+endTime);
				}
				else System.out.println("no events");}
				  catch (ParseException e) {
			           System.out.println("Error in parse");
			        }
				
				
				
				
			}
			else {
				
				Date currentTime = new Date();
				String now = format.format(currentTime);
				String[] token = info.split(" ");
				String eventDate = token[0];
				String eventStart = token[1];
				String eventEnd = token[2];
				try {
				Date  today = format.parse(now);
				Date date = format.parse(eventDate);
				Date startTime = format.parse(eventStart);
				Date endTime =   format.parse(eventEnd);
				
				if(today.equals(date)) {
					System.out.print(eventName+" : "+startTime+"-"+endTime);
				}
				else {
					System.out.println("no events");
				}}
				  catch (ParseException e) {
			            System.out.println("error");
			        }
				
				}
		}
	closeEvents();
	}
	
			
			
		
		
	public void printEvent(Event event) {
		System.out.println(event.eventName+" : "+event.timeInterval);
	}
	public String getEventName() {
		return eventName;
		
	}
	public TimeInterval getTimeInterval() {
		return timeInterval;
	}
	

}
