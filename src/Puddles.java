import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Puddles{
	
	static long latestTime = 0;
	static SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	static Calendar c = Calendar.getInstance();
	static ArrayList<Long> list=new ArrayList();
	
//--------------------------------------------------------------------------------------------------
	
	public static long time(String orderTime){
		try {
			c.setTime(format.parse(orderTime));
		} catch (ParseException e) {
			System.out.println("Please enter correct time format");
		}
		long startMillis = c.getTimeInMillis();
		long current = TimeUnit.MILLISECONDS.toMinutes(startMillis);
		return current;
	}	
	
//--------------------------------------------------------------------------------------------------	
	
	public static boolean canSellHydrant(String orderTime){
		
		long current = time(orderTime);
		if(latestTime==0)
			return true;
		else{
			if(current<latestTime)
				return false;
			if((current-latestTime)>=5){
				if(list.size()==5){                              
					int sum = (int) (current-list.get(0));
					if(sum<60){
						return false;
					}
				}
				return true;
			}
			else
				return false;
				
		}	
	}
	
//----------------------------------------------------------------------------------------------------
	
	public static boolean sellHydrant(String orderTime){
		long current = time(orderTime);
		if(latestTime==0 || (current-latestTime)>=5){
			if(list.size()==5){
				int sum = (int) (current-list.get(0));
				if(sum<60){
					return false;
				}
			}
			latestTime = current;
			if(list.size()==5){   				//To store the latest 5 sells an array list has been used.
				list.remove(0);
			}list.add(current);
			return true;
		}
		if(current<latestTime)
			return false;
		else
			return false;
		
	}

//------------------------------------------------------------------------------------------------------	


}
