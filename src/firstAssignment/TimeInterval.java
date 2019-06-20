package firstAssignment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * class that represents an interval of time suitable for events
 * @author nadazeini
 *
 */
public class TimeInterval  {
	
	private LocalTime start;
	private LocalTime end;
	
	/**
	 * constructor
	 * @param start
	 * @param end
	 * @throws Exception 
	 */
	public TimeInterval(LocalTime start,LocalTime end) throws Exception {
		this.start = start;
		this.end = end;
	
		
	}
	public boolean noOverlap(TimeInterval t1,TimeInterval t2) {
		   return (t1.start.isBefore(t2.start)&& t1.end.isBefore(t2.start))
		   || (t1.start.isAfter(t2.end) && t1.end.isAfter(t2.end)) ;
		}
	
	public void setStart() {
		this.start=start;
	}
	public void setEnd() {
		this.end= end;
	}
	public LocalTime getStart() {
		return start;
	}
	public LocalTime getEnd() {
		return end;
	}
	public String toString() {

		return start.format(DateTimeFormatter.ofPattern("H:mm")) 
				+ "-"+end.format(DateTimeFormatter.ofPattern("H:mm"));
	}
	public String toString1() {

		return start.format(DateTimeFormatter.ofPattern("H:mm")) 
				+ " "+end.format(DateTimeFormatter.ofPattern("H:mm"));
	}

 
}
