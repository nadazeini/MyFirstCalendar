package calendar;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class MyEvents {
	private static Scanner fileScanner;

	/**
	 * checks if event is regular or one time
	 * 
	 * @param line
	 * @return
	 */

	public static boolean isRegular(String line) {
		return line.length() - line.replaceAll(" ", "").length() >= 4;
	}

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
	 * to close scanner after readinf file
	 */
	public static void closeEvents() {
		fileScanner.close();
	}

	public static /* List<String> */ void eventsList() {

		List<String> events = new ArrayList<String>();
		while (fileScanner.hasNextLine()) { // while the file is not empty
			String eventName = fileScanner.nextLine();
			String info = fileScanner.nextLine();
			// if (isRegular(info)) {

			events.add(eventName);

		}
		// return events;

	}

	public static void datesInFile() {
		List<Date> dates = new ArrayList<>();
		loadEvents();

		while (fileScanner.hasNextLine()) { // while the file is not empty
			String eventName = fileScanner.nextLine();
			String info = fileScanner.nextLine();

			if (!isRegular(info)) { // if it is a one time event
				String[] token = info.split(" ");
				String eventDate = token[0];
				String startTime = token[1]; // to use for interval time nz
				String endTime = token[2]; // to use nz

				DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				try {

					Date date = formatter.parse(eventDate);
					dates.add(date);

				} catch (ParseException e) {
					e.printStackTrace();
				}

			} else {

				String[] token = info.split(" ");
				String eventDays = token[0];
				String eventStart = token[1]; // change to date/time??
				String eventEnd = token[2];
				String startDate = token[3];
				String endDate = token[4];
				/**
				 * get all dates in between check if every date matches the specified dates if
				 * they match add them to the list
				 * 
				 */
			}
		}

		closeEvents();

	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return List of dates between 2 dates
	 */
	public static ArrayList<Calendar> datesBetween(Calendar start, Calendar end) {

		  ArrayList<Calendar> fileDates = new ArrayList<>();
		 
		while(start.before(end) )
			{
			
			fileDates.add(start);
			start.add(Calendar.DAY_OF_MONTH, 1);
		}
		return fileDates;
	}

	public static ArrayList<Calendar> datesWithEvents(Calendar start,Calendar end,String daysOccurence){
		 ArrayList<Calendar> fileDates =datesBetween(start,end);
		 ArrayList<Calendar> dates = new ArrayList<>();
		for (Calendar dateBetween : fileDates) {
		    if(daysOccurence.contains("M")) {
		    	//check if that date occurs on a monday
		    	if(dateBetween.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY) {
		    		
		    		dates.add(dateBetween);
		    	}
		    }
		    	 if(daysOccurence.contains("T")) {
				    	//check if that date occurs on a tuesday
				    	if(dateBetween.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY) {
				    		
				    		dates.add(dateBetween);
				    	}}
				    	 if(daysOccurence.contains("W")) {
						    	//check if that date occurs on a wednesday
						    	if(dateBetween.get(Calendar.DAY_OF_WEEK)==Calendar.WEDNESDAY) {
						    		
						    		dates.add(dateBetween);
						    	}}
						    	 if(daysOccurence.contains("R")) {
								    	//check if that date occurs on a thursday
								    	if(dateBetween.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY) {
								    		
								    		dates.add(dateBetween);
								    	}}
								    	 if(daysOccurence.contains("F")) {
										    	//check if that date occurs on afriday
										    	if(dateBetween.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY) {
										    		
										    		dates.add(dateBetween);
										    	}}
										    	 if(daysOccurence.contains("Sa")) {
												    	//check if that date occurs on a saturday
												    	if(dateBetween.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {
												    		
												    		dates.add(dateBetween);
												    	}}
												    	 if(daysOccurence.contains("Su")) {
														    	//check if that date occurs on a sunday
														    	if(dateBetween.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
														    		
														    		dates.add(dateBetween);
														    	}}
												    	 
		    }
		
		return dates;
	}

	
	public static void SomethingEvents(Calendar dateWanted) throws Exception {
		//go throughtext file
		loadEvents();
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
				//convert to calendars
				
				Calendar startDateCal = Calendar.getInstance();
				Calendar endDateCal = Calendar.getInstance();
				
				
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
				
				
				
				
				ArrayList<Calendar> datesRegular = datesWithEvents(startDateCal, endDateCal,eventDays);
				 
				 
				 //check if date wanted is in the list 
				//if it is print the event
				 if(datesRegular.contains(dateWanted)) {
					 System.out.println(eventName);
				 }
	
	}
		
		}
		closeEvents();
	
						    	 }
	
	
	public static void stringToCalendar(String dateStr) {
	
			 try {  
			 DateFormat formatter ; 
			 Date date ; 
			  formatter = new SimpleDateFormat("MM-dd-yy");
			  date = (Date)formatter.parse(dateStr); 
			 Calendar cal=Calendar.getInstance();
			 cal.setTime(date);
			
			  } catch (ParseException e)
			 {
				  System.out.println("Could not convert string to calendar");
			 }
			 
			 
			 }
			
		
		
	}

