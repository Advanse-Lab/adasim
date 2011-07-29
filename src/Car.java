import java.util.Random;

public class Car {
	
	private int startx;
	private int starty;
	private int endx;
	private int endy;
	private int currentx;
	private int currenty;
	private int carNum;
	private int carConflict;
	private String path;
	private boolean finish;
	
	public Car(String positions, int num) {
		startx = Integer.parseInt(positions.substring(0, 2));
		starty = Integer.parseInt(positions.substring(3, 5));
		endx = Integer.parseInt(positions.substring(6, 8));
		endy = Integer.parseInt(positions.substring(9, 11));
		currentx = startx;
		currenty = starty;
		carNum = num;
		carConflict = -1;
		path = "(" + startx + "," + starty + ")";
		finish = false;
	}
	
	public void moveOneStep() {
		if(!finish) {
			if(currentx != endx && currenty != endy) {
				Random generator = new Random();
				int rand = generator.nextInt(2);
				if(rand == 0) {
					if(endx > startx) {
						currentx++;
					} else {
						currentx--;
					}
				} else {
					if(endy > starty) {
						currenty++;
					} else {
						currenty--;
					}
				}
			} else if (currentx == endx) {
				if(endy > starty) {
					currenty++;
				} else {
					currenty--;
				}
			} else if (currenty == endy) {
				if(endx > startx) {
					currentx++;
				} else {
					currentx--;
				}
			}
			path = path.concat(", (" + currentx + "," + currenty + ")");
			setFinish();
			if(finish) {
				path = path.concat(", Finished");	
			}
		}
	}
	
	public int getCurrentX() {
		return currentx;
	}
	
	public int getCurrentY() {
		return currenty;
	}
	
	public int getCarNumber() {
		return carNum;
	}
	
	public void setConflict(int carN) {
		carConflict = carN;
	}
	
	public int getConflict() {
		return carConflict;
	}
	
	private void setFinish() {
		finish = currentx == endx && currenty == endy;
	}
	
	public boolean checkFinish() {
		return finish;
	}
	
	public String toString() {
		return "Car " + carNum + ": " + path;
	}
}
