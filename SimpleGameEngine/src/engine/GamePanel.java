package engine;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import entity.*;
import object.*;
public class GamePanel extends JPanel implements Runnable,Serializable{
	//debug
	boolean debug;
	//System
	private static final int FPS=60;
	private  Thread gameThread;
	private  KeyHandler keyH;
	private  MouseHandler mouseH;
	private  GameUI ui;
	private  GameFrame frame;
	//Screen States
	public static final int PLAY_STATE=0;
	public static final int EDIT_STATE=1;
	public static final int TOOL_STATE=2;
	private int currentState=TOOL_STATE;
	//Screen Settings
	private static final int TILE_SIZE=48;
	private int maxScreenRow=15;
	private int maxScreenCol=15;
	private int screenWidth=TILE_SIZE*maxScreenCol;
	private int screenHeight=TILE_SIZE*maxScreenRow;
	//Map
	private  TileManager tileM;
	private boolean showGrid;
	//GameContent
	private  ArrayList<Entity>entityList;
	private  ArrayList<GameObject>objectList;
	private  Player player;
	//Loaded
	private boolean loadedTiles;
	private boolean loadedObjects;
	private boolean loadedPlayer;
	private boolean loadedEntities;
//======================Constructor==========================	
	public GamePanel(GameFrame frame) {
		setPreferredSize(new Dimension(screenWidth,screenHeight));
		setDoubleBuffered(true);
		setFocusable(true);
		ui=new GameUI(this);
		keyH=new KeyHandler(this,ui);
		addKeyListener(keyH);
		mouseH=new MouseHandler(this,ui);
		addMouseListener(mouseH);
		this.frame=frame;
		entityList=new ArrayList<Entity>();
		gameThread=new Thread(this);
		gameThread.start();
	}
//==========================test=============
	public BufferedImage getEntityImage(int num) {
		return entityList.get(num).getEntityImage();
	}
//===============================G&S===================	
	public boolean isLoaded(int choice) {
		switch(choice) {
		case 1:return loadedTiles;
		case 3:return loadedObjects;
		case 5:return loadedEntities;
		default:return loadedPlayer;
		}
	}
	public BufferedImage getTileImage(int num) {
		return tileM.getTileImage(num);
	}
	public int getMaxTiles() {
		return tileM.getMaxTiles();
	}
	public int getCurrentState() {
		return currentState;
	}
	public void setState(int state) {
		currentState=state;
	}
	public Player getPlayer() {
		return player;
	}
	public int getScreenWidth() {
		return screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public int getTileSize() {
		return TILE_SIZE;
	}
	public int getMiddleScreenX() {
		return (screenWidth-TILE_SIZE)/2;
	}
	public int getMiddleScreenY() {
		return (screenHeight-TILE_SIZE)/2;
	}
	public void toggleGrid() {
		if(showGrid)
			showGrid=false;
		else
			showGrid=true;
	}
//============================paint====================	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;	
		g2d.setColor(Color.pink);
		g2d.fillRect(0,0, screenWidth, screenHeight);
		//always draw
		if(showGrid) {
			g2d.setColor(Color.black);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
			for(int row=0;row<=maxScreenRow;row++) {
				for(int col=0;col<=maxScreenCol;col++)
					g2d.drawLine(col*TILE_SIZE,0,col*TILE_SIZE,screenHeight);
				g2d.drawLine(0,row*TILE_SIZE,screenWidth,row*TILE_SIZE);
			}
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

		}
		if(tileM!=null)
			tileM.draw(g2d);	
		if(player!=null)
			player.draw(g2d);
		if(objectList!=null)
			for(int i=0;i<objectList.size();i++)
				objectList.get(i).draw(g2d);
		if(entityList!=null)
			for(int i=0;i<entityList.size();i++)
				entityList.get(i).draw(g2d);
		if(currentState==TOOL_STATE)
			ui.draw(g2d);
		
		
		g2d.dispose();
	}
//======================Methods=========================	
	public void changeScreenSize(int colNum,int rowNum) {
		if(colNum<=25&&colNum>=11&&rowNum>=13&&rowNum<=20) {
		//Changing screen settings
		maxScreenCol=colNum;
		maxScreenRow=rowNum;
		screenWidth=TILE_SIZE*maxScreenCol;
		screenHeight=TILE_SIZE*maxScreenRow;
		//modify in all areas
		ui.setUp();
		setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setSize(getPreferredSize());
		frame.pack();
		frame.setLocationRelativeTo(null);
		}
	}
	public void spawnNewTile(int id,int col,int row) {
		tileM.spawnNewTile(id,col,row);
	}
	public void spawnNewEntity(int id,int col,int row) {
		entityList.add(new Entity(id,col,row,this));
	}
//==============================Load===================
	public void loadTiles() {
		tileM=new TileManager(this);
		tileM.loadTiles();
		loadedTiles=true;
	}
	public void loadTiles2() {//// what is this????
		tileM.loadTiles();
	}
	public void loadPlayer() {
		player=new Player(this,keyH);
		loadedPlayer=true;
	}
	public void loadEntity() {
		//user inputs entity number
		int userEntityNumber=1;
		Entity.loadEntities(userEntityNumber);
		loadedEntities=true;
	}
	public void loadObject() {
		//user input object number
		loadedObjects=true;
	
	}
	
//========================GAME FPS THING AND UPDATE============================	
	public void update() {
		if(currentState==PLAY_STATE) {
		if(player!=null)
			player.move();
		if(entityList!=null) {
			for(int i=0;i<entityList.size();i++) {
				entityList.get(i).move();
			}
		}
		
		
		
		}
	}
	@Override
	public void run() {
		long lastTime=System.nanoTime();
		long currentTime;
		double drawInterval=1000000000/FPS;
		double delta=0;
		int drawCount=0;
		double lastPaint=0;
		while(gameThread!=null) {
			currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			lastPaint+=currentTime-lastTime;
			lastTime=currentTime;
			if(delta>=1) {
				drawCount++;
				long t1=System.nanoTime();
				update();
				repaint();
				long t2=System.nanoTime();
				if(debug)
					System.out.println(t2-t1);
				delta--;
			}
			if(lastPaint>=1000000000) {
//				System.out.println("FPS :"+drawCount);
				drawCount=0;
				lastPaint=0;
			}
			
		}
				
	}

}

