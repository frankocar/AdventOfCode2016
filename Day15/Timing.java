import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Timing {

	public static void main(String[] args) {
		List<String> input;
		Map<Integer, Disc> discs = new TreeMap<>(); 
		int noOfDiscs = 0;
		int time = 0;
		boolean step2 = true;
		boolean isPassable = false;
		
		try {
			input = Files.readAllLines(Paths.get("day15_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		
		for(String line : input){
			int discNo = Integer.parseInt(line.split("#")[1].substring(0, 2).trim());
			int posNo = Integer.parseInt(line.split(" ")[3]);
			int startingPos = Integer.parseInt(line.split(" ")[11].replace(".", " ").trim());
			
			discs.put(discNo, new Disc(discNo, posNo, startingPos));
			noOfDiscs++;
		}
		
		if(step2) {
			discs.put(++noOfDiscs, new Disc(noOfDiscs, 11, 0));
		}
		
		int[] positions = new int[noOfDiscs];
		
		while(!isPassable){
			for(Map.Entry<Integer, Disc> disc : discs.entrySet()) {
				positions[disc.getKey() - 1] = disc.getValue().posAtTime(time + disc.getValue().discNo);
			}
			Arrays.sort(positions);
			if(positions [0] == positions[noOfDiscs - 1]){
				isPassable = true;
			} else {
				time++;
			}
			
		}
		
		System.out.println(time);
		
	}
	
	public static class Disc{
		public int discNo;
		public int posNo;
		public int strartingPos;
		
		public Disc(int discNo, int posNo, int startingPos) {
			this.discNo = discNo;
			this.posNo = posNo;
			this.strartingPos = startingPos;
		}
		
		public int posAtTime(int time){
			return (this.strartingPos + time) % posNo;
		}

		@Override
		public String toString() {
			return "Disc [discNo=" + discNo + ", posNo=" + posNo + ", strartingPos=" + strartingPos + "]";
		}
	}
	
}
