import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Microchips {
	
	private static Map<Integer, List<Integer>> bots = new HashMap<>();
	private static Map<Integer, List<Integer>> bins = new HashMap<>();

	private static final int TYPE1 = 61;
	private static final int TYPE2 = 17;
	
	public static void main(String[] args) {
		List<String> input;
		int botPt1 = -1;


		try {
			input = Files.readAllLines(Paths.get("day10_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		for(int i = 0; i < input.size(); i++) {
			String line = input.get(i);
			
			if(line.startsWith("value")){
				parseInitialValues(line.substring(6));
				input.remove(i);
				i--;
			}			
		}
		
		while(bots.size() > 0) {
			Set<Map.Entry<Integer, List<Integer>>> botSet = new HashSet<>(bots.entrySet());
			
			for (Map.Entry<Integer, List<Integer>> each : botSet) {
				
				if(each.getValue().contains(TYPE1) && each.getValue().contains(TYPE2)){
					botPt1 = each.getKey();
				}	
				
				if(each.getValue().size() == 2) {
					parseInstruction(input, each.getKey());
					bots.remove(each.getKey());
				}
			}
		}
		
		System.out.println(botPt1);
		
		int part2 = bins.get(0).get(0) * bins.get(1).get(0) * bins.get(2).get(0);
		
		System.out.println(part2);
		
	}

	private static void parseInstruction(List<String> input, int bot) {
		int botOut;
		String substring;
		int low;
		int high;
		boolean isLowBot;
		boolean isHighBot;
		
		for(int i = 0; i < input.size(); i++) {
			substring = input.get(i).substring(4);
			botOut = Integer.parseInt(substring.split(" gives low to ")[0]);
			if (bot == botOut) {
				substring = substring.split(" gives low to ")[1];

				if(substring.split(" and high to ")[0].startsWith("bot")){
					low = Integer.parseInt(substring.split(" and high to ")[0].substring(4));
					isLowBot = true;
				} else {
					low = Integer.parseInt(substring.split(" and high to ")[0].substring(7));
					isLowBot = false;
				}
				
				if(substring.split(" and high to ")[1].startsWith("bot")){
					high = Integer.parseInt(substring.split(" and high to ")[1].substring(4));
					isHighBot = true;
				} else {
					high = Integer.parseInt(substring.split(" and high to ")[1].substring(7));
					isHighBot = false;
				}
				
				
				List<Integer> temp;
				if(isLowBot) {
					
					
					if(bots.get(low) == null) {
						temp = new ArrayList<>(2);
					} else {
						temp = bots.get(low);
					}
					
					
					if(temp.size() < 2) {
						temp.add(Math.min(bots.get(bot).get(0), bots.get(bot).get(1)));
					} else {
						System.out.println("WTF");
						return;
					}
					bots.put(low, temp);
				} else {
					
					if(bins.get(low) == null) {
						temp = new ArrayList<>(2);
					} else {
						temp = bins.get(low);
					}

					temp.add(Math.min(bots.get(bot).get(0), bots.get(bot).get(1)));
					bins.put(low, temp);
				}
				
				if(isHighBot) {
					
					if(bots.get(high) == null) {
						temp = new ArrayList<>(2);
					} else {
						temp = bots.get(high);
					}
					
					if(temp.size() < 2) {
						temp.add(Math.max(bots.get(bot).get(0), bots.get(bot).get(1)));
					} else {
						System.out.println("WTF");
						return;
					}
					bots.put(high, temp);
				} else {
					
					if(bins.get(high) == null) {
						temp = new ArrayList<>(2);
					} else {
						temp = bins.get(high);
					}
					
					temp.add(Math.max(bots.get(bot).get(0), bots.get(bot).get(1)));
					bins.put(high, temp);
				}
				
				break;
			}
					
		}
			
	}

	private static void parseInitialValues(String substring) {
		int value = Integer.parseInt(substring.split(" goes to bot ")[0]);
		int bot = Integer.parseInt(substring.split(" goes to bot ")[1]);
		
		List<Integer> temp;
		
		if(bots.containsKey(bot)){
			temp = bots.get(bot);
			temp.add(value);
		} else {
			temp = new ArrayList<>(2);
			temp.add(value);
		}
		
		bots.put(bot, temp);

	}
	
}
