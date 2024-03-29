package hw1Final;


import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Scanner;
/**
 * class represents an event 
 * an event consists of the name and the timeInterval of that event
 * @author nadazeini
 *
 */
public class Event implements Comparable<Event> {

	private String eventName;
	private TimeInterval timeInterval;

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
    public String toString() {
    	return eventName + ": "+ timeInterval.toString();
    }
   /**
    * compares events by time interval to sort later by timeInterval
    */
	@Override
	public int compareTo(Event e) {
			if(this.getTimeInterval().getStart().isAfter(e.getTimeInterval().getStart())) {
				return 1;
			}
			else if (this.getTimeInterval().getStart().isBefore(e.getTimeInterval().getStart()))
				return -1;
			else 
				return 0;
	}
/**
 * gets timeInterval and eventName into a string
 * @return String
 */
	 public String toString1() {


	return getTimeInterval().toString()+ " "+getEventName();
	 }
}

    



