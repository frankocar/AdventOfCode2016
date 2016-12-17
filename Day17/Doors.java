import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Doors {

	public static Queue<State> list = new LinkedList<>();
	public static Set<State> visited = new HashSet<>();

	public static void main(String[] args) {
		String input = "veumntbg";

		solve(input, false);
		solve(input, true);

	}

	public static void solve(String input, boolean step2) {
		String output = "ERROR";
		State state = new State(0, 0, input);
		list.add(state);

		while (!list.isEmpty()) {
			State temp = list.poll();
			if (!visited.contains(temp)) {
				boolean[] isLocked = locked(hash(temp.directions).substring(0, 4));

				Boolean out = false;
				for (int i = 0; i < 4; i++) {
					State x = new State(temp);
					if (!isLocked[i]) {
						x.move(i);
						if (x.x == 3 && x.y == 3) {
							output = x.directions;
							out = true;
							if (!step2) {
								break;
							} else {
								continue;
							}
						}
						list.add(x);
					}
				}
				if (out && !step2) {
					break;
				}
			}
		}

		System.out.println("Step " + (step2 ? 2 : 1));
		if (step2) {
			System.out.println("Number of steps: " + output.substring(input.length(), output.length()).length());
		} else {
			System.out.println("Path: " + output.substring(input.length(), output.length()));
		}
	}

	public static String hash(String input) {

		String toBeHashed = input;

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
		return hashed;
	}

	public static boolean[] locked(String hashed) {
		boolean[] isLocked = new boolean[4];
		for (int i = 0; i < 4; i++) {
			if (hashed.charAt(i) >= 'b' && hashed.charAt(i) <= 'f') {
				isLocked[i] = false;
			} else {
				isLocked[i] = true;
			}
		}

		return isLocked;
	}

	public static class State {
		int x;
		int y;
		String directions;

		public State(int x, int y, String directions) {
			this.x = x;
			this.y = y;
			this.directions = directions;
		}

		public State(State state) {
			this.x = state.x;
			this.y = state.y;
			this.directions = state.directions;
		}

		public void move(int direction) {
			visited.add(this);

			switch (direction) {
			case 0:
				if (y > 0) {
					y--;
					directions += 'U';
				}
				break;
			case 1:
				if (y < 3) {
					y++;
					directions += 'D';

				}
				break;
			case 2:
				if (x > 0) {
					x--;
					directions += 'L';

				}
				break;
			case 3:
				if (x < 3) {
					x++;
					directions += 'R';

				}
				break;
			default:
				break;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((directions == null) ? 0 : directions.hashCode());
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			State other = (State) obj;
			if (directions == null) {
				if (other.directions != null)
					return false;
			} else if (!directions.equals(other.directions))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}
}
