package solved_problems;

import java.util.Date;
import java.util.Random;

public class Prime_distinguisher {

	public static void main(String[] args){
		
		Random ran = new Random();
		ran.setSeed(new Date().getTime());
		
		int ranVar = ran.nextInt(30000)+1;
		
		if(ranVar == 1){
			System.out.println(ranVar + " is not prime");
		}
		else if(ranVar > 1){
			
			boolean aliquot = false;
			
			for(int i=2; i<=ranVar/2; i++){
				
				if(ranVar % i == 0){
					aliquot = true;
					break;
				}
			}
			
			if(aliquot == false)
				System.out.println(ranVar + " is prime");
			else
				System.out.println(ranVar + " is not prime");
		}
	}
}
