package hw1;

import java.io.File;
import java.util.Scanner;

public class Event {

	private String eventName;
	private TimeInterval timeInterval;
	//private static Scanner fileScanner;
	public Event(String eventName, TimeInterval timeInterval ) {
		this.eventName= eventName;
		this.timeInterval= timeInterval;
		
	}
	public void setEventName() {
		this.eventName=eventName;
	}
	public void setTimeInterval() {
		this.timeInterval=timeInterval;
		
	}
	public String getEventName() {
		return eventName;
		
	}
    public TimeInterval getTimeInterval() {
    	return timeInterval;
    }
    public String eventFormat() {
    	return eventName + " : "+ timeInterval;
    }
    
    
/*	public void loadEvents() {

		try {
			fileScanner = new Scanner(new File("events.txt"));
			System.out.println();
			System.out.println("Loading is done!");

		} catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");

		}
	}
	
	public void closeEvents() {
		fileScanner.close();
	}
	public void doEventName() {
		while (fileScanner.hasNextLine()) { // while the file is not empty
			this.eventName = fileScanner.nextLine();
			String info = fileScanner.nextLine();
		
		
	}
	
	
	*/
	
}
