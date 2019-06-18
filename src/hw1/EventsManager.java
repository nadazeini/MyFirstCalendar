package hw1;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;
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
			//System.out.println("Loading is done!");

		} catch (Exception error) {
			System.out.println("Error loading file: Possibily not found");

		}
	}
	/**
	 * converts date in string format 'd/M/y' to LocalDate 
	 * @param dateStr
	 * @return
	 */
	public LocalDate stringToDate(String dateStr) {

		/*
		 * SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy"); Calendar
		 * calendar = Calendar.getInstance();
		 * calendar.setTime(formatter.parse(dateStr)); return calendar.getTime();
		 */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
		LocalDate localDate = LocalDate.parse(dateStr, formatter);
		return localDate;
	}
	/**
	 * gets all dates between 2 dates 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public ArrayList<LocalDate> datesBetween(String start, String end) throws ParseException {
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
 * 
 * @param dates
 */
	public void printArray(ArrayList<LocalDate> dates) {
		for (int i = 0; i < dates.size(); i++) {
			System.out.println(dates.get(i));

		}
	}
/**
 * 
 * @param date
 * @return
 */
	public String dateToString(LocalDate date) {

		String dateStr = date.format(DateTimeFormatter.ofPattern("M/d/yy"));
		return dateStr;
	}
/**
 * get all dates with events happening regularly
 * @param day
 * @param totalDates
 * @return
 */
public ArrayList<LocalDate> datesWithEvents(String day,ArrayList<LocalDate> totalDates){
	
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
				   if(day.contains("S")) { 
					   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("saturday")) {
						   regularDates.add(totalDates.get(i)); }}
					   if(day.contains("Su")) { 
						   if(totalDates.get(i).getDayOfWeek().toString().toLowerCase().equals("sunday")) {
							   regularDates.add(totalDates.get(i)); }}
	}
				   
		
	return regularDates;

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
		//String[] token = date.split("/");
		//int month = Integer.parseInt(token[0]);
		//int day =Integer.parseInt(token[1]);
		//int year =Integer.parseInt(token[2]);
		return event;}
	else {
		String startDate=element[3];
		String endDate=element[4];
		
		ArrayList<LocalDate> totalDates =datesBetween(startDate, endDate);
		
		ArrayList<LocalDate>regDates = datesWithEvents(occursOn,totalDates);
	
		for(int i=0;i<regDates.size();i++) {
			//String[] token = infoLine.split(" ");
//if(dateManager.foundRegularDateEvent(regDates.get(i),startDate, endDate, occursOn))
			event = new Event(eventName,timeInterval);
			return event;
		}
		
		
	}
	return event;
	}

/*public EventDate getEventDate(String nameLine,String infoLine) throws Exception {
	
	EventDate eventDate = new EventDate(null,null);
	String [] element  =infoLine.split(" "); 
	String occursOn = element[0];
	if(isRegular(infoLine)) {
		String startDate=element[3];
		String endDate=element[4];
	
		ArrayList<LocalDate> dates = datesWithEvents(occursOn,datesBetween(startDate, endDate));
	  for(int i =0;i<dates.size();i++) {
		 eventDate = new EventDate(dates.get(i),getEvent(nameLine,infoLine));
		return eventDate;
	  }
	
	}
	else {
		eventDate = new EventDate(stringToDate(occursOn),getEvent(nameLine,infoLine) );
		return eventDate;
	}
	return eventDate;
	
	
}*/

public ArrayList<EventDate> getAllEventDates() throws ParseException {
	/*ArrayList<EventDate> allEventDates = new ArrayList<>();
	loadEvents();
	while (fileScanner.hasNextLine()) {

		String eventName = fileScanner.nextLine();
		String infoLine = fileScanner.nextLine();
		
		allEventDates.add(getEventDate(eventName,infoLine));
	}
	fileScanner.close();
	return allEventDates;
		
	*/
	
	for(int i =0;i<allEventDates().size();i++) {
		
		EventDate eventDate = new EventDate(allEventDates().get(i))
	}
	

}

public void printEventList() throws Exception {
	
	ArrayList<EventDate> eventDates = getAllEventDates();
	ArrayList<Event> allEvents = new ArrayList<Event>();
	Collections.sort(eventDates,new Comparator<Object>() {

		        public int compare(Object o1, Object o2) {

		            LocalDate x1 =  ((EventDate) o1).getDate();
		            LocalDate x2 = ((EventDate) o2).getDate();
		            int sComp = x1.compareTo(x2);

		            if (sComp != 0) {
		               return sComp;
		            } 

		            Event x11 = ((EventDate) o1).getEvent();
		            Event x21= ((EventDate) o2).getEvent();
		            return x11.compareTo(x21);
		    }});
	ArrayList<Integer>years = new ArrayList<>();
	for(int i =0;i<eventDates.size();i++) {
		if(!years.contains(eventDates.get(i).getDate().getYear()))
	years.add(eventDates.get(i).getDate().getYear());
	    
	}
	for(int i=0;i<years.size();i++) {

		
		System.out.println(years.get(i));
		for(int j =0;j<eventDates.size();j++) {
			
		if(eventDates.get(j).getDate().getYear()==years.get(i)) {
			System.out.println(eventDates.get(j).toString());
		}
		
		}
	}
	
	
}
	
	

	
	
	

	public ArrayList<Event> getEventsOnDate(String dateWanted) throws Exception{
		ArrayList<Event> eventsOnDate = new ArrayList<>();
		loadEvents();
		String date;
		
		while (fileScanner.hasNextLine()) {
			String eventName = fileScanner.nextLine();
			String infoLine = fileScanner.nextLine();
		
			if(!isRegular(infoLine)) {
				int spaceAfterDate = infoLine.indexOf(" ",0);
				date = infoLine.substring(0,spaceAfterDate);
				if(dateWanted.equals(date))
					eventsOnDate.add(getEvent(eventName,infoLine));
				
			}
			else
			{
				String[] token = infoLine.split(" ");
				String occursOn=token[0];
				String startDate=token[3];
				String endDate=token[4];
if(foundRegularDateEvent(stringToDate(dateWanted),startDate, endDate, occursOn)) {
	eventsOnDate.add(getEvent(eventName,infoLine));
	
}
			}
		
	}
		
		fileScanner.close();
		return eventsOnDate;
		
	
	}

	

	/**
	 * sort events based on start time
	 * then print them
	 * @param dateWanted
	 * @throws Exception
	 */
	public void printEventsOnDate(String dateWanted) throws Exception {
		
	     
				ArrayList<Event> eventsToSort = new ArrayList<Event>();
				eventsToSort = getEventsOnDate(dateWanted);
			Collections.sort(eventsToSort);
			for(int i=0;i<eventsToSort.size();i++) {
				System.out.println(eventsToSort.get(i).toString());
			}
	}
		
		/*loadEvents();
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
			else
			{
				String[] token = infoLine.split(" ");
				String occursOn=token[0];
				String startDate=token[3];
				String endDate=token[4];
if(foundRegularDateEvent(stringToDate(dateWanted),startDate, endDate, occursOn)) {
	System.out.println(getEvent(eventName,infoLine).toString());
	
}
			}
		
	}
		System.out.println();
		fileScanner.close();
		
	}*/
	/**
	 * gets all dates that have events scheduled on them
	 * @return
	 * @throws ParseException
	 */
	public static ArrayList<LocalDate> allEventDates() throws ParseException{
		loadEvents();
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
	
	
	
}
