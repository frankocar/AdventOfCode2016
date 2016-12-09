import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;

public class Compression {

	public static void main(String[] args) {
		String input;

		try {
			input = Files.readAllLines(Paths.get("day9_data.txt")).get(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		System.out.println("Part one: " + solve(input, false));
		System.out.println("Part one: " + solve(input, true));

	}

	public static long solve(String data, boolean part2) {
		long count = 0;
		char[] array = data.toCharArray();

		for (int i = 0; i < array.length; i++) {
			if (array[i] == ' ') {
				continue;
			} else if (array[i] == '(') {
				int end = data.indexOf(')', i);
				int howMuch = Integer.valueOf(data.substring(i + 1, end).split("x")[0]);
				int times = Integer.valueOf(data.substring(i + 1, end).split("x")[1]);
				String repeat = data.substring(end + 1, end + 1 + howMuch);
				count += times * ((part2) ? solve(repeat, true) : repeat.length());
				i = end + howMuch;
			} else {
				count++;
			}
		}

		return count;
	}

}
