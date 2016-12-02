import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Bathroom {
	
	public static void main(String[] args) {
		List<String> input;
		
		try {
			input = Files.readAllLines(Paths.get("day2_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		for (String line : input) {

//			Use this class for solving step 1:		
//			NumKey key = new NumKey();
			
			KeyPad key = new KeyPad();			

			for (char next : line.toCharArray()){
				
				if(next == 'U'){
					key.up();
				}
				
				if(next == 'R'){
					key.right();
				}
				
				if(next == 'L'){
					key.left();
				}
				
				if(next == 'D'){
					key.down();
				}
				
			}
			
//			When solving step 2, replace 10 with A, 11 with B, 12 with C, 13 with D
			System.out.print(key.getCurrentKey() + " ");
			
		}
		
	}
}



