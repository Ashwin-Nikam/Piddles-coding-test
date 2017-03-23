import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Piddles{
	
	static long latestTime = 0;
	static SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	static Calendar c = Calendar.getInstance();
	static ArrayList<Long> list=new ArrayList();
	static ArrayList<Long> list1=new ArrayList();
	static boolean burstFlag = false;
	static int burstCount = 0;
	static Scanner input = new Scanner(System.in);
	
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
		if(burstFlag==true)
			return(sellHydrantBurst(orderTime));
		System.out.println("Burst? [y/n]");
		String choice = input.next();
		if(choice.equals("y"))
			burstFlag=true;
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

	public static boolean sellHydrantBurst(String orderTime){
		long current = time(orderTime);
		if(latestTime==0 || (current-latestTime)>=5){
			if(list1.size()==10){
				int sum = (int) (current-list1.get(0));
				if(sum<60){
					return false;
				}
			}
			latestTime = current;
			if(list1.size()==10){   				
				list1.remove(0);
			}list1.add(current);
			burstCount++;
			if(burstCount==10){
				burstCount=0;
				burstFlag=false;
				System.out.println("Burst ended!");
			}
			return true;
		}
		if(current<latestTime)
			return false;
		else
			return false;
	}

//-------------------------------------------------------------------------------------------------------	
	
}
