package gamelib;

import java.awt.event.KeyEvent;

public class Motion4D {
	
	private Gait current;
	private int nullCounter;
	private int counterValue = 5;
	private Gait top, right, bottom, left;
	
	public Motion4D(Gait top, Gait right, Gait bottom, Gait left) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		current = right;
		resetCounter();
	}
	
	private void resetCounter() {
		nullCounter = counterValue;
	}
	
	public Sprite getSprite(KeyEvent ke) {
		if(ke != null) {
			switch(ke.getKeyCode()) {
			case KeyEvent.VK_UP:
				current = top;
				break;
			case KeyEvent.VK_DOWN:
				current = bottom;
				break;
			case KeyEvent.VK_LEFT:
				current = left;
				break;
			case KeyEvent.VK_RIGHT:
				current = right;
				break;
			default:
				return current.getStationary();
			}
			resetCounter();
			return current.getSprite();
		}
		else {
			if(nullCounter-- <= 0) {
				return current.getStationary();
			}
			return current.getSprite();
		}
	}

}
