package firstAssignment;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import hw1.Event;
import hw1.TimeInterval;

public class MyCalendarTester {
	private static Scanner fileScanner;
	/**
	 * converts date in string format 'd/M/y' to LocalDate 
	 * @param dateStr
	 * @return
	 */
	public static LocalDate stringToDate(String dateStr) {

	
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
		LocalDate localDate = LocalDate.parse(dateStr, formatter);
		return localDate;
	}
	/**
	 * gets all dates that have events scheduled on them
	 * @return
	 * @throws ParseException
	 */
	public  ArrayList<LocalDate> allDatesWithEvents() throws ParseException{
		try {
			fileScanner = new Scanner(new File("events.txt"));
			System.out.println();
			//System.out.println("Loading is done!");

		} catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");

		}
	
		ArrayList<LocalDate> allEventDates = new ArrayList<>();
		while (fileScanner.hasNextLine()) {
			String eventName = fileScanner.nextLine();
			String infoLine = fileScanner.nextLine();
			String[] token =infoLine.split(" ");
			if(!isRegular(infoLine)) {
				
				String oneTime = token[0];
				LocalDate oneTimeDate =stringToDate(oneTime);
				allEventDates.add(oneTimeDate);
			}
			else {
				String occursOn = token[0];
				String startDate=token[3];
				String endDate=token[4];
				allEventDates.addAll(datesWithEvents(occursOn,datesBetween(startDate,endDate)));
				
				
			}	
			}
		return allEventDates;
				
		
	}
	/**
	 * 
	 * @param dateWanted
	 * @return
	 * @throws Exception
	 */

	public ArrayList<Event> getEventsOnDate(LocalDate dateWanted) throws Exception{
		ArrayList<Event> eventsOnDate = new ArrayList<>();
		
		
		try {
			fileScanner = new Scanner(new File("events.txt"));
			System.out.println();
			//System.out.println("Loading is done!");

		} catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");

		}
		while (fileScanner.hasNextLine()) {
			String eventName = fileScanner.nextLine();
			String infoLine = fileScanner.nextLine();
		
			if(!isRegular(infoLine)) {
				int spaceAfterDate = infoLine.indexOf(" ",0);
				LocalDate date = stringToDate(infoLine.substring(0,spaceAfterDate));
				if(dateWanted.equals(date))
					eventsOnDate.add(getEvent(eventName,infoLine));
				
			}
			else
			{
				String[] token = infoLine.split(" ");
				String occursOn=token[0];
				String startDate=token[3];
				String endDate=token[4];
if(foundRegularDateEvent(dateWanted,startDate, endDate, occursOn)) {
	eventsOnDate.add(getEvent(eventName,infoLine));
	
}
			}
		
	}
		
		fileScanner.close();
		return eventsOnDate;
		
	
	}
	
	/**
	 * gets all dates between 2 dates 
	 * @param start
	 * @param end
	 * @return
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
	 * @param day
	 * @param totalDates
	 * @return
	 */
	public static ArrayList<LocalDate> datesWithEvents(String day,ArrayList<LocalDate> totalDates){
		
		ArrayList<LocalDate> regularDates = new ArrayList<LocalDate>();
		for(int i =0;i<totalDates.size();i++) {
	   if(day.contains("M")) { 
	   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("monday")) {
		   regularDates.add(totalDates.get(i)); }}
	   if(day.contains("T")) { 
		   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("tuesday")) {
			   regularDates.add(totalDates.get(i)); }}
		   if(day.contains("W")) { 
			   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("wednesday")) {
				   regularDates.add(totalDates.get(i)); }}
			   if(day.contains("R")) { 
				   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("thursday")) {
					   regularDates.add(totalDates.get(i)); }}
				   if(day.contains("F")) { 
					   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("friday")) {
						   regularDates.add(totalDates.get(i)); }}
					   if((day.contains("S") && !day.contains("Su")) ||(day.contains("SSu"))) { 
						   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("saturday")) {
							   regularDates.add(totalDates.get(i)); }}
						   if(day.contains("Su")) { 
							   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("sunday")) {
								   regularDates.add(totalDates.get(i)); }}
		}
					   
			
		return regularDates;

	}
	
	
	 /**
	 * 
	 * @param line
	 * @return if event is regular
	 */
	public static boolean isRegular(String line) {
		return line.length() - line.replaceAll(" ", "").length() >= 4;
	}
	
	/**
	 * check if date requested has an event
	 * @param date
	 * @param datesList
	 * @return
	 * @throws ParseException 
	 */
	public boolean foundRegularDateEvent(LocalDate dateWanted,String startDate,String endDate,String occursOn) throws ParseException {

			ArrayList<LocalDate>totalDates =datesBetween(startDate, endDate);
		ArrayList<LocalDate>datesList =datesWithEvents(occursOn,totalDates);
		for(int i =0;i<datesList.size();i++) {
			if(datesList.get(i).isEqual(dateWanted)) {
				return true;
			}
			
	}
		return false;
			   }
	 /**
		 * Creates an IntervalTime object,an Event object and returns it
		 * @param nameLine
		 * @param infoLine
		 * @throws Exception 
		 */
		public Event getEvent(String nameLine,String infoLine) throws Exception  {
			Event event = new Event(null,null);
			String occursOn;
			String startingTime;
			String endingTime;
			LocalTime startTime;
			LocalTime endTime;
			String eventName = nameLine;
			String[] element =infoLine.split(" ");
			occursOn = element[0];
			startingTime =element[1];
			endingTime= element[2];
			startTime=LocalTime.parse(startingTime,DateTimeFormatter.ofPattern("H:mm"));
			endTime=LocalTime.parse(endingTime,DateTimeFormatter.ofPattern("H:mm"));
			TimeInterval timeInterval = new TimeInterval(startTime,endTime);
		if(!isRegular(infoLine)) {
			 event = new Event(eventName,timeInterval);
			return event;}
		else {
			String startDate=element[3];
			String endDate=element[4];
			
			ArrayList<LocalDate> totalDates =datesBetween(startDate, endDate);
			
			ArrayList<LocalDate>regDates = datesWithEvents(occursOn,totalDates);
		
			for(int i=0;i<regDates.size();i++) {
				event = new Event(eventName,timeInterval);
				return event;
			}
			
			
		}
		return event;
		}
	public void loadEvents() throws Exception {
		HashMap<LocalDate, ArrayList> hashMap = new HashMap<>();
		MyCalendar cal = new MyCalendar(hashMap);
		for(int i =0;i<allDatesWithEvents().size();i++) {
			
			ArrayList<Event> eventList =getEventsOnDate(allDatesWithEvents().get(i));
			cal.addEvents(allDatesWithEvents().get(i), eventList);
		}
	}
}

	
	
	