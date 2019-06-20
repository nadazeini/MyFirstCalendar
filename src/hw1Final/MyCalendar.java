package hw1Final;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author Nada El Zeini
 *defines hashMap as underlying data structure 
 *to retrieve dates of events and vice versa easily
 */
public class MyCalendar {

	HashMap<LocalDate,ArrayList> allEvents= new HashMap<>();

	public MyCalendar (HashMap allEvents) {
		this.allEvents=allEvents;
		
	}
	
	/**
	 * adds events to hashMap allEvents
	 * @param date
	 * @param events
	 */
	public void addEvents(LocalDate date,ArrayList events) {
		allEvents.put(date, events)
;		
		
	}
	/**
	 * removes event from hashMap
	 * @param dateOfEvent
	 * @param eventToRemove
	 */
	public void removeEvent (LocalDate dateOfEvent,Event eventToRemove) {
	allEvents.remove(dateOfEvent, eventToRemove);

}
/**
 * get date in letters to print in eventList option
 * @param date
 * @return
 */
	public String literalDate(LocalDate date) {

        // To print a calendar in a specified format. 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMMM d  ");
   
		return formatter.format(date);
	}
/**
 * 
 * @return hashMap
 */
public HashMap getHashMap() {
	return allEvents;
}}