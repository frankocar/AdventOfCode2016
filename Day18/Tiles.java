import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tiles {

	public static void main(String[] args) {
		String input;
		try {
			input = Files.readAllLines(Paths.get("day18_data.txt")).get(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		System.out.println("Step 1: " + solve(input, false));
		System.out.println("Step 2: " + solve(input, true));

	}

	public static int solve(String input, boolean part2) {
		List<boolean[]> list = new ArrayList<>();
		boolean[] safe = new boolean[input.length()];
		int num = 0;
		
		for (int i = 0; i < input.length(); i++) {
			safe[i] = (input.charAt(i) == '.');
			if(safe[i]) num++;
		}
		
		list.add(safe);
		
		for (int i = 1; i < (part2 ? 400000 : 40); i++) {
			boolean[] previous = list.get(i - 1);
			safe = new boolean[input.length()];
			
			for (int j = 0; j < input.length(); j++) {
				if(j == 0) {
					safe[j] = (previous[j] == true && previous[j+1] == true) || (previous[j] == false && previous[j+1] == true);
					if(safe[j]) num++;
				} else if (j == (input.length() - 1)) {
					safe[j] = (previous[j - 1] == true && previous[j] == true) || (previous[j - 1] == true && previous[j] == false);
					if(safe[j]) num++;
				} else {
					safe[j] = (previous[j - 1] == previous[j + 1]);
					if(safe[j]) num++;
				}
				
			}
			
			list.add(safe);			
		}
		
		return num;
	}

}
