import java.io.IOException;
import java.util.Scanner;

class Customer{

	String name;
	int buyCount;
	Customer(String name, int buyCount){
		this.name = name;
		this.buyCount = buyCount;
	}
	
}

public class QualityAssurance {
	
	static Scanner input = new Scanner(System.in);
	static Piddles piddles = new Piddles();
	static Puddles puddles = new Puddles();
	static Pyddles pyddles = new Pyddles();
	static int count = 0;
	
//-----------------------------------------------------------------------------------------------------------	
	
	public static void main(String args[]) throws IOException{
		mainFunction();
		//testFunction();
	}
	
//-----------------------------------------------------------------------------------------------------------	
	
	public static int buyHydrant(String name, int buyCount) throws IOException{
		
		System.out.println("\nHi "+name+"!");
		
		while (true){
			System.out.println("Enter time in 'HH:mm' between 00:00 to 23:59 or type 'Back' to go back to Main Menu");
			String line = input.next();
			if(line.equals("Back"))
				break;
			if(buyCount==10){
				System.out.println("Maximum buy capacity reached for the day!");
				break;
			}
			if(count==0){
				System.out.println(line+" canSellHydrant: "+piddles.canSellHydrant(line));
				if(piddles.canSellHydrant(line)){
					System.out.println("Buy Hydrant tested by Piddles? [y/n]");
					String choice = input.next();
					if(choice.equals("y")){
						System.out.println("sellHydrant: "+piddles.sellHydrant(line)+"\n");
						buyCount++;
						count++;
					}
				}else
					System.out.println();
			}else if(count==1){
				System.out.println(line+" canSellHydrant: "+puddles.canSellHydrant(line));
				if(puddles.canSellHydrant(line)){
					System.out.println("Buy Hydrant tested by Puddles? [y/n]");
					String choice = input.next();
					if(choice.equals("y")){
						System.out.println("sellHydrant: "+puddles.sellHydrant(line)+"\n");
						buyCount++;
						count++;
					}
				}else
					System.out.println();
			}else if(count==2){
				System.out.println(line+" canSellHydrant by Pyddles: "+pyddles.canSellHydrant(line));
				if(pyddles.canSellHydrant(line)){
					System.out.println("Buy Hydrant tested by Pyddles? [y/n]");
					String choice = input.next();
					if(choice.equals("y")){
						System.out.println("sellHydrant: "+pyddles.sellHydrant(line)+"\n");
						buyCount++;
						count=0;
					}
				}else
					System.out.println();		
			}
			
		}
		
		return buyCount;
	}

//-------------------------------------------------------------------------------------------------------------	

	public static void mainFunction() throws IOException{
		Customer c1 = new Customer("Ashwin",0);
		Customer c2 = new Customer("Lannon",0);
		Customer c3 = new Customer("Seth",0);
		A: while(true){
			System.out.println("Please select customer [Enter 1/2/3/4]\n1. Ashwin\n2. Lannon\n3. Seth\n4. Exit");
			int choice = input.nextInt();
			switch(choice){
				case 1:
					c1.buyCount = buyHydrant(c1.name,c1.buyCount);
					break;
				case 2:
					c2.buyCount = buyHydrant(c2.name,c2.buyCount);
					break;
				case 3:
					c3.buyCount = buyHydrant(c3.name,c3.buyCount);
					break;
				case 4:
					break A;
			}
		}
	}
	
//-------------------------------------------------------------------------------------------------------------
	
	public static void testFunction(){
		System.out.println("This is a test function to check basic functionality of Piddles");
		while(true){
			System.out.println("Enter time in 'HH:mm' between 00:00 to 23:59 or type 'Exit'");
			String line = input.next();
			if(line.equals("Exit"))
				break;
			System.out.println(line+" canSellHydrant: "+piddles.canSellHydrant(line));
			if(piddles.canSellHydrant(line)){
				System.out.println("Buy Hydrant tested by Piddles? [y/n]");
				String choice = input.next();
				if(choice.equals("y")){
					System.out.println("sellHydrant: "+piddles.sellHydrant(line)+"\n");
				}
			}else
				System.out.println();
		}
	}
	
	
//--------------------------------------------------------------------------------------------------------------
}
