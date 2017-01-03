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
		
		// - height: 지도의 세로 크기
		// - width: 지도의 가로 크기
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
		
		// - preX: 직전의 X좌표 / preY: 직전의 Y좌표
		// - curX: 현재의 X좌표 / curY: 현재의 Y좌표
		// - distance: 이동거리
		// - finalDistance: 최단거리
		int preX=height-1, preY=-1;
		int curX=height-1, curY=0;
		int distance=1;
		int finalDistance = height*width;
		
		// Crossroads: 이동경로의 좌표 또는 분기의 좌표 및 방향을 담을 HashMap 객체
		Map<String, Object> Crossroads = new HashMap<String, Object>();
		
		// CrossroadsList: 이동경로를 담을 ArrayList 객체
		// RouteList: 최단경로를 담을 ArrayList 객체
		List<Map<String, Object>> CrossroadsList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> RouteList = new ArrayList<Map<String, Object>>();
		
		while(true){
			
			// 교차로에서 '나아갈' 분기의 방향들을 담은 리스트 객체 반환.
			CrossroadsDistinguisher CD = new CrossroadsDistinguisher(preX, preY, curX, curY, map, width, height);
			List<String> CrossroadsKinds = CD.CrossroadsDistinguish();
			
			// 현재 좌표가 이미 지나온 곳인지를 판별하기 위해 필요한 Map 객체들 생성.
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
			
			// 막다른 골목에 이르거나, 출구에 다다르거나, 이미 지나온 곳일 때.
			if((CrossroadsKinds.size() == 0) || (curX==0 && curY==width-1) || CrossroadsList.contains(Crossroads1) || CrossroadsList.contains(Crossroads2) || CrossroadsList.contains(Crossroads3) || CrossroadsList.contains(Crossroads4) || CrossroadsList.contains(Crossroads5)){
				
				// 현재 위치의 좌표를 이동경로 리스트에 추가.
				Crossroads = new HashMap<String, Object>();
				Crossroads.put("X", curX);
				Crossroads.put("Y", curY);
				Crossroads.put("direction", null);
				CrossroadsList.add(Crossroads);
				
				// - 출구까지의 이동거리가 이전에 구했던 최단거리보다 짧거나 같을 경우,
				//  최단거리를 이동거리로 갱신.
				if((curX==0 && curY==width-1) && (distance<=finalDistance)){
					
					// - 출구까지의 이동거리가 이전에 구했던 최단거리보다 짧을 경우,
					//  최단이동경로의 좌표를 담을 리스트 객체를 다시 생성. 
					if(distance<finalDistance){
						RouteList = new ArrayList<Map<String, Object>>();
					}
					
					finalDistance = distance;
					
					// 새로운 최단이동경로 추가
					Iterator<Map<String, Object>> it3 = CrossroadsList.iterator();
					while(it3.hasNext()){
						RouteList.add(it3.next());
					}
				}
				
				// < 프로그램 종료 판별 로직 : 시작 >
				// - 입구로부터 이어진 모든 길을 한번씩 지나도록 설계함.
				// - 교차로를 지날 때는 지나친 분기의 좌표와 모든 방향을 각각 
				//  이동경로 리스트 객체에 저장해둠.
				// - 나아갔던 경로가 막다른 골목이나 출구, 이미 지나온 경로에 이르면 바로 직전의
				//  분기로 돌아와 남은 방향 중 한 곳으로 다시 나아가는 원리.
				// - 이때, 남아있던 분기의 좌표와 방향 중, 방향정보가 담긴 'direction'키의
				//  값을 null로 바꿔줌.
				// - 결국 막다른 골목이나 출구, 이미 지나온 경로에 이를 때마다,
				//  'direction'키 값이 null이 아닌 것의 존재 유무를 판단하고,
				//  모두 null일 때, 모든 경로를 지나온 것으로 판단해 프로그램을 종료.
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
				//// < 프로그램 종료 판별 로직 : 끝 >

				// - 막다른 골목이나 출구, 이미 지나온 경로에 이르렀을 때,
				//  바로 직전의 분기로 돌아올 때까지,
				//  이동경로를 담은 리스트 객체에서 마지막 좌표를 하나씩 제거하면서
				//  이동거리도 1씩 줄임.
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
				
				// - 현재 위치의 X좌표와 Y좌표를 입력하는 변수 preX와 preY에 각각,
				//  분기의 X좌표 값과 Y좌표 값을 대입해 분기를 직전 위치로 만들고,
				//  분기의 방향 값에 따라 이동시킨 좌표를 현재 위치로 만듦.
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
				
				// - 직전에 꺼낸 방향 값이 담긴 Hashmap 객체의 좌표와
				//  이동경로 리스트에 남아있는 마지막 Hashmap 객체의 좌표를 비교해,
				//  아직 분기하지 않은 방향이 남아있는 지를 판별.
				// - 모든 방향으로 한번씩 분기할 때마다 소진되어 하나도 남지않게 된 교차로의 좌표를,
				//  방향 값에 null을 대입한 Hashmap 객체를 통해 이동경로 리스트에 추가.
				if(CrossroadsList.size()>0){
					
					Map<String, Object> k = CrossroadsList.get(CrossroadsList.size()-1);
					if((int)k.get("X")!=preX || (int)k.get("Y")!=preY){
						Crossroads6.put("direction", null);
						CrossroadsList.add(Crossroads6);
					}
				}
				// - 교차로의 좌표가 입구여서 이동경로를 담은 리스트 객체의 크기가 0일 때,
				//  입구의 좌표를 이동경로에 추가.
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
			System.out.println("- 출구로 나가는 길이 존재하지 않음.");
		else{
			System.out.println("- 최단거리 : "+finalDistance);
			System.out.println("- 최단경로 수 : "+RouteList.size()/finalDistance);
		}
		
		System.out.println();
		int m=0;
		for(int k=0; k<(RouteList.size()/finalDistance); k++){
			
			System.out.println("< "+(k+1)+"번째 경로 >");
			
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
						System.out.print("＋");
					else if(map[i][j]==1)
						System.out.print("■");
					else
						System.out.print("　");
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
