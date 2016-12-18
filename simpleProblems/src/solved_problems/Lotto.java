package solved_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Lotto extends Thread{
	
	Random r = new Random();
	int ranVar;
	private String[] name = {"김기훈","박근호","노태훈","장효정","최용한","설동민"
							,"고경주","이광복","박진아","김준범"};
	
	private int num;
	
	public Lotto(int num){
		this.num = num;
	}
	
	@Override
	public void run() {
		
		int i = 1;
		double term = 1;
		
		try {
			
			while(i<101){
				
				System.out.print("\n\n\n\n\n\n");
				System.out.printf("셔틀 인원수? %d", num);
				System.out.println();
				System.out.printf("\t\t\t...%d%%", i);
				System.out.println();
				if(i<100)
					System.out.print("\n\n\n\n");
				Thread.sleep(120-(int)Math.pow(term,2));
				i++;
				term = term + 0.1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		/*int[] ranArray = new int[num];
		
		int cnt = 0;
		
		while(cnt < num){
			
			//r.setSeed(System.currentTimeMillis());
			
			ranArray[cnt] = r.nextInt(num);
			
			for(int j=0; j<cnt; j++){
				
				if(ranArray[j] == ranArray[cnt]){
					
					cnt--;
					break;
				}
			}
			
			cnt++;
		}
		
		for(int m=0; m<num; m++){
			
			System.out.println(m+1 + "번 발표자 : " + name[ranArray[m]]);
		}*/
		
		List<String> ar = new ArrayList<>();
		
		int k =0;
		while(k < name.length){
			
			ar.add(name[k]);
			k++;
		}
		
		for(int j=0; j<num; j++){
			
			ranVar = r.nextInt(ar.size());
			System.out.printf("%2d번 셔틀 : %s\n", j+1, ar.get(ranVar).toString());
			ar.remove(ar.get(ranVar));
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n\n\n\n\n");
		System.out.print("셔틀 인원수? ");
		
		Lotto sp = new Lotto(sc.nextInt());
		
		sp.start();
	}

}


/*public class Speaker extends Thread{
	
	Random r = new Random();
	int ranVar;
	private String[] name = {"김기훈","노태훈","이광복","김준범","고경주","이미정","박진아","박근호","설동민","최용한","장효정"};
	
	private int num;
	
	public Speaker(int num){
		this.num = num;
	}
	
	@Override
	public void run() {
		
		int i = 0;
		
		System.out.print("고민중");
		
		try {
			
			while(i<7){
				
				System.out.print(".");
				i++;
				Thread.sleep(1000);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println();
		System.out.println();
		
		
		int[] ranArray = new int[num];
		
		int cnt = 0;
		
		while(cnt < num){
			
			//r.setSeed(System.currentTimeMillis());
			
			ranArray[cnt] = r.nextInt(num);
			
			for(int j=0; j<cnt; j++){
				
				if(ranArray[j] == ranArray[cnt]){
					
					cnt--;
					break;
				}
			}
			
			cnt++;
		}
		
		for(int m=0; m<num; m++){
			
			System.out.println(m+1 + "번 발표자 : " + name[ranArray[m]]);
		}
		
		List<String> ar = new ArrayList<>();
		
		int k =0;
		while(k < name.length){
			
			ar.add(name[k]);
			k++;
		}
		
		for(int j=0; j<num; j++){
			
			ranVar = r.nextInt(ar.size());
			System.out.printf("%2d번 셔틀 : %s\n", j+1, ar.get(ranVar).toString());
			ar.remove(ar.get(ranVar));
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("셔틀 인원수? ");
		
		Speaker sp = new Speaker(sc.nextInt());
		
		sp.start();
	}

}
*/
