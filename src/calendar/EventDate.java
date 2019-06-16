package calendar;
import java.util.*;
public class EventDate {

	
	enum Months{
		Jan,Feb,Mar,Apr,Jun,Jul,Aug,Sep,Oct,Nov,Dec;
	}
	enum Days{
		Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday;
	}
	
	private int year;
	private int month;
	private int day;
	private ArrayList<Event> events = new ArrayList<>();
	private Calendar calendar = Calendar.getInstance();
	private Months[] allMonths = Months.values();
	private Days[] allDays = Days.values();
	
	public EventDate(int month,int day, int year, hw1.Event event) {
		this.month = month;
		this.day = day ;
		this.year = year;
		events.add(event);
		calendar.set(year,month-1,day);
		
	}
	
	public EventDate(int month,int day,int year) {
		this.month = month;
		this.day = day ;
		this.year = year;
		calendar.set(year,month-1,day);
	}
	public int getDay() {
		return day;
		
	}
	public int getMonth() {
		return month;
		
	}
	public int  getYear() {
		return year;
	}
	public void setDay(int day) {
		this.day=day;
	}
	public void setMonth(int month) {
		this.month=month;
	}
	public void setYear(int year) {
		this.year= year;
	}
	
	public boolean deleteEvent() {
		for(int i=0;i<events.size();i++) {
			events.remove(events.get(0)); //deleting first event
		}
		return true;
		}
	public boolean addEvent() {
		for(int i =0; i<events.size();i++) {
			events.add(events.get(0));
		}
		return true;
	}
	
	/**
	 * returns date with event printed 
	 * to add time interval
	 */
	public String printDateAndEvent() { //toString
		//getyear print
		String line = allDays[calendar.get(Calendar.DAY_OF_WEEK-1)]+" "+allMonths[month-1];
		//print the events under the date 
		for(int i=0;i<events.size();i++) {
			line=line+events.get(i).toString();
		}
		return line;
	}
	public String printDateInNumb() {
		String line = month+"/"+day+"/"+year;
		for(int i =0;i<events.size();i++) {
			line+=events.get(i).toString();
			
		}
		return line;
	}
	//toString2?????????????
	
	//equals
	
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(this.getClass()!=obj.getClass())
			return false;
		EventDate someDate = (EventDate) obj;
		return year==someDate.year && month==someDate.month && year==someDate.year;
	}
	
}
