package calendar;

import java.util.ArrayList;

public class ManagerEvents {
	private ArrayList<MyEvents>	fileEvents;
	public ManagerEvents() {
	ArrayList<MyEvents>	fileEvents = new ArrayList<>();
		
	}
	
	public boolean add(MyEvents toAdd) {
		fileEvents.add(toAdd);
		return true;
	}

	
	
	
	public MyEvents search(MyEvents date) {
		for(int i=0;i<fileEvents.size();i++) {
			if(fileEvents.get(i).equals(date))
				return fileEvents.get(i);
		}
		return null;
	}
	
	
	
	public void printAllEvents() {
		if(fileEvents.isEmpty()){
		System.out.println("no events for this date");	
		}
		
		
		//sort before saving info to String[]
		
    fileEvents.sort((date1, date2)->{if(date1.getYear()!=date2.getYear()) return date1.getYear()-date2.getYear();
		if(date1.getMonth()!=date2.getMonth()) return date1.getMonth()-date2.getMonth();
		if(date1.getDay()!=date2.getDay())return date1.getDay()-date2.getDay();
		return 0;
		});
			
		}
	}
	
	
}

