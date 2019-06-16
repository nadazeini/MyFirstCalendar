package hw1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class DateManager {

	/*private int month;
	private int year;
	private int day;
	
	public Date(int month,int day,int year) {
	
		this.month=month;
		this.year=year;
		this.day=day;
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
	}
	*/
	public Date stringToCalendar(String dateStr) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse(dateStr));
		return calendar.getTime();
	}
	public ArrayList<Date> datesBetween(String start,String end) throws ParseException{
	Date startDate = stringToCalendar(start);
		Date endDate = stringToCalendar(end);
		ArrayList<Date> listDatesBetween = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		while(startDate.before(endDate)) {
			listDatesBetween.add(startDate);
			calendar.add(Calendar.DATE, 1);
			
		}
		return listDatesBetween;
		
	
	}
	
	public void printArray(Ar)
}

