package gamelib;

import java.awt.event.KeyEvent;

import com.jogamp.opengl.GL2;

public class Character {
	
	private Motion4D m4d;
	private static float defaultSpeed = 0.01f;
	private float posX, posY, speed;
	private int currentCode = 0;
//	private int nullCounter, counterValue = 5;
	
	public Character(Motion4D m4d, float x, float y) {
		this(m4d, defaultSpeed, x, y);
	}
	
	public Character(Motion4D m4d, float speed, float x, float y) {
		this.m4d = m4d;
		this.speed = speed;
		posX = x;
		posY = y;
	}
	
//	private void resetCounter() {
//		nullCounter = counterValue;
//	}
	
	public Dimension2f move(GL2 gl, KeyEvent ke, float size) {
		Sprite sp = m4d.getSprite(ke);
		if(ke != null && sp != null) {
			int code = ke.getKeyCode();
			if(code == KeyEvent.VK_LEFT) {
				if(currentCode == code) {
					posX -= speed;
					sp.draw(gl, posX, posY, 0, size);
				}
				else {
					currentCode = code;
					sp.draw(gl, posX, posY, 0, size);
				}
			}
			else if(code == KeyEvent.VK_RIGHT) {
				if(currentCode == code) {
					posX += speed;
					sp.draw(gl, posX, posY, 0, size);
				}
				else {
					currentCode = code;
					sp.draw(gl, posX, posY, 0, size);
				}
			}
			else if(code == KeyEvent.VK_UP) {
				if(currentCode == code) {
					posY += speed;
					sp.draw(gl, posX, posY, 0, size);
				}
				else {
					currentCode = code;
					sp.draw(gl, posX, posY, 0, size);
				}
			}
			else if(code == KeyEvent.VK_DOWN) {
				if(currentCode == code) {
					posY -= speed;
					sp.draw(gl, posX, posY, 0, size);
				}
				else {
					currentCode = code;
					sp.draw(gl, posX, posY, 0, size);
				}
			}
		}
		else if(sp != null) {
//			nullCounter--;
			sp.draw(gl, posX, posY, 0, size);
		}
		return new Dimension2f(posX, posY);
	}
	
}
