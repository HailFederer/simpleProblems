package solved_problems;

import java.util.Scanner;

public class Crown {

	public static void main(String[] args) {
		
		int i, j;
		int size_top, size_bot;
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			do{
				System.out.print("출력할 왕관 모양의 크기를 정수로 입력하세요(2이상) : ");
				size_top = sc.nextInt();
			}while(size_top<2);
			
			size_bot = size_top - (int)Math.sqrt(size_top * 2);
			
			for(i=1; i<=size_top; i++){
				
				for(j=1; j<=i; j++)
					System.out.print(" ");
				
				for(j=1; j<=i*2-1; j++)
					System.out.print("*");
				
				for(j=1; j<=(size_top-i)*4; j++)
					System.out.print(" ");
					
				for(j=1; j<=i*2-1; j++)
					System.out.print("*");
				
				for(j=1; j<=(size_top-i)*4; j++)
					System.out.print(" ");
				
				for(j=1; j<=i*2-1; j++)
					System.out.print("*");
				
				System.out.println();
			}
			
			for(i=1; i<=size_bot; i++){
				
				for(j=1; j<=i+size_top; j++)
					System.out.print(" ");
				
				for(j=1; j<=(size_top*6-3)-i*2; j++)
					System.out.print("*");
				
				System.out.println();
			}
		}
	}
}