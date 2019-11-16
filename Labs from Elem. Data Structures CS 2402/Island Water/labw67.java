import java.util.Scanner;
public class labw67 {
	
	public static int[] randomMap(int size) {
		int[] map = new int[size];
		for(int i=0; i<size; i++) {
			map[i]= (int)(Math.random()*9 +1);
		}
		
		return map;
	}
	
	public static int leftLimit(int[] map) {
		int i=0;
		while(i<map.length-1 && map[i]<=map[i+1]) {
			i++;
		}
		return i;
	}
	
	public static int rightLimit(int[] map) {
		int i=map.length-1;
		while(i>0 && map[i]<=map[i-1]) {
			i--;
		}
		return i;
	}
	
	public int islandWater(int[] map) {
		int volume = 0;
		int left = leftLimit(map);
		int right = rightLimit(map);
		int level = 0;
		if (map[left]>map[right]) {
			level = right;
		}else {
			level = left;
		}
		for (int i = left+1; i<right; i++) {
			if (map[i]<map[level]) {
				volume += map[level]-map[i];
			}
		}
		
		int size = right-left;
		if(size < 3 ) {
			return 0;
		}
		if(level == right) {
			right--;
		}else {
			left++;
		}
		int[] newMap = new int[size];
		for (int i=left; i<=right; i++) {
			newMap[i-left] = map[i] - map[level];
			if (newMap[i-left]<0) {
				newMap[i-left]=0;
			}
		}
		return volume + islandWater(newMap);
	}

	public int maxLeft(int[] map, int lim) {
		int max=0;
		for (int i = max; i<=lim; i++) {
			if(map[i]>map[max]) {
				max = i;
			}
		}
		return max;
	}
	
	public int maxRight(int[] map, int lim) {
		int max=map.length-1;
		for (int i= max ; i>=lim; i--) {
			if(map[i]>map[max]) {
				max = i;
			}
		}
		return max;
	}
	
	public int islandWater2(int[] map) {
		int volume = 0;
		int left =0;
		int right =0;
		for(int i = 0; i<map.length; i++) {
			left = maxLeft(map, i);
			right = maxRight(map,i);
			if(map[left] > map[i] | map[right] > map[i]) {
				if(map[left]>map[right]) {
					volume += map[right] - map[i];
				}else{
					volume += map[left] - map[i];
				}
			}
		}
		return volume;
	}

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		int[] map1 = {1,3,2,4,1,3,1,4,5,2,2,1,4,2,2}; //15
		int[] map2 = {2,0,0,1,0,3,2}; //7
		int[] map3 = {1,2}; //0
		
		System.out.println("Testing:");
		System.out.println("Vol map1: " + islandWater(map1));
		System.out.println("Vol map2: " + islandWater(map2));
		System.out.println("Vol map3: " + islandWater(map3));
		
		System.out.print("What size is your new island? ");
		int size = scnr.nextInt();
		int[] newMap = randomMap(size);
		for(int i=0; i<size; i++) {
			System.out.print(newMap[i]+ " ");
		}
		System.out.println();
		System.out.println("Vol new map: " + islandWater(newMap));
		
		System.out.println("Testing:");
		System.out.println("Vol map1: " + islandWater2(map1));
		System.out.println("Vol map2: " + islandWater2(map2));
		System.out.println("Vol map3: " + islandWater2(map3));
		
		System.out.print("What size is your new island? ");
		size = scnr.nextInt();
		newMap = randomMap(size);
		for(int i=0; i<size; i++) {
			System.out.print(newMap[i]+ " ");
		}
		System.out.println();
		System.out.println("Vol new map: " + islandWater2(newMap));
		scnr.close();
	}

}
