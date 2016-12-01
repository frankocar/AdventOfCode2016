import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.awt.Point;

public class Distance {

	public enum Direction {
		NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

		int dx, dy;
		Direction left, right;

		static {
			NORTH.left = WEST;
			NORTH.right = EAST;
			EAST.left = NORTH;
			EAST.right = SOUTH;
			SOUTH.left = EAST;
			SOUTH.right = WEST;
			WEST.left = SOUTH;
			WEST.right = NORTH;
		}

		Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}

	}

	static int manhattanDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public static void main(String[] args) {
		//Next line is required only as a solution to the second step
		Set<Point> visited = new HashSet<>();
		int x = 0;
		int y = 0;
		//Next line is required only as a solution to the second step
		visited.add(new Point(x, y));

		Direction current = Direction.NORTH;
		String input;
		try {
			//This solution requires the input file to be named day1_data.txt and to be located in 
			//the same direstory from which the program is run.
			input = Files.readAllLines(Paths.get("day1_data.txt")).get(0);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}
		String[] whereTo = input.split(",");

		for (String each : whereTo) {
			String temp = each.trim();
			Boolean isRight = temp.startsWith("R");
			int dist = Integer.parseInt(temp.substring(1));

			if (isRight) {
				current = current.right;
			} else {
				current = current.left;
			}

			for (int i = 0; i < dist; i++) {
				x += current.dx;
				y += current.dy;
				//Following lines are a solution to the second step and are not required for the first step
				Point p = new Point(x, y);
				if (visited.contains(p)) {
					System.out.println("Visited for the second time: " + manhattanDist(0, 0, x, y));
				} else {
					visited.add(p);
				}
				//end of step two solution part
			}

		}

		System.out.println("Final distance: " + manhattanDist(0, 0, x, y));
	}

}
