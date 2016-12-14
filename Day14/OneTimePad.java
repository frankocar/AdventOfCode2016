import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class OneTimePad {

//	public static final String input = "abc";
	public static Map<Integer, String> generated = new HashMap<>();
	
	
	
	public static void main(String[] args) {

		String input = "zpqevtbw";
		long start = System.currentTimeMillis();
		int index1 = solve(input, 1);
		long end = System.currentTimeMillis();
		System.out.println("Step 1 solution: " + index1);
		System.out.println("Time to run: " + (end-start)/1000.f + "s");
		
		start = System.currentTimeMillis();
		int index2 = solve(input, 2);
		end = System.currentTimeMillis();
		System.out.println("Step 2 solution: " + index2);
		System.out.println("Time to run: " + (end-start)/1000.f + "s");		
	}



	private static int solve(String input, int step) {
		
		int keys = 0;
		int index = -1;
		generated.clear();
		
		while(keys < 64) {

			index++;
			
			String current;
			if(generated.containsKey(index)) {
				current = generated.get(index);
			} else {
				current = saltedHash(input, index, step);
				generated.put(index, current);
			}
			
			for(int i = 0; i < current.length() - 2; i++) {
				if(current.charAt(i) == current.charAt(i+1) && current.charAt(i+1)  == current.charAt(i+2)){
					if (checkNextThousand(input, index, current.charAt(i), step)){
						keys++;
					}
					break;
				}
			}
		}
		
		return index;
				
	}



	private static boolean checkNextThousand(String input, int index, char charAt, int step) {
		
		for(int i = index + 1; i <= index + 1000; i++) {
			String current;
			if(generated.containsKey(i)) {
				current = generated.get(i);
			} else {
				current = saltedHash(input, i, step);
				generated.put(i, current);
			}
			generated.put(i, current);
			String sequence = String.valueOf(charAt) + charAt + charAt + charAt + charAt;
			if(current.contains(sequence)){
				return true;
			}
		}
		
		return false;
		
	}



	private static String saltedHash(String input, long salt, int step){
		String toBeHashed;
		
		toBeHashed = (salt >= 0) ? input + salt : input;
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
        md.update(toBeHashed.getBytes());

        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String hashed = sb.toString();
        
        if(step == 2) {
        	for (int i = 0; i < 2016; i++) {
        		hashed = saltedHash(hashed, -1, 1);
        	}
        }
        
        return hashed;
	}
	
	

}
