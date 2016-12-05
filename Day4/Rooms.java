import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Rooms {

	public static void main(String[] args) {
		List<String> input;

		try {
			input = Files.readAllLines(Paths.get("day4_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		int totalId = 0;

		for (String line : input) {
			
			String checksum = line.split("\\[")[1];
			checksum = checksum.substring(0, checksum.length() - 1);
			
			String text = line.split("\\[")[0];
			
			String[] data = line.split("\\[")[0].split("-");
			
			int sectorId = Integer.parseInt(data[data.length - 1]);
						
			totalId += step1(data, checksum, sectorId);
			
			step2(text, sectorId);
			
		}
						
		System.out.println(totalId);
			
	}

	public static void step2(String text, int sectorId) {
		
		char[] array = new char[text.toCharArray().length];
		int i = 0;
		
		for(char x : text.toCharArray()) {
			
			if (x == '-') {
				x = ' ';
			}
			
			if(x >= 'a' && x <= 'z' ) {
				int z = Character.valueOf(x) + (sectorId % 26);
				if((z - 97) >= 26) {
					z = z - 26;
				}
				
				x = Character.toChars(z)[0];
			}
			
			array[i] = x;
			i++;
		}
		
		String out = new String(array);
		
		if(out.startsWith("north")){
			System.out.println(out);
		}
		
		
	}
	
	public static int step1(String[] data, String checksum, int sectorId) {
		String dataString = data[0];			
		for(int i = 1; i < data.length - 1; i++) {
			dataString += data[i]; 
		}
		
		char[] chars = dataString.toCharArray();
		char[] checkChar = checksum.toCharArray();
		int[] numOfApperances = new int[5];
		int[] histogram = new int[26];
		
		for(int j = 0; j < chars.length; j++){
			histogram[Integer.valueOf(chars[j]) - 97]++;
		}
		
		for(int i = 0; i < checkChar.length; i++) {
			for(int j = 0; j < chars.length; j++) {
				if(chars[j] == checkChar[i]){
					numOfApperances[i]++;
				}
			}
		}
					
		int histMax = findMax(histogram);
		
		if(histMax > numOfApperances[0]) {
			return 0;
		}
		
		
		if(isSorted(numOfApperances, checkChar)) {
			return sectorId;
		}
		
		return 0;
	}

		
	public static boolean isSorted(int[] a, char[] chars) {
			for (int i = 0; i < a.length - 1; i++) {
		        if (a[i] < a[i + 1]) {
		            return false; 
		        }
		        
		        if((a[i] == a[i + 1]) && (chars[i] > chars[i + 1])) {
		        	return false;
		        }
			}
			
			return true;
	}
	
	public static int findMax(int[] num) {
		int max = 0;
		for (int i = 0; i < num.length; i++) {
			if (num[i] > max) {
				max = num[i];
			}
		}
		
		return max;
	}
		
}
