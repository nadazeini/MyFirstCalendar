package hw1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

import calendar.MyEvents;

/**
 *  Tester of the calendar 
 * Java 8.0 API. 
 * @author: Nada El Zeini
 */
public class MyCalendarTester {

	
	public static void main(String [] args) throws Exception {
		MyCalendar calendar = new MyCalendar();
		calendar.displayCurrentMonth();
		//Event event = new Event(null, null);
		//event.loadEvents();
		//event.printEventsFile();
		//calendar.displayMM();
		//calendar.printTodayDate();
		//LocalDate date = LocalDate.now();
		//LocalTime time= LocalTime.now();
		//System.out.println(date);
		//System.out.println(time);
		//event.findEvent(event.todayInFormat());;
		//System.out.println(event.todayInFormat());
		//Calendar today = Calendar.getInstance();
		MyEvents myevents= new MyEvents();
		
		//System.out.println(	today.getTime());
		/*Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
		cal.setTime(sdf.parse("06/12/19"));// all done
		myevents.SomethingEvents(cal);
		*/
		//Calendar trying =myevents.stringToCalendar("06/12/19");
		//myevents.SomethingEvents(trying);
		
		//EventsManager manager = new EventsManager();
		//manager.printEventsOnDate("10/3/19");
		DateManager dateManager = new DateManager();
		System.out.println(dateManager.stringToCalendar("10/3/19"));
		System.out.println(dateManager.datesBetween("06/12/19", "07/12/19"));
	}
}
