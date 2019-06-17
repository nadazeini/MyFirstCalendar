package hw1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import calendar.MyEvents;

/**
 *  Tester of the calendar 
 * Java 8.0 API. 
 * @author: Nada El Zeini
 */
public class MyCalendarTester {

	public static void runCalendar() throws Exception {
		
		MyCalendar cal = new MyCalendar();
		cal.mainMenu();
		
	}
	
	public static void main(String [] args) throws Exception {
	
		runCalendar();
	
}
}
