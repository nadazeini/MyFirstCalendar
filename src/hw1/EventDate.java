package hw1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class EventDate implements Comparable<EventDate> {
	private LocalDate date;
	EventsManager eventsManager = new EventsManager();
	private Event event;
	public EventDate(LocalDate date , Event event) {
		this.date=date;
		this.event= event;
	}
	
	public void setDate() {
		this.date=date;
		
	}
	
	public void setEvent() {
		this.event=event;
	}
	
	public Event getEvent() {
		return event;
	}
	public LocalDate getDate() {
		return date;
	}

	@Override
	public int compareTo(EventDate e) {
		if(this.getDate().isAfter(e.getDate())) {
			return 1;
		}
		else if(this.getDate().isBefore(e.getDate()))
			return -1;
		else
		return 0;
	}
	
	public String literalDate(LocalDate date) {

        // To print a calendar in a specified format. 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMMM d  ");
   
		return formatter.format(date);
	}
	
	
public String  toString() {
	
	return literalDate(date)+" "+event.getTimeInterval().toString()+ " "+event.getEventName();
	
	
}




}


