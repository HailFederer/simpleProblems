package unsolved_problems;

import java.util.Scanner;

public class Veteran {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		double x;
		int a1 = 0, b1 = 0, a2 = 0, b2 = 0, c1 = 0, c2 = 0, d1 = 0, d2 = 0;
		
		a1 = sc.nextInt();
		b1 = sc.nextInt();
		a2 = sc.nextInt();
		b2 = sc.nextInt();
		c1 = sc.nextInt();
		d1 = sc.nextInt();
		c2 = sc.nextInt();
		d2 = sc.nextInt();
		
		//x = crossPoint(a1, b1, a2, b2, c1, d1, c2, d2);
		
		double slope1, slope2;
		double y_intercept1, y_intercept2;
		
		slope1 = (b2-b1)/(a2-a1);
		slope2 = (d2-d1)/(c2-c1);
		
		System.out.print(slope1);
		System.out.println(slope2);
		
		y_intercept1 = b1 - slope1*a1;
		y_intercept2 = d1 - slope2*c1;
		
		System.out.print(y_intercept1);
		System.out.println(y_intercept2);
		
		x = (-y_intercept1 + y_intercept2) / (slope1 - slope2);
		
		System.out.println(x);
	}
	
	/*public static double crossPoint(int a1, int b1, int a2, int b2, int c1, int d1, int c2, int d2){
		
		double x=0;
		
		double slope1, slope2;
		double y_intercept1, y_intercept2;
		
		slope1 = (b2-b1)/(a2-a1);
		slope2 = (d2-d1)/(c2-c1);
		
		y_intercept1 = b1 - slope1*a1;
		y_intercept2 = b1 - slope1*a1;
		
		x = (-y_intercept1 + y_intercept2) / (slope1 - slope2);
		
		return x;
	}*/
}
