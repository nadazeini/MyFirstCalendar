package hw1;
import java.util.*;
import java.io.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class Event {

	private static Scanner fileScanner ;
	
	/**
	 * finds file and opens it
	 */
	public static void loadEvents() {
		
		try {
			fileScanner = new Scanner(new File("events.txt"));
			System.out.println("Loading is done!");
			
		}
		catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");
			
		}
	}
	/**
	 * reads events file
	 */
	public static void readEvents() {
		while(fileScanner.hasNextLine()) { //while the file is not empty
			String eventName = fileScanner.nextLine();
			String info = fileScanner.nextLine();
			if(isRegular(info)) {
				
			
			
			String[] token = info.split(" ");
			String eventDays = token[0];
			String eventStart = token[1];
			String eventEnd =token[2];
			String startDate = token[3];
			String endDate = token[4];
			
			System.out.printf("%s\n",eventName);
			System.out.printf("%s %s %s %s %s\n",eventDays,eventStart,eventEnd,startDate,endDate);
			}
			else
			{
				
			}
			
			
		}
	}
	
	/**
	 * 
	 * @param line 
	 * @return if event is regular 
	 */
	public static boolean isRegular(String line) {
		return line.length()-line.replaceAll(" ", "").length() >4;
	}
	
	public static void closeEvents() {
		fileScanner.close();
	}
	public static void printEventsFile() {
		
		openEvents();
		readEvents();
		closeEvents();
		
	}
	
	
	
	
}
