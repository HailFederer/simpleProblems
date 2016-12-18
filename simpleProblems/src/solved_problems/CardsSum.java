package solved_problems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CardsSum {

	public static void main(String[] args) {
		
		// 1차원 String 배열 cards에 52장의 카드를 넣음.
		String[] cards = new String[52];
		String[] alphabet = {"S","D","H","C"};
		
		for(int i=0; i<alphabet.length; i++){
			for(int j=0; j<13; j++){
				
				cards[(13*i)+j] = String.format("%s%d", alphabet[i], j+1);
			}
		}
			
		cards_Filler cards_Filler;
		List<String> remaining_Cards;
		cards_Divider cards_Divider;
		String[][] cards_Players;
		sum_Calculator sum_Calculator;
		int[] sum;
		winners_Drawer winners_Drawer;
		List<Integer> winners;			// 우승자의 번호를 저장한 ArrayList 객체를 할당할 변수
		total_Printer total_Printer;
		
		do{
			cards_Filler = new cards_Filler(cards);			// 게임을 한 차례 진행하기에 앞서, cards 배열을 매개변수로, 카드를 새로 채우는 작업을 할 객체를 생성.
			remaining_Cards = cards_Filler.cards_Fill();		// 카드를 채운 ArrayList 객체의 주소를 반환받아 remaining_Cards 변수에 할당. 
			
			cards_Divider = new cards_Divider(remaining_Cards);	// remaining_Cards 객체를 매개변수로, 카드를 플레이어들에게 랜덤으로 배분하는 작업을 할 객체 생성.
			cards_Players = cards_Divider.cards_Div();			// 배분한 카드를 담은 2차원 String 배열의 주소을 반환받아 cards_Players 변수에 할당.
																			// ex) cards_Players[0][6] -> Player1에게 마지막에 배분된 카드.
																			//     cards_Players[3][6] -> Player4에게 마지막에 배분된 카드.
			
			sum_Calculator = new sum_Calculator(cards_Players);	// cards_Players 객체를 매개변수로, 플레이어 각각의 합계를 구하는 작업을 할 객체 생성.
			sum = sum_Calculator.sum_Calc();							// 계산한 합계를 저장한 1차원 정수 배열의 주소를 반환받아 sum 변수에 할당.
																			// ex) sum[0] -> Player1의 sum.
																			// ex) sum[3] -> Player4의 sum.
			
			winners_Drawer = new winners_Drawer(sum);		// sum 객체를 매개변수로, 우승자 번호를 추첨하는 객체 생성(1명일 수도, 아닐 수도 있음).
			winners = winners_Drawer.winners_Draw();						// 우승자를 저장한 ArrayList 객체의 주소를 반환받아 winners 변수에 할당.
			
			total_Printer = new total_Printer(cards_Players, sum, winners);	// 우승자의 번호를 할당한 winners 객체의 사이즈를 판별해,
			total_Printer.print();											// 각각의 경우에 해당하는 메세지 출력.
			
		}while(winners.size() > 1);			// 우승자가 1명이 될 때까지 위의 과정을 반복.
		
		cards_Filler = null;
		remaining_Cards = null;
		cards_Divider = null;
		cards_Players = null;
		sum_Calculator = null;
		sum = null;
		winners_Drawer = null;
		winners = null;
		total_Printer = null;
		
		System.gc();
		System.runFinalization();
	}
}



class cards_Filler{							// cards 배열을 매개변수로, 카드를 새로 채우는 작업을 하는 클래스.
	
	String[] cards;
	
	public cards_Filler(String[] cards){	// 생성자가 cards 배열을 매개변수로 받아,
		this.cards = cards;					// 클래스의 멤버변수에 할당.
	}
	
	public List<String> cards_Fill(){
		
		List<String> remaining_Cards = new ArrayList<String>();		// 카드를 담을 ArrayList 객체를 생성하여 그 주소를 할당.
		
		for(int i=0; i<cards.length; i++){
			remaining_Cards.add(cards[i]);		// 반복문을 이용해, 모든 카드를 remaining_Cards 객체에 저장.
		}
		
		return remaining_Cards;					// 카드를 저장한 remaining_Cards 객체를 반환.
	}
}

class cards_Divider{						// remaining_Cards 객체를 매개변수로, 카드를 플레이어들에게 나눠줄 작업을 하는 클래스.
	
	List<String> remaining_Cards;
	
	public cards_Divider(List<String> remaining_Cards){		// 생성자가 remaining_Cards 객체를 매개변수로 받아,
		this.remaining_Cards = remaining_Cards;				// 클래스의 멤버변수에 할당.
	}
	
	public String[][] cards_Div(){

		String[][] cards_Players = new String[4][7];		// 플레이어들에게 배분한 카드를 저장할 2차원 String 배열을 생성하여 그 주소를 할당.
		Random random = new Random();						// 난수를 생성할 Random 객체 생성.
		random.setSeed(new Date().getTime());				// 난수 생성 시의 무작위성을 높이기 위해, Date 객체를 생성하여 시간을 시드로 셋팅.
		int ranVar;					// 난수를 저장할 변수.
		String picked_card;			// 한번 배분된 카드를 저장할 변수.
		
		for(int i=0; i<cards_Players.length; i++){
			for(int j=0; j<cards_Players[i].length; j++){							// 중첩 반복문을 통해 2차원 배열에 카드 저장.
				
				ranVar = random.nextInt(remaining_Cards.size());					// 난수 생성.
				picked_card = cards_Players[i][j] = remaining_Cards.get(ranVar);	// remaining_Cards 객체에서 생성한 난수 크기 만큼의 index에 
																					// 해당하는 값을 가져와 2차원 배열 및 picked_card 변수에 할당.
				remaining_Cards.remove(picked_card);		// 한번 배분된 카드는 remaining_Cards 객체에서 제거.
			}
		}
		
		return cards_Players;		// 플레이어들에게 배분한 카드를 저장한 cards_Players 객체를 반환.
	}
}

class sum_Calculator{				// 플레이어들이 각자 받은 카드들의 합계를 계산하는 클래스.
	
	String[][] cards_Players;
	
	public sum_Calculator(String[][] cards_Players){		// 생성자가 cards_Player 객체를 매개변수로 받아,
		this.cards_Players = cards_Players;				// 클래스의 멤버변수에 할당.
	}
	
	public int[] sum_Calc(){
		
		int sum[] = new int[4];							// 합계를 저장할 1차원 정수 배열을 생성하여 그 주소를 할당.
		
		for(int i=0; i<cards_Players.length; i++){
			for(int j=0; j<cards_Players[i].length; j++){	// 중첩 반복문을 통해 합계 저장.
				
				sum[i] += Integer.parseInt(cards_Players[i][j].substring(1));	// cards_Player 배열 객체에 저장되어 있는 String값을 불러와,
																				// 맨 앞의 영문을 뺀 나머지 String을 정수로 변환하여 저장.
			}
		}
		
		return sum;					// 합계를 저장한 sum 객체를 반환.
	}
}

class winners_Drawer{				// 우승자 번호를 추첨하는 클래스
	
	int[] sum;
	
	public winners_Drawer(int[] sum){		// 생성자가 sum 객체를 매개변수로 받아,
		this.sum = sum;						// 클래스의 멤버변수에 할당.
	}
	
	public List<Integer> winners_Draw(){
		
		int min_Score = sum[0];				// Player1의 합계를 최저점수로 셋팅.
		List<Integer> winners = new ArrayList<Integer>();	// 우승자들의 번호를 저장할 ArrayList 객체를 생성하여 그 주소를 할당.
		winners.add(1);						// Player1을 우승자 목록에 추가.
		
		for(int order=1; order<sum.length; order++){	// order : Player의 번호
														// ex) Player2의 order : 1, Player4의 order : 3
			if(min_Score > sum[order]){					// 다음 Player의 합계가 더 작을경우,
				min_Score = sum[order];					// 최저점수를 해당 Player의 합계로 바꾸고,
				winners = new ArrayList<Integer>();		// 우승자들의 번호를 저장할 ArrayList 객체를 새로 생성,
				winners.add(order+1);					// 해당 Player의 번호를 새로 생성한 winners 객체에 추가.
			}else if(min_Score == sum[order]) {			// 다음 Player의 합계가 같을 경우,
				winners.add(order+1);					// 해당 Player의 번호를 기존의 winners 객체에 추가.
			}
		}
		
		return winners;				// 우승자들의 번호가 저장된 winners 객체 반환.
	}
}

class total_Printer{				// cards_Printer 객체와 winners_Printer 객체를 통해 모든 메세지를 출력하는 클래스
	
	String[][] cards_Players;
	int[] sum;
	List<Integer> winners;
	
	public total_Printer(String[][] cards_Players, int[] sum, List<Integer> winners){
		
		this.cards_Players = cards_Players;
		this.sum = sum;
		this. winners = winners;
	}
	
	public void print(){
		
		cards_Printer cards_Printer = new cards_Printer(cards_Players, sum);
		cards_Printer.print();
		
		winners_Printer winners_Printer = new winners_Printer(winners);
		winners_Printer.print();
	}
}

class cards_Printer{				// 플레이어들에게 배분된 카드의 목록과 각각의 합계를 출력하는 클래스
	
	String[][] cards_Players;
	int[] sum;
	
	public cards_Printer(String[][] cards_Players, int[] sum){
		
		this.cards_Players = cards_Players;
		this.sum = sum;
	}
	
	public void print(){
		
		for(int i=0; i<cards_Players.length; i++){
			
			System.out.printf("Player%d: ", i+1);
			
			for(int j=0; j<cards_Players[i].length; j++){
				
				System.out.printf("%-3s", cards_Players[i][j]);
				
				if(j<cards_Players[i].length-1)
					System.out.print(",");
				else
					System.out.print(":");
			}
			
			System.out.println("sum = " + sum[i]);
		}
	}
}

class winners_Printer{				// 실패 시의 메세지와 우승자를 출력하는 클래스
	
	List<Integer> winners;
	
	public winners_Printer(List<Integer> winners){
		
		this.winners = winners;
	}
	
	public void print(){
		
		if(winners.size() == 1){					// 우승자가 1명일 때의 메세지
			System.out.print("\nWinner: Player" + winners.get(0));
		}else{										// 우승자가 2명 이상일 때의 메세지
			System.out.println("> 복수의 우승자가 나와 게임을 재시작합니다.");
			System.out.print("> 우승자: ");
			
			for(int i=0; i<winners.size(); i++){
				
				System.out.printf("Player%d", winners.get(i));
				if(i < winners.size()-1){
					System.out.print(", ");
				}
			}
			System.out.println();
			System.out.println();
		}
	}
}
