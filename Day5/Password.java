import java.io.UnsupportedEncodingException;
import java.security.*;

public class Password {

	public static int digit = 0;
	public static boolean[] first = {true, true, true, true, true, true, true, true};
	
	public static void main(String[] args) {
		String input = "reyedfim";
		
		String passwordStep1;
		String passwordStep2;
		
		try {
			passwordStep1 = getPassword(input, 1);
			digit = 0;
			passwordStep2 = getPassword(input, 2);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			System.out.println("Something happened: " + e.getMessage());
			return;
		}
		
		System.out.println("Step1: " + passwordStep1);		
		System.out.println("Step2: " + passwordStep2);
		
		

	}

	private static String getPassword(String input, int step) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		char[] password = new char[8];
		
		long salt = 0;
		
		do {
			
			String toBeHashed = input + salt;
			
			MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(toBeHashed.getBytes());

	        byte byteData[] = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        String hashed = sb.toString();
			
	        
	        if(hashed.startsWith("00000")){
	        	if(step == 1) step1(password, hashed);
	        	if(step == 2) step2(password, hashed);
	        }
	        
	        if(salt % 1000000 == 0) {
	        	System.out.println("Progress: " + (double)digit/8 * 100 + "%");
	        }
			
			salt++;
			
		} while (digit < 8);
		
		return new String(password);
	}

	private static void step2(char[] password, String hashed) {
		int location;
		
		try {
			location = Character.getNumericValue(hashed.charAt(5));
		} catch (NumberFormatException e) {
			return;
		}
		
		if(location < 0 || location >= 8) {
			return;
		}
		
		if(first[location]) {
			password[location] = hashed.charAt(6);
			digit++;
			first[location] = false;
		}
		
		
	}

	private static void step1(char[] password, String hashed) {
		password[digit] = hashed.charAt(5);
		digit++;
		
	}
	
	

}
