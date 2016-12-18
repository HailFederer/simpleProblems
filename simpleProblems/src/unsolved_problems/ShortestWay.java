package unsolved_problems;

import java.util.Scanner;

public class ShortestWay {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int height = sc.nextInt();
		int width = sc.nextInt();
		
		int[][] map = new int[height][width];
		
		for(int i=0; i<height; i++){
			
			for(int j=0; j<width; j++)
				
				map[i][j] = sc.nextInt();
		}
		
		for(int i=0; i<height; i++){
			
			for(int j=0; j<width; j++)
				System.out.print(map[i][j]);
			
			System.out.println();
		}
		
		while(true){
			
			Thread th = new Thread();
			
			
		}
	}

}
