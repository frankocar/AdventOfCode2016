public class DragonChecksum {

	public static void main(String[] args) {
		String input = "01110110101001000";
		int driveLength1 = 272;
		int driveLength2 = 35651584;

		String data1 = driveData(input, driveLength1);
		String checksum1 = driveChecksum(data1);
		System.out.println("First checksum: " + checksum1);

		String data2 = driveData(input, driveLength2);
		String checksum2 = driveChecksum(data2);
		System.out.println("Second checksum: " + checksum2);

	}

	private static String driveChecksum(String input) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < input.length(); i += 2) {
			sb.append(input.charAt(i) == input.charAt(i + 1) ? '1' : '0');
		}

		String checksum = sb.toString();

		if (checksum.length() % 2 == 0) {
			checksum = driveChecksum(checksum);
		}

		return checksum;

	}

	public static String driveData(String a, int driveLength) {

		String b = new StringBuffer(a).reverse().toString();

		b = b.replace('0', 'x');
		b = b.replace('1', '0');
		b = b.replace('x', '1');

		String toReturn = a + '0' + b;

		if (toReturn.length() < driveLength) {
			toReturn = driveData(toReturn, driveLength);
		}

		toReturn = toReturn.substring(0, driveLength);

		return toReturn;

	}

}
