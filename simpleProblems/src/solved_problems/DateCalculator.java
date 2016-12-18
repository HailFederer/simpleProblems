package solved_problems;

import java.util.Scanner;

public class DateCalculator {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int y, m, d, period;
		int annY, annM=1, annD = 0;
		int nalsu, annNalsu, lastYNalsu, thisYNalsu;
		int week;
		int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		String[] DayofWeek = {"일", "월", "화", "수", "목", "금", "토"};
		
		do{
			System.out.print("기준 연도?(1이상) : ");
			y = sc.nextInt();
		}while(y<1);
		
		do{
			System.out.print("기준 월?(1~12) : ");
			m = sc.nextInt();
		}while(m<1 || m>12);
		
		do{
			System.out.print("기준 일?(1~31) : ");
			d = sc.nextInt();
		}while(d<1 || d>31);
		
		do{
			System.out.print("기준일로부터 며칠 후?(1이상) : ");
			period = sc.nextInt();
		}while(period<1);
		
		nalsu = (y-1)*365 + (y-1)/4 - (y-1)/100 + (y-1)/400;
		
		if(y%4==0 && y%100!=0 || y%400==0)
			months[1] = 29;
		
		for(int i=0; i<m-1; i++){
			nalsu += months[i];
		}
		
		nalsu += d;
		
		annNalsu = nalsu + period - 1;
		
		week = annNalsu%7;
		
		annY = (int)(annNalsu/365.2425)+1;
		
		lastYNalsu = (annY-1)*365 + (annY-1)/4 - (annY-1)/100 + (annY-1)/400;
		
		thisYNalsu = annNalsu - lastYNalsu + 1;
		
		if(annY%4==0 && annY%100!=0 || annY%400==0)
			months[1] = 29;
		
		for(int i=0; i<months.length; i++){
			if(thisYNalsu>months[i]){		//thisYNalsu 의 수가 thisY 의 2월달수보다 크고, 32(1월달수+1)보다 작아,
				thisYNalsu -= months[i];	//조건문이 만족될 때 잘못된 결과값 출력..
				annM++;
			}
			
			if(thisYNalsu<=months[i])
				break;
		}
		
		annD = thisYNalsu;
		
		System.out.printf("기준일로부터 %d일째 되는 날 : %d년 %d월 %d일 %s요일", period, annY, annM, annD, DayofWeek[week]);
	}
}