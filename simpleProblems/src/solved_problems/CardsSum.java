package solved_problems;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CardsSum {

	public static void main(String[] args) {
		
		// 1���� String �迭 cards�� 52���� ī�带 ����.
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
		List<Integer> winners;			// ������� ��ȣ�� ������ ArrayList ��ü�� �Ҵ��� ����
		total_Printer total_Printer;
		
		do{
			cards_Filler = new cards_Filler(cards);			// ������ �� ���� �����ϱ⿡ �ռ�, cards �迭�� �Ű�������, ī�带 ���� ä��� �۾��� �� ��ü�� ����.
			remaining_Cards = cards_Filler.cards_Fill();		// ī�带 ä�� ArrayList ��ü�� �ּҸ� ��ȯ�޾� remaining_Cards ������ �Ҵ�. 
			
			cards_Divider = new cards_Divider(remaining_Cards);	// remaining_Cards ��ü�� �Ű�������, ī�带 �÷��̾�鿡�� �������� ����ϴ� �۾��� �� ��ü ����.
			cards_Players = cards_Divider.cards_Div();			// ����� ī�带 ���� 2���� String �迭�� �ּ��� ��ȯ�޾� cards_Players ������ �Ҵ�.
																			// ex) cards_Players[0][6] -> Player1���� �������� ��е� ī��.
																			//     cards_Players[3][6] -> Player4���� �������� ��е� ī��.
			
			sum_Calculator = new sum_Calculator(cards_Players);	// cards_Players ��ü�� �Ű�������, �÷��̾� ������ �հ踦 ���ϴ� �۾��� �� ��ü ����.
			sum = sum_Calculator.sum_Calc();							// ����� �հ踦 ������ 1���� ���� �迭�� �ּҸ� ��ȯ�޾� sum ������ �Ҵ�.
																			// ex) sum[0] -> Player1�� sum.
																			// ex) sum[3] -> Player4�� sum.
			
			winners_Drawer = new winners_Drawer(sum);		// sum ��ü�� �Ű�������, ����� ��ȣ�� ��÷�ϴ� ��ü ����(1���� ����, �ƴ� ���� ����).
			winners = winners_Drawer.winners_Draw();						// ����ڸ� ������ ArrayList ��ü�� �ּҸ� ��ȯ�޾� winners ������ �Ҵ�.
			
			total_Printer = new total_Printer(cards_Players, sum, winners);	// ������� ��ȣ�� �Ҵ��� winners ��ü�� ����� �Ǻ���,
			total_Printer.print();											// ������ ��쿡 �ش��ϴ� �޼��� ���.
			
		}while(winners.size() > 1);			// ����ڰ� 1���� �� ������ ���� ������ �ݺ�.
		
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



class cards_Filler{							// cards �迭�� �Ű�������, ī�带 ���� ä��� �۾��� �ϴ� Ŭ����.
	
	String[] cards;
	
	public cards_Filler(String[] cards){	// �����ڰ� cards �迭�� �Ű������� �޾�,
		this.cards = cards;					// Ŭ������ ��������� �Ҵ�.
	}
	
	public List<String> cards_Fill(){
		
		List<String> remaining_Cards = new ArrayList<String>();		// ī�带 ���� ArrayList ��ü�� �����Ͽ� �� �ּҸ� �Ҵ�.
		
		for(int i=0; i<cards.length; i++){
			remaining_Cards.add(cards[i]);		// �ݺ����� �̿���, ��� ī�带 remaining_Cards ��ü�� ����.
		}
		
		return remaining_Cards;					// ī�带 ������ remaining_Cards ��ü�� ��ȯ.
	}
}

class cards_Divider{						// remaining_Cards ��ü�� �Ű�������, ī�带 �÷��̾�鿡�� ������ �۾��� �ϴ� Ŭ����.
	
	List<String> remaining_Cards;
	
	public cards_Divider(List<String> remaining_Cards){		// �����ڰ� remaining_Cards ��ü�� �Ű������� �޾�,
		this.remaining_Cards = remaining_Cards;				// Ŭ������ ��������� �Ҵ�.
	}
	
	public String[][] cards_Div(){

		String[][] cards_Players = new String[4][7];		// �÷��̾�鿡�� ����� ī�带 ������ 2���� String �迭�� �����Ͽ� �� �ּҸ� �Ҵ�.
		Random random = new Random();						// ������ ������ Random ��ü ����.
		random.setSeed(new Date().getTime());				// ���� ���� ���� ���������� ���̱� ����, Date ��ü�� �����Ͽ� �ð��� �õ�� ����.
		int ranVar;					// ������ ������ ����.
		String picked_card;			// �ѹ� ��е� ī�带 ������ ����.
		
		for(int i=0; i<cards_Players.length; i++){
			for(int j=0; j<cards_Players[i].length; j++){							// ��ø �ݺ����� ���� 2���� �迭�� ī�� ����.
				
				ranVar = random.nextInt(remaining_Cards.size());					// ���� ����.
				picked_card = cards_Players[i][j] = remaining_Cards.get(ranVar);	// remaining_Cards ��ü���� ������ ���� ũ�� ��ŭ�� index�� 
																					// �ش��ϴ� ���� ������ 2���� �迭 �� picked_card ������ �Ҵ�.
				remaining_Cards.remove(picked_card);		// �ѹ� ��е� ī��� remaining_Cards ��ü���� ����.
			}
		}
		
		return cards_Players;		// �÷��̾�鿡�� ����� ī�带 ������ cards_Players ��ü�� ��ȯ.
	}
}

class sum_Calculator{				// �÷��̾���� ���� ���� ī����� �հ踦 ����ϴ� Ŭ����.
	
	String[][] cards_Players;
	
	public sum_Calculator(String[][] cards_Players){		// �����ڰ� cards_Player ��ü�� �Ű������� �޾�,
		this.cards_Players = cards_Players;				// Ŭ������ ��������� �Ҵ�.
	}
	
	public int[] sum_Calc(){
		
		int sum[] = new int[4];							// �հ踦 ������ 1���� ���� �迭�� �����Ͽ� �� �ּҸ� �Ҵ�.
		
		for(int i=0; i<cards_Players.length; i++){
			for(int j=0; j<cards_Players[i].length; j++){	// ��ø �ݺ����� ���� �հ� ����.
				
				sum[i] += Integer.parseInt(cards_Players[i][j].substring(1));	// cards_Player �迭 ��ü�� ����Ǿ� �ִ� String���� �ҷ���,
																				// �� ���� ������ �� ������ String�� ������ ��ȯ�Ͽ� ����.
			}
		}
		
		return sum;					// �հ踦 ������ sum ��ü�� ��ȯ.
	}
}

class winners_Drawer{				// ����� ��ȣ�� ��÷�ϴ� Ŭ����
	
	int[] sum;
	
	public winners_Drawer(int[] sum){		// �����ڰ� sum ��ü�� �Ű������� �޾�,
		this.sum = sum;						// Ŭ������ ��������� �Ҵ�.
	}
	
	public List<Integer> winners_Draw(){
		
		int min_Score = sum[0];				// Player1�� �հ踦 ���������� ����.
		List<Integer> winners = new ArrayList<Integer>();	// ����ڵ��� ��ȣ�� ������ ArrayList ��ü�� �����Ͽ� �� �ּҸ� �Ҵ�.
		winners.add(1);						// Player1�� ����� ��Ͽ� �߰�.
		
		for(int order=1; order<sum.length; order++){	// order : Player�� ��ȣ
														// ex) Player2�� order : 1, Player4�� order : 3
			if(min_Score > sum[order]){					// ���� Player�� �հ谡 �� �������,
				min_Score = sum[order];					// ���������� �ش� Player�� �հ�� �ٲٰ�,
				winners = new ArrayList<Integer>();		// ����ڵ��� ��ȣ�� ������ ArrayList ��ü�� ���� ����,
				winners.add(order+1);					// �ش� Player�� ��ȣ�� ���� ������ winners ��ü�� �߰�.
			}else if(min_Score == sum[order]) {			// ���� Player�� �հ谡 ���� ���,
				winners.add(order+1);					// �ش� Player�� ��ȣ�� ������ winners ��ü�� �߰�.
			}
		}
		
		return winners;				// ����ڵ��� ��ȣ�� ����� winners ��ü ��ȯ.
	}
}

class total_Printer{				// cards_Printer ��ü�� winners_Printer ��ü�� ���� ��� �޼����� ����ϴ� Ŭ����
	
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

class cards_Printer{				// �÷��̾�鿡�� ��е� ī���� ��ϰ� ������ �հ踦 ����ϴ� Ŭ����
	
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

class winners_Printer{				// ���� ���� �޼����� ����ڸ� ����ϴ� Ŭ����
	
	List<Integer> winners;
	
	public winners_Printer(List<Integer> winners){
		
		this.winners = winners;
	}
	
	public void print(){
		
		if(winners.size() == 1){					// ����ڰ� 1���� ���� �޼���
			System.out.print("\nWinner: Player" + winners.get(0));
		}else{										// ����ڰ� 2�� �̻��� ���� �޼���
			System.out.println("> ������ ����ڰ� ���� ������ ������մϴ�.");
			System.out.print("> �����: ");
			
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
