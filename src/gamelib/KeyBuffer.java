package gamelib;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

public class KeyBuffer implements KeyListener {
	
	private Queue<KeyEvent> buffer;
	
	public KeyBuffer() {
		buffer = new LinkedList<KeyEvent>();
	}
	
	public KeyEvent get() {
		return buffer.poll();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("keypressed");
		buffer.add(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("keyreleased");
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("keytyped");
	}

}
