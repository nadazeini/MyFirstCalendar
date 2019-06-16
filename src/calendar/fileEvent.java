package calendar;

import java.time.LocalTime;
import java.util.*;

import hw1.Event;
import hw1.TimeInterval;
/**
 * 
 * @author nadazeini
 *
 */
public class fileEvent {
	
	/**
	 * Creates an IntervalTime object,an Event object and EventDate object
	 * @param nameLine
	 * @param infoLine
	 * @throws Exception 
	 */
	public Event getEvent(String nameLine,String infoLine)  {
		String date;
		String startingTime;
		String endingTime;
		LocalTime startTime;
		LocalTime endTime;
		String eventName = nameLine;
		//if the line is in the format of a one time event
		int spaceAfterDate = infoLine.indexOf(" ",0);
		date = infoLine.substring(0,spaceAfterDate);
		//get time interval as string than convert it to event
		
		startingTime =infoLine.substring(spaceAfterDate+1,6);
		endingTime= infoLine.substring(6, 11);
		startTime=LocalTime.parse(startingTime);
		endTime=LocalTime.parse(endingTime);
		
		TimeInterval timeInterval = new TimeInterval(startTime,endTime);
		
		Event event = new Event(eventName,timeInterval);
		
		String[] token = date.split("/");
		int month = Integer.parseInt(token[0]);
		int day =Integer.parseInt(token[1]);
		int year =Integer.parseInt(token[2]);
		
		
		return event;
		
	}
}