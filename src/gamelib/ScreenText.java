package gamelib;

import java.awt.Font;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.awt.TextRenderer;

public class ScreenText {
	
	private TextRenderer tr;
	private String message;
	
	public ScreenText(String font, int style, int size, String m) {
		tr = new TextRenderer(new Font(font, style, size));
		message = m;
	}
	
	public void draw(GLAutoDrawable drawable, int x, int y) {
		int width = drawable.getSurfaceWidth(),
				height = drawable.getSurfaceHeight();
		
		tr.beginRendering(width, height);
		tr.draw(message, x, y);
		tr.endRendering();
	}
	
	public void setText(String m) {
		message = m == null ? message : m;
	}
	
	public String getText() {
		return message;
	}

}
