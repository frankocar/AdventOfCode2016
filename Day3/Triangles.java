import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangles {

	public static void main(String[] args) {
		List<String> input;
		List<Integer> column1 = new ArrayList<>();
		List<Integer> column2 = new ArrayList<>();
		List<Integer> column3 = new ArrayList<>();

		int numOfTrianglesPt1 = 0;
		int numOfTrianglesPt2 = 0;

		try {
			input = Files.readAllLines(Paths.get("day3_data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Instructions are nowhere to be found");
			return;
		}

		for (String line : input) {
			line = line.trim();
			String[] numbers = line.split("\\s+");

			int num1 = Integer.parseInt(numbers[0]);
			int num2 = Integer.parseInt(numbers[1]);
			int num3 = Integer.parseInt(numbers[2]);
			
			int[] nums = {num1, num2, num3};

			column1.add(num1);
			column2.add(num2);
			column3.add(num3);


			if (isTriangle(nums)) {
				numOfTrianglesPt1++;
			}

		}
		


		//Loop for step2
		for (int i = 0; i < column1.size(); i += 3) {
						
			int[] nums1 = {column1.get(i), column1.get(i + 1), column1.get(i + 2)};
			int[] nums2 = {column2.get(i), column2.get(i + 1), column2.get(i + 2)};
			int[] nums3 = {column3.get(i), column3.get(i + 1), column3.get(i + 2)};
			
			if (isTriangle(nums1)) {
				numOfTrianglesPt2++;
			}

			if (isTriangle(nums2)) {
				numOfTrianglesPt2++;
			}

			if (isTriangle(nums3)) {
				numOfTrianglesPt2++;
			}
		}
		
		System.out.println("Total number of triangles for step 1: " + numOfTrianglesPt1);
		System.out.println("Total number of triangles for step 2: " + numOfTrianglesPt2);

	}

	public static boolean isTriangle(int[] nums) {

		Arrays.sort(nums);
		
		if (nums[0] + nums[1] > nums[2]) {
			return true;
		}

		return false;

	}

}
