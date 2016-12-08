import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Display {
	
	static int r = 6; //rows
	static int c = 50; //columns
	static char[][] display = new char[r][c];
	
	public static void main(String[] args) {
		List<String> input/* = new ArrayList<>()*/;

		int litPixels = 0;
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				display[i][j] = ' ';
			}
		}
		
		try {
			input = Files.readAllLines(Paths.get("day8_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		for (String line : input) {
			if (line.startsWith("rect")) {
				rect(line.substring(5, line.length()));
			}
			
			if(line.startsWith("rotate row")){
				row(line.substring(11, line.length()));
			}
			
			if(line.startsWith("rotate column")){
				column(line.substring(14, line.length()));
			}			
		}
		
		
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(display[i][j]);
				if(display[i][j] == 'x') {
					litPixels++;
				}
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Total pixels lit: " + litPixels);
				
	}

	private static void column(String data) {
		int column = Integer.parseInt(data.split(" by ")[0].substring(2, data.split(" by ")[0].length()));
		int by = Integer.parseInt(data.split(" by ")[1]);		
		
		int[] newColumn = new int[r];
		
		int goal;
		
		for(int i = 0; i < r; i++) {
			if(display[i][column] == 'x') {
				display[i][column] = ' ';
				goal = (i + (by % r) >= r) ? (i + (by % r) - r) : (i + (by % r));
				newColumn[goal] = 'x';
			}
		}
		
		for(int i = 0; i < r; i++) {
			if (newColumn[i] == 'x'){
				display[i][column] = 'x';
			}
		}
		
	}

	private static void row(String data) {
		
		int row = Integer.parseInt(data.split(" by ")[0].substring(2, data.split(" by ")[0].length()));
		int by = Integer.parseInt(data.split(" by ")[1]);
		int[] newRow = new int[c];
		
		int goal;
		
		for(int i = 0; i < c; i++) {
			if(display[row][i] == 'x') {
				display[row][i] = ' ';
				goal = (i + (by % c) >= c) ? (i + (by % c) - c) : (i + (by % c));
				newRow[goal] = 'x';
			}
		}
		
		for(int i = 0; i < c; i++) {
			if (newRow[i] == 'x'){
				display[row][i] = 'x';
			}
		}
		
		
	}

	private static void rect(String data) {
		
		int width = Integer.parseInt(data.split("x")[0]);
		int height = Integer.parseInt(data.split("x")[1]);		
		
		if (width > c) {
			width = c;
		}
		
		if (height > r) {
			height = r;
		}
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				display[j][i] = 'x';
			}
		}
	}

}
