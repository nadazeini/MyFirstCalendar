package hw1;

import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * 
 * @author nadazeini
 *
 */
public class EventsManager {
	private static Scanner fileScanner;
	 /**
		 * 
		 * @param line
		 * @return if event is regular
		 */
		public static boolean isRegular(String line) {
			return line.length() - line.replaceAll(" ", "").length() >= 4;
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
	 * Creates an IntervalTime object,an Event object and returns it
	 * @param nameLine
	 * @param infoLine
	 * @throws Exception 
	 */
	public Event getEvent(String nameLine,String infoLine) throws Exception  {
		String date;
		String startingTime;
		String endingTime;
		LocalTime startTime;
		LocalTime endTime;
		String eventName = nameLine;
	if(!isRegular(infoLine)) {
		String[] element =infoLine.split(" ");
	
		date = element[0];
		
		startingTime =element[1];
		endingTime= element[2];
		
		
		
		
		startTime=LocalTime.parse(startingTime,DateTimeFormatter.ofPattern("HH:mm"));
		endTime=LocalTime.parse(endingTime,DateTimeFormatter.ofPattern("HH:mm"));
		
		TimeInterval timeInterval = new TimeInterval(startTime,endTime);
		
		Event event = new Event(eventName,timeInterval);
		
		String[] token = date.split("/");
		int month = Integer.parseInt(token[0]);
		int day =Integer.parseInt(token[1]);
		int year =Integer.parseInt(token[2]);
		
		
		return event;
		
	}
	
	public void printEventsOnDate(String dateWanted) throws Exception {
		loadEvents();
		String date;
		
		while (fileScanner.hasNextLine()) {
			String eventName = fileScanner.nextLine();
			String infoLine = fileScanner.nextLine();
		
			if(!isRegular(infoLine)) {
				int spaceAfterDate = infoLine.indexOf(" ",0);
				date = infoLine.substring(0,spaceAfterDate);
				if(dateWanted.equals(date))
					System.out.println(getEvent(eventName,infoLine).toString());

					
			}
		
	}
		fileScanner.close();
		
	}
}
