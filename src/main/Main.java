package main;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import gamelib.ClickBuffer;
import gamelib.Gait;
import gamelib.KeyBuffer;
import gamelib.Motion4D;
import gamelib.ScreenText;
import gamelib.Sprite;

public class Main implements GLEventListener {
	
	private float angle, translatex;
	private Texture texture;
	private float textureTop, textureBottom, textureLeft, textureRight;
	private TextRenderer tr;
	private String msg = "Hello world";
	private Sprite man;
	private ScreenText st;
	private KeyBuffer kb;
	private ClickBuffer cb;
	private Motion4D m4d;
	
	private Gait gaitRight, gaitLeft, gaitTop, gaitBottom;
	
	private void start() {
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		GLCanvas canvas = new GLCanvas(capabilities);
		canvas.addGLEventListener(this);
		canvas.setSize(1024, 768);
		
		canvas.addMouseListener(cb);
		canvas.addKeyListener(kb);
		
		final JFrame frame = new JFrame("Basic frame");
		frame.getContentPane().add(canvas);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(frame.getContentPane().getPreferredSize());
		
		frame.setVisible(true);
		
		final FPSAnimator animator = new FPSAnimator(canvas, 60, true);
		animator.start();
	}
	
	private Main() {
		kb = new KeyBuffer();
		cb = new ClickBuffer();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Main().start();
			}
			
		});
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
//		gaitRight.getSprite().draw(gl, translatex, 0.0f, 0.0f, 0.2f);
		Gait.counter++;
		
		KeyEvent ke = kb.get();
		Sprite sp;
		sp = m4d.getSprite(ke);
		if(sp != null) {
			sp.draw(gl, 0, 0, 0, 0.2f);
		}
		st.setText(ke == null ? null : ke.paramString());
		
		st.draw(drawable, 0, 0);
		
//		MouseEvent me = cb.get();
//		st.setText(me == null ? null : me.paramString());
//		st.draw(drawable, 0, 20);
		
		translatex += 0.01f;
		if(translatex > 1.0f) {
			translatex = -1.0f;
		}
		angle += 0.2f;
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
		angle = 0.0f;
		translatex = 0.0f;
//		System.out.println("Init");
		
		tr = new TextRenderer(new Font("SansSerif", Font.PLAIN, 14));
		Rectangle2D bounds = tr.getBounds(msg + "0000.0");
		
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		
		man = new Sprite(Main.class.getClassLoader().getResource("main/sprite.gif"), false);
		
		st = new ScreenText("SansSerif", Font.PLAIN, 20, "Hello world");
		
		
		gaitTop = new Gait(10);
		gaitTop.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-top-0.gif"), false));
		gaitTop.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-top-1.gif"), false));
		
		gaitBottom = new Gait(10);
		gaitBottom.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-bottom-0.gif"), false));
		gaitBottom.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-bottom-1.gif"), false));
		
		gaitLeft = new Gait(10);
		gaitLeft.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-left-0.gif"), false));
		gaitLeft.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-left-1.gif"), false));
		
		gaitRight = new Gait(10);
		gaitRight.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-right-0.gif"), false));
		gaitRight.add(new Sprite(Main.class.getClassLoader().getResource("main/gait-right-1.gif"), false));
		
		m4d = new Motion4D(gaitTop, gaitRight, gaitBottom, gaitLeft);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		
	}

}
