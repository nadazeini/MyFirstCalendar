package calendar;

import java.util.*;

import hw1.Event;
import hw1.TimeInterval;

import java.io.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author nadazeini
 *
 */
public class Event1 {

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

	public void printEvent(Event event) {
		System.out.println(event.eventName + " : " + event.timeInterval);
	}

	public String getEventName() {
		return eventName;

	}

	public TimeInterval getTimeInterval() {
		return timeInterval;
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

	public static void printTodayEvents() throws Exception {
		loadEvents();
		LocalDate todayDate = LocalDate.now();

		DateFormat format = new SimpleDateFormat("dd/MM/yy");
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

				Date today = format.parse(now);
				Date start = format.parse(startDate);
				Date end = format.parse(endDate);
				Date startTime = formatTime.parse(eventStart);
				Date endTime = formatTime.parse(eventEnd);
				if (today.equals(start) || today.equals(end) || (today.after(start) && today.before(end))) {
					System.out.println(eventName + " : " + startTime + "-" + endTime);
				} else
					System.out.println("no events");
			}

			else {

				Date currentTime = new Date();
				String now = format.format(currentTime);
				String[] token = info.split(" ");
				String eventDate = token[0];
				String eventStart = token[1];
				String eventEnd = token[2];

				Date today = format.parse(now);
				Date date = format.parse(eventDate);
				Date startTime = formatTime.parse(eventStart);
				Date endTime = formatTime.parse(eventEnd);

				if (today.equals(date)) {
					System.out.print(eventName + " : " + startTime + "-" + endTime);
				} else {
					System.out.println("no events");
				}
			}
		}

		closeEvents();
	}

public static String todayInFormat() {
	
	LocalDateTime today = LocalDateTime.now();

	DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
String now = format.format(today);
	return now;
}





public void  findEvent(String date) {
	loadEvents();
	HashMap<String ,String> hashMap = new HashMap<>();
	while (fileScanner.hasNextLine()) { // while the file is not empty
		String eventName = fileScanner.nextLine();
		String info = fileScanner.nextLine();
		if (!isRegular(info)) {
			String[] token = info.split(" ");
			String eventDate = token[0];
			String eventStart = token[1];
			String eventEnd = token[2];
			//
			
	hashMap.put(eventDate,eventName );
	if(hashMap.containsKey(date)) {
		System.out.println(hashMap.get(date));
		
	}
	
}
	
		else
			System.out.println("not regular");

	}
}

	
}

























