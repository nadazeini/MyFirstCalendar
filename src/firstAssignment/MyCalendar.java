package firstAssignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author nadazeini
 *
 */
public class MyCalendar {

	HashMap<LocalDate,ArrayList> allEvents= new HashMap<>();
	//HashMap<Event> hasMap = new HashMap<>();
	public MyCalendar (HashMap allEvents) {
		this.allEvents=allEvents;
		
	}
	
	
	public void addEvents(LocalDate date,ArrayList events) {
		allEvents.put(date, events)
;		
		
	}
	public void removeEvent (LocalDate dateOfEvent,Event eventToRemove) {
	allEvents.remove(dateOfEvent, eventToRemove);
	
}
	}
