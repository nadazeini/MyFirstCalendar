package hw1;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  Tester of the calendar 
 * Java 8.0 API. 
 * @author: Nada El Zeini
 */
public class MyCalendarTester {

	
	public static void main(String [] args) {
		MyCalendar calendar = new MyCalendar();
		calendar.displayCurrentMonth();
		Event event = new Event(null, null);
		event.loadEvents();
		event.printEventsFile();
		calendar.displayMM();
		calendar.printTodayDate();
		//LocalDate date = LocalDate.now();
		//LocalTime time= LocalTime.now();
		//System.out.println(date);
		//System.out.println(time);
		event.printTodayEvents();
		
		
		
		
		
	}
}
