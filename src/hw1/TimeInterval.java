package hw1;
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
public class TimeInterval {
	
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
		
		if(start.isAfter(end))
		{
			throw new Exception("Start time should be less than end time");
		}
		
	}
	public boolean timeOverlap(TimeInterval t1,TimeInterval t2) {
		return t1.start.isAfter(t2.start) && t1.start.isBefore(t2.start)
				|| t2.start.isAfter(t1.start) && t2.start.isBefore(t1.start);
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

		return start.format(DateTimeFormatter.ofPattern("HH:mm")) 
				+ "-"+end.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
 
}
