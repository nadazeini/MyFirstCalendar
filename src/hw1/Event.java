package hw1;

import java.io.File;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Scanner;
/**
 * 
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
}
 
    


