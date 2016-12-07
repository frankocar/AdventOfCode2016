import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IPv7 {

	public static void main(String[] args) {

		List<String> input;

		int validIP = 0;
		int validSSL = 0;

		try {
			input = Files.readAllLines(Paths.get("day7_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}

		for (String line : input) {

			List<String> hypernet = new ArrayList<>();
			List<String> nonHyper = new ArrayList<>();

			line += '\0'; //To find the end of the line

			String temp = "";

			for (char ch : line.toCharArray()) {
				temp += ch;

				if (ch == '[' || ch == '\0') {
					nonHyper.add(temp.substring(0, temp.length() - 1));
					temp = "";
				}

				if (ch == ']') {
					hypernet.add(temp.substring(0, temp.length() - 1));
					temp = "";
				}
			}

			if (hasSequence(nonHyper) && !hasSequence(hypernet)) {
				validIP++;
			}

			if (supportsSSL(hypernet, nonHyper)) {
				validSSL++;
			}
		}

		System.out.println("Supports TLS: " + validIP);
		System.out.println("Supports SSL: " + validSSL);

	}

	private static boolean hasSequence(List<String> list) {

		for (String each : list) {
			char[] array = each.toCharArray();
			for (int i = 0; i < array.length - 3; i++) {
				if (array[i] == array[i + 3] && array[i + 1] == array[i + 2] && array[i] != array[i + 1]) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean supportsSSL(List<String> hypernet, List<String> nonHyper) {

		Set<String> ABA = new HashSet<>();
		Set<String> invertedBAB = new HashSet<>();

		for (String s : nonHyper) {
			for (int i = 0; i <= s.length() - 3; i++) {
				String sub = s.substring(i, i + 3);
				if (sub.charAt(0) == sub.charAt(2) && sub.charAt(0) != sub.charAt(1)) {
					ABA.add(sub);
				}
			}
		}

		for (String s : hypernet) {
			for (int i = 0; i <= s.length() - 3; i++) {
				String sub = s.substring(i, i + 3);
				if (sub.charAt(0) == sub.charAt(2) && sub.charAt(0) != sub.charAt(1)) {
					invertedBAB.add("" + sub.charAt(1) + sub.charAt(0) + sub.charAt(1));
				}
			}
		}

		ABA.retainAll(invertedBAB);
		if (ABA.size() > 0) {
			return true;
		}

		return false;

	}

}
