package unsolved_problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShortestWay {

	public static void main(String[] args) {
		
		//Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = "";
		
		try {
			line = br.readLine().trim().replaceAll("\\s", "");
		} catch (Exception e) {
			e.toString();
		}
		
		// - height: ������ ���� ũ��
		// - width: ������ ���� ũ��
		int height = Integer.parseInt(line.substring(0, 1));
		int width = Integer.parseInt(line.substring(1, 2));
		
		int[][]map = new int[height][width];
		
		for(int i=0; i<height; i++){
			
			try {
				line = br.readLine().trim().replaceAll("\\s", "");
			} catch (Exception e) {
				e.toString();
			}
			
			for(int j=0; j<width; j++)
				map[i][j] = Integer.parseInt(line.substring(j, j+1));
		}
		
		// - preX: ������ X��ǥ / preY: ������ Y��ǥ
		// - curX: ������ X��ǥ / curY: ������ Y��ǥ
		// - distance: �̵��Ÿ�
		// - finalDistance: �ִܰŸ�
		int preX=height-1, preY=-1;
		int curX=height-1, curY=0;
		int distance=1;
		int finalDistance = height*width;
		
		// Crossroads: �̵������ ��ǥ �Ǵ� �б��� ��ǥ �� ������ ���� HashMap ��ü
		Map<String, Object> Crossroads = new HashMap<String, Object>();
		
		// CrossroadsList: �̵���θ� ���� ArrayList ��ü
		// RouteList: �ִܰ�θ� ���� ArrayList ��ü
		List<Map<String, Object>> CrossroadsList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> RouteList = new ArrayList<Map<String, Object>>();
		
		while(true){
			
			// �����ο��� '���ư�' �б��� ������� ���� ����Ʈ ��ü ��ȯ.
			CrossroadsDistinguisher CD = new CrossroadsDistinguisher(preX, preY, curX, curY, map, width, height);
			List<String> CrossroadsKinds = CD.CrossroadsDistinguish();
			
			// ���� ��ǥ�� �̹� ������ �������� �Ǻ��ϱ� ���� �ʿ��� Map ��ü�� ����.
			Map<String, Object> Crossroads1 = new HashMap<String, Object>();
			Crossroads1.put("X", curX);
			Crossroads1.put("Y", curY);
			Crossroads1.put("direction", "right");
			Map<String, Object> Crossroads2 = new HashMap<String, Object>();
			Crossroads2.put("X", curX);
			Crossroads2.put("Y", curY);
			Crossroads2.put("direction", "up");
			Map<String, Object> Crossroads3 = new HashMap<String, Object>();
			Crossroads3.put("X", curX);
			Crossroads3.put("Y", curY);
			Crossroads3.put("direction", "left");
			Map<String, Object> Crossroads4 = new HashMap<String, Object>();
			Crossroads4.put("X", curX);
			Crossroads4.put("Y", curY);
			Crossroads4.put("direction", "down");
			Map<String, Object> Crossroads5 = new HashMap<String, Object>();
			Crossroads5.put("X", curX);
			Crossroads5.put("Y", curY);
			Crossroads5.put("direction", null);
			
			// ���ٸ� ��� �̸��ų�, �ⱸ�� �ٴٸ��ų�, �̹� ������ ���� ��.
			if((CrossroadsKinds.size() == 0) || (curX==0 && curY==width-1) || CrossroadsList.contains(Crossroads1) || CrossroadsList.contains(Crossroads2) || CrossroadsList.contains(Crossroads3) || CrossroadsList.contains(Crossroads4) || CrossroadsList.contains(Crossroads5)){
				
				// ���� ��ġ�� ��ǥ�� �̵���� ����Ʈ�� �߰�.
				Crossroads = new HashMap<String, Object>();
				Crossroads.put("X", curX);
				Crossroads.put("Y", curY);
				Crossroads.put("direction", null);
				CrossroadsList.add(Crossroads);
				
				// - �ⱸ������ �̵��Ÿ��� ������ ���ߴ� �ִܰŸ����� ª�ų� ���� ���,
				//  �ִܰŸ��� �̵��Ÿ��� ����.
				if((curX==0 && curY==width-1) && (distance<=finalDistance)){
					
					// - �ⱸ������ �̵��Ÿ��� ������ ���ߴ� �ִܰŸ����� ª�� ���,
					//  �ִ��̵������ ��ǥ�� ���� ����Ʈ ��ü�� �ٽ� ����. 
					if(distance<finalDistance){
						RouteList = new ArrayList<Map<String, Object>>();
					}
					
					finalDistance = distance;
					
					// ���ο� �ִ��̵���� �߰�
					Iterator<Map<String, Object>> it3 = CrossroadsList.iterator();
					while(it3.hasNext()){
						RouteList.add(it3.next());
					}
				}
				
				// < ���α׷� ���� �Ǻ� ���� : ���� >
				// - �Ա��κ��� �̾��� ��� ���� �ѹ��� �������� ������.
				// - �����θ� ���� ���� ����ģ �б��� ��ǥ�� ��� ������ ���� 
				//  �̵���� ����Ʈ ��ü�� �����ص�.
				// - ���ư��� ��ΰ� ���ٸ� ����̳� �ⱸ, �̹� ������ ��ο� �̸��� �ٷ� ������
				//  �б�� ���ƿ� ���� ���� �� �� ������ �ٽ� ���ư��� ����.
				// - �̶�, �����ִ� �б��� ��ǥ�� ���� ��, ���������� ��� 'direction'Ű��
				//  ���� null�� �ٲ���.
				// - �ᱹ ���ٸ� ����̳� �ⱸ, �̹� ������ ��ο� �̸� ������,
				//  'direction'Ű ���� null�� �ƴ� ���� ���� ������ �Ǵ��ϰ�,
				//  ��� null�� ��, ��� ��θ� ������ ������ �Ǵ��� ���α׷��� ����.
				int cnt = 0;
				Iterator<Map<String, Object>> it = CrossroadsList.iterator();
				while(it.hasNext()){
					
					if(it.next().get("direction")!=null){
						break;
					}
					cnt++;
				}
				
				if(cnt==CrossroadsList.size()){
					break;
				}
				//// < ���α׷� ���� �Ǻ� ���� : �� >

				// - ���ٸ� ����̳� �ⱸ, �̹� ������ ��ο� �̸����� ��,
				//  �ٷ� ������ �б�� ���ƿ� ������,
				//  �̵���θ� ���� ����Ʈ ��ü���� ������ ��ǥ�� �ϳ��� �����ϸ鼭
				//  �̵��Ÿ��� 1�� ����.
				Map<String, Object> Crossroads6 = new HashMap<String, Object>();
				while(true){
					
					Crossroads6 = CrossroadsList.get(CrossroadsList.size()-1);
					CrossroadsList.remove(CrossroadsList.size()-1);
					distance--;
					
					if(Crossroads6.get("direction")!=null){
						break;
					}
				}
				distance++;
				
				// - ���� ��ġ�� X��ǥ�� Y��ǥ�� �Է��ϴ� ���� preX�� preY�� ����,
				//  �б��� X��ǥ ���� Y��ǥ ���� ������ �б⸦ ���� ��ġ�� �����,
				//  �б��� ���� ���� ���� �̵���Ų ��ǥ�� ���� ��ġ�� ����.
				preX=curX=(int)Crossroads6.get("X");
				preY=curY=(int)Crossroads6.get("Y");
				if(Crossroads6.get("direction")=="right"){
					curY++;
				}else if(Crossroads6.get("direction")=="up"){
					curX--;
				}else if(Crossroads6.get("direction")=="left"){
					curY--;
				}else if(Crossroads6.get("direction")=="down"){
					curX++;
				}
				
				// - ������ ���� ���� ���� ��� Hashmap ��ü�� ��ǥ��
				//  �̵���� ����Ʈ�� �����ִ� ������ Hashmap ��ü�� ��ǥ�� ����,
				//  ���� �б����� ���� ������ �����ִ� ���� �Ǻ�.
				// - ��� �������� �ѹ��� �б��� ������ �����Ǿ� �ϳ��� �����ʰ� �� �������� ��ǥ��,
				//  ���� ���� null�� ������ Hashmap ��ü�� ���� �̵���� ����Ʈ�� �߰�.
				if(CrossroadsList.size()>0){
					
					Map<String, Object> k = CrossroadsList.get(CrossroadsList.size()-1);
					if((int)k.get("X")!=preX || (int)k.get("Y")!=preY){
						Crossroads6.put("direction", null);
						CrossroadsList.add(Crossroads6);
					}
				}
				// - �������� ��ǥ�� �Ա����� �̵���θ� ���� ����Ʈ ��ü�� ũ�Ⱑ 0�� ��,
				//  �Ա��� ��ǥ�� �̵���ο� �߰�.
				else{
					Crossroads6.put("X", preX);
					Crossroads6.put("Y", preY);
					Crossroads6.put("direction", null);
					CrossroadsList.add(Crossroads6);
				}
				
				distance++;
			}
			else{
				
				if(CrossroadsKinds.size() == 1){
					
					Crossroads = new HashMap<String, Object>();
					Crossroads.put("X", curX);
					Crossroads.put("Y", curY);
					Crossroads.put("direction", null);
					CrossroadsList.add(Crossroads);
	
					preX=curX;
					preY=curY;
					
					if(CrossroadsKinds.get(0)=="right"){
						curY++;
					}else if(CrossroadsKinds.get(0)=="up"){
						curX--;
					}else if(CrossroadsKinds.get(0)=="left"){
						curY--;
					}else if(CrossroadsKinds.get(0)=="down"){
						curX++;
					}
				}
				else if(CrossroadsKinds.size() > 1){
					
					int i;
					for(i=0; i<CrossroadsKinds.size()-1; i++){
						
						Crossroads = new HashMap<String, Object>();
						Crossroads.put("X", curX);
						Crossroads.put("Y", curY);
						Crossroads.put("direction", CrossroadsKinds.get(i));
						CrossroadsList.add(Crossroads);
					}
					
					preX=curX;
					preY=curY;
					
					if(CrossroadsKinds.get(i)=="right"){
						curY++;
					}else if(CrossroadsKinds.get(i)=="up"){
						curX--;
					}else if(CrossroadsKinds.get(i)=="left"){
						curY--;
					}else if(CrossroadsKinds.get(i)=="down"){
						curX++;
					}
				}
				
				distance++;
			}
			
		}
		
		for(int i=0; i<RouteList.size(); i++){
			
			if(RouteList.get(i).get("direction")!=null)
				RouteList.remove(i);
		}
		
		/*Iterator<Map<String, Object>> it2 = RouteList.iterator();
		while(it2.hasNext()){
			Map<String, Object> k = it2.next();
			System.out.println("("+k.get("X")+","+k.get("Y")+") "+k.get("direction"));
		}*/

		System.out.println();
		if(finalDistance==height*width)
			System.out.println("- �ⱸ�� ������ ���� �������� ����.");
		else{
			System.out.println("- �ִܰŸ� : "+finalDistance);
			System.out.println("- �ִܰ�� �� : "+RouteList.size()/finalDistance);
		}
		
		System.out.println();
		int m=0;
		for(int k=0; k<(RouteList.size()/finalDistance); k++){
			
			System.out.println("< "+(k+1)+"��° ��� >");
			
			CrossroadsList = new ArrayList<Map<String, Object>>();
			int n=0;
			while(n<finalDistance){
				CrossroadsList.add(RouteList.get(m));
				n++;
				m++;
			}
			
			for(int i=0; i<height; i++){
				for(int j=0; j<width; j++){
					
					Crossroads = new HashMap<String, Object>();
					Crossroads.put("X",i);
					Crossroads.put("Y",j);
					Crossroads.put("direction",null);
					
					if(CrossroadsList.contains(Crossroads))
						System.out.print("��");
					else if(map[i][j]==1)
						System.out.print("��");
					else
						System.out.print("��");
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}

class CrossroadsDistinguisher{

	int width, height;
	int preX, preY;
	int curX, curY;
	int[][] map;
	
	public CrossroadsDistinguisher(int preX, int preY, int curX, int curY, int[][] map, int width, int height){
		this.width = width;
		this.height = height;
		this.preX = preX;
		this.preY = preY;
		this.curX = curX;
		this.curY = curY;
		this.map = map;
	}
	
	public List<String> CrossroadsDistinguish(){
		
		List<String> CrossroadsKinds = new ArrayList<String>();

		if(curX!=height-1 && map[curX+1][curY]==0 && preX!=(curX+1)){
			CrossroadsKinds.add("down");
		}
		if(curY!=0 && map[curX][curY-1]==0 && preY!=(curY-1)){
			CrossroadsKinds.add("left");
		}
		if(curX!=0 && map[curX-1][curY]==0 && preX!=(curX-1)){
			CrossroadsKinds.add("up");
		}
		if(curY!=width-1 && map[curX][curY+1]==0 && preY!=(curY+1)){
			CrossroadsKinds.add("right");
		}
		
		return CrossroadsKinds;
	}
}
