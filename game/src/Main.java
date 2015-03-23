

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Box;
import logic.Box;
import logic.Game;
import logic.SkipBox;

public class Main extends Canvas implements Runnable{


	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	private static final double SCALE = 1;
	
	private static Game game;
	
	private static Point mouse;

	private static String monitor = "a";
	
	private boolean running;
	private Thread thread;
	private static boolean exit = false;

	

	public static Screen screen;
	private BufferedImage img;
	public static int[] pixels;


	
	private Main(){
		mouse = new Point(); 
		Dimension size = new Dimension((int)(WIDTH*SCALE),(int) (HEIGHT*SCALE));
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		screen = new Screen(WIDTH,HEIGHT);
		
		img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		setPixels(((DataBufferInt)img.getRaster().getDataBuffer()).getData());
		
		
		addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) {
	        	  int val = Art.click.pixels[mouse.x+mouse.y*1024];
	        	  String value = Art.getColour(val);
	        	  Box boxPressed = game.getBoard().getBoxes().get(value);
	        	  game.pressed(boxPressed);
	          }
	        }); 
	}
	private synchronized void start(){
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public void run(){
		
		while (running && exit ==false) {
			
				mouse.x=MouseInfo.getPointerInfo().getLocation().x-this.getLocationOnScreen().x;
				mouse.y=MouseInfo.getPointerInfo().getLocation().y-this.getLocationOnScreen().y;
				
				render(mouse);
			
		
		}
		synchronized (monitor) {
			monitor.notify();
		}
	}
	private void render(Point mouse) {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

			screen.render(mouse,game);
			for (int i = 0; i < WIDTH * HEIGHT; i++) {
				getPixels()[i] = screen.pixels[i];
			}
			Graphics g = bs.getDrawGraphics();
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(img, 0, 0, (int) (WIDTH * SCALE),
					(int) (HEIGHT * SCALE), null);
			g.dispose();
			bs.show();
		

	}

	public static void main (String[] args) {
		Map<String,Box> list = new HashMap<String, Box>();
		Box b1 = new SkipBox("404040");
		Box b2 = new SkipBox("FF0000");
		Box b3 = new SkipBox("FF6A00");
		Box b4 = new SkipBox("FFD800");
		Box b5 = new SkipBox("B6FF00");
		
		Box b6 = new SkipBox("4CFF00");
		Box b7 = new SkipBox("0094FF");
		Box b8 = new SkipBox("B200FF");
		
		Box b9 = new SkipBox("00FF21");
		Box b10 = new SkipBox("00FF90");
		Box b11 = new SkipBox("00FFFF");
		Box b12 = new SkipBox("0026FF");
		Box b13 = new SkipBox("4800FF");
		
		Box b14 = new SkipBox("FF00DC");
		Box b15 = new SkipBox("7F6A00");
		Box b16 = new SkipBox("007F0E");
		
		Box b17 = new SkipBox("FF006E");
		Box b18 = new SkipBox("7F0000");
		Box b19 = new SkipBox("7F3300");
		Box b20 = new SkipBox("5B7F00");
		Box b21 = new SkipBox("267F00");
		
		b1.setAdjacents(b2,b6);
		b2.setAdjacents(b1,b3);
		b3.setAdjacents(b2,b4,b7);
		b4.setAdjacents(b3,b5);
		b5.setAdjacents(b4,b8);
		
		b6.setAdjacents(b1,b9);
		b7.setAdjacents(b3,b11);
		b8.setAdjacents(b5,b13);
		
		b9.setAdjacents(b6,b14,b10);
		b10.setAdjacents(b9,b11);
		b11.setAdjacents(b10,b12,b7,b15);
		b12.setAdjacents(b11,b13);
		b13.setAdjacents(b12,b8,b16);
		
		b14.setAdjacents(b9,b17);
		b15.setAdjacents(b11,b19);
		b16.setAdjacents(b13,b21);
		
		b17.setAdjacents(b14,b18);
		b18.setAdjacents(b17,b19);
		b19.setAdjacents(b18,b20,b15);
		b20.setAdjacents(b19,b21);
		b21.setAdjacents(b20,b16);

		list.put(b1.getColour(),b1);
		list.put(b2.getColour(),b2);
		list.put(b3.getColour(),b3);
		list.put(b4.getColour(),b4);
		list.put(b5.getColour(),b5);
		list.put(b6.getColour(),b6);
		list.put(b7.getColour(),b7);
		list.put(b8.getColour(),b8);
		list.put(b9.getColour(),b9);
		list.put(b10.getColour(),b10);
		list.put(b11.getColour(),b11);
		list.put(b12.getColour(),b12);
		list.put(b13.getColour(),b13);
		list.put(b14.getColour(),b14);
		list.put(b15.getColour(),b15);
		list.put(b16.getColour(),b16);
		list.put(b17.getColour(),b17);
		list.put(b18.getColour(),b18);
		list.put(b19.getColour(),b19);
		list.put(b20.getColour(),b20);
		list.put(b21.getColour(),b21);
		
		callHereStart(list);
	}

	private static int[] getPixels() {
		return pixels;
	}

	private void setPixels(int[] pixels) {
		Main.pixels = pixels;
	}
	
	public static Game callHereStart(Map<String, Box> list){
		game = new Game(list);
		
		Main start = new Main();
		
		JFrame frame = new JFrame("Reserva");
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(start, BorderLayout.CENTER);
		
		frame.setContentPane(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
		start.start();
		synchronized (monitor) {
			try {  
				monitor.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		frame.dispose();
		
		
		
		return game;
	}
	
}
