import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

	public static void main(String[] args) {
		List<String> input;
		char[] message1 = new char[8];
		char[] message2 = new char[8];

		try {
			input = Files.readAllLines(Paths.get("day6_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		for(int i = 0; i < input.get(0).length(); i++) {
			Map<Character, Integer> map = new HashMap<>();
			int count = 0;
			for (String line : input) {
				char x = line.charAt(i);
				if (map.containsKey(x)) {
					count = map.get(x) + 1;
					map.put(x, count);
				} else {
					count = 1;
					map.put(x, count);
				}
			}
			
			char mostCommon = 'a';
			char leastCommon = 'a';
			
			
			for (Map.Entry<Character, Integer> each : map.entrySet()) {
				if (each.getValue() > map.get(mostCommon)) {
					mostCommon = each.getKey();
				}
				
				if (each.getValue() < map.get(leastCommon)) {
					leastCommon = each.getKey();
				}
			}
			
			message1[i] = mostCommon;
			message2[i] = leastCommon;
		}
		
		System.out.println(new String(message1));
		System.out.println(new String(message2));
	}
}
