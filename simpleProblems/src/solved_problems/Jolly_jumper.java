package solved_problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Jolly_jumper {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		
		String ch = null;
		
		do{
			
			System.out.println("Jolly jumper인지 판별할 수열을 입력하세요(숫자는 콤마로 구분).");
		
			try {
				input = br.readLine();
			} catch (IOException e) {
				System.out.println("6");
				e.toString();
			}
			
			input = input.replaceAll("\\s", "");
			
			String[] nums = input.split(",");
			
			List<Integer> jolly_nums = new ArrayList<Integer>();
			
			for(int i=0; i<nums.length-1; i++){
				
				jolly_nums.add(i+1);
			}
			
			boolean jolly_distinguisher = true;
			
			for(int i=0; i<nums.length-1; i++){
				
				int difference = Math.abs(Integer.parseInt(nums[i+1]) - Integer.parseInt(nums[i]));
				
				if(jolly_nums.contains(difference)){
					jolly_nums.remove((Object)difference);
				}else{
					jolly_distinguisher = false;
					break;
				}
			}
			
			if(jolly_distinguisher == true){
				System.out.println("Jolly");
			}else{
				System.out.println("Not jolly");
			}
			System.out.println();
			
			System.out.print("계속 하시려면 y 또는 Y를 입력하세요 : ");
			ch = sc.next();
			System.out.println();
			
		}while(ch.equals("y") || ch.equals("Y"));
	}
}



