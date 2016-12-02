package com.adventofcode.day2;

public class NumKey {
	
	int currentKey;
	
	public int getCurrentKey() {
		return currentKey;
	}

	public NumKey() {
		super();
		this.currentKey = 5;
	}

	public void left() {
		if (this.currentKey != 1 && this.currentKey != 4 && this.currentKey != 7) {
			this.currentKey -= 1;
		}
	}
	
	public void right() {
		if (this.currentKey != 3 && this.currentKey != 6 && this.currentKey != 9) {
			this.currentKey += 1;
		}
	}
	
	public void up() {
		if (this.currentKey != 1 && this.currentKey != 2 && this.currentKey != 3) {
			this.currentKey -= 3;
		}
	}
	
	public void down() {
		if (this.currentKey != 7 && this.currentKey != 8 && this.currentKey != 9) {
			this.currentKey += 3;
		}
	}
	
	
}


