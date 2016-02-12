package gamelib;

import java.util.LinkedList;
import java.util.ListIterator;

public class Gait {
	
	private LinkedList<Sprite> frames;
	private int frameSkip;
	public static int counter = 0;
	private Sprite currentSprite;
	private ListIterator<Sprite> li;
	private Sprite stationary = null;
	
	public Gait(int frameSkip) {
		frames = new LinkedList<Sprite>();
		this.frameSkip = frameSkip;
	}
	
	public void add(Sprite s) {
		frames.add(s);
		li = frames.listIterator();
		if(stationary == null) {
			stationary = s;
		}
	}
	
	private void reset() {
		li = frames.listIterator();
	}
	
	public Sprite getStationary() {
		reset();
		return stationary;
	}
	
	public Sprite getSprite() {
		if(counter % frameSkip == 0) {
			if(li.hasNext()) {
				currentSprite = li.next();
			}
			else {
				reset();
			}
		}
		return currentSprite;
	}

}
