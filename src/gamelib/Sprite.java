package gamelib;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureIO;

public class Sprite {
	
	private Texture texture;
	private float aspectRatio;
	private float textureTop, textureBottom, textureLeft, textureRight;
	
	public Sprite(File file, boolean enableMipMap) {
		try {
			texture = TextureIO.newTexture(file, enableMipMap);
		} catch (GLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		aspectRatio = texture.getAspectRatio();
		
		TextureCoords textureCoords = texture.getImageTexCoords();
		textureTop = textureCoords.top();
		textureBottom = textureCoords.bottom();
		textureLeft = textureCoords.left();
		textureRight = textureCoords.right();
		
	}
	
	public float getAspectRatio() {
		return aspectRatio;
	}
	
	public Sprite(URL path, boolean enableMipMap) {
		this(new File(path.toString().split(":/", 2)[1]), enableMipMap);
	}
	
	public void draw(GL2 gl, float x, float y, float z, float size) {
		float aspectXsize = aspectRatio * size;
//		gl.glTranslatef(x, y, z);
		
		texture.enable(gl);
		texture.bind(gl);
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(x, y + size, z);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(x + aspectXsize, y + size, z);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(x + aspectXsize, y, z);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(x, y, z);
		gl.glEnd();
		
		texture.disable(gl);
	}

}
