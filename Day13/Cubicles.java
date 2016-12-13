import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Cubicles {

	private static final int INPUT = 1364; // 1364
	private static int locations = 0;
	private static Queue<int[]> queue = new LinkedList<>();
	private static Set<Point> visited = new HashSet<>();

	public static void main(String[] args) {

		int step1 = bfsSearch(31, 39, 1);
		System.out.println("Step 1: " + step1);

		int step2 = bfsSearch(31, 39, 2);
		System.out.println("Step 2: " + step2);

	}

	public static int bfsSearch(int goalX, int goalY, int step) {
		int[] start = { 1, 1, 0 }; // starting coordinates and number of steps, 0 at the beginning
		queue.add(start);

		while (queue.peek() != null) {
			int[] array = queue.remove();
			int result;
			
			result = check(array[0] - 1, array[1], array[2], step, goalX, goalY);
			if(result > 0 && step == 1) return result;
			result = check(array[0], array[1] - 1, array[2], step, goalX, goalY);
			if(result > 0 && step == 1) return result;
			result = check(array[0], array[1] + 1, array[2], step, goalX, goalY);
			if(result > 0 && step == 1) return result;
			result = check(array[0] + 1, array[1], array[2], step, goalX, goalY);
			if(result > 0 && step == 1) return result;
			
		}

		return step == 2 ? locations : -1;
	}
	
	public static int check(int x, int y, int s, int part, int goalX, int goalY) {
		if (x >= 0 && !isWall(x, y)) {

			Point point = new Point(x, y);

			if (!visited.contains(point)) {
				if (x == goalX && y == goalY) {
					if (part == 1) {
						return s + 1;
					}
				} else {
					int[] temp = { x, y, s + 1 };
					queue.add(temp);
					visited.add(point);
				}

				if (s < 50) {
					locations++;
				}
			}
			
		}
		return -1;
	}

	public static boolean isWall(int x, int y) {
		int value = (x * x) + (3 * x) + (2 * x * y) + y + (y * y) + INPUT;
		int parity = Integer.bitCount(value);
		return parity % 2 == 1;
	}
	
}
