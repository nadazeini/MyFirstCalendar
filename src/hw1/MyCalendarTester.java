package hw1;
/**
 *  Tester of the calendar 
 * Java 8.0 API. 
 * @author: Nada El Zeini
 */
public class MyCalendarTester {

	
	public static void main(String [] args) {
		MyCalendar calendar = new MyCalendar();
		calendar.displayCurrentMonth();
		Event event = new Event();
		event.loadEvents();
		calendar.displayMM();
		
		
		
		
		
		
		
	}
}
