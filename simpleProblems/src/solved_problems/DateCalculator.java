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
		String[] DayofWeek = {"��", "��", "ȭ", "��", "��", "��", "��"};
		
		do{
			System.out.print("���� ����?(1�̻�) : ");
			y = sc.nextInt();
		}while(y<1);
		
		do{
			System.out.print("���� ��?(1~12) : ");
			m = sc.nextInt();
		}while(m<1 || m>12);
		
		do{
			System.out.print("���� ��?(1~31) : ");
			d = sc.nextInt();
		}while(d<1 || d>31);
		
		do{
			System.out.print("�����Ϸκ��� ��ĥ ��?(1�̻�) : ");
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
			if(thisYNalsu>months[i]){		//thisYNalsu �� ���� thisY �� 2���޼����� ũ��, 32(1���޼�+1)���� �۾�,
				thisYNalsu -= months[i];	//���ǹ��� ������ �� �߸��� ����� ���..
				annM++;
			}
			
			if(thisYNalsu<=months[i])
				break;
		}
		
		annD = thisYNalsu;
		
		System.out.printf("�����Ϸκ��� %d��° �Ǵ� �� : %d�� %d�� %d�� %s����", period, annY, annM, annD, DayofWeek[week]);
	}
}