  package engine;
import java.awt.*;
import java.io.Serializable;

import javax.swing.*;
public class GameUI implements Serializable{
/// debug
	boolean enableText;
	JTextArea test;
///Game Panel	
	private  GamePanel gp;
	private int screenWidth;
	private int screenHeight;
	private int panelWidth;
////UI STATES	
	public static final int SCREEN_LOAD=0;
	public static final int SCREEN_TILES=1;
	public static final int SCREEN_OBJECTS=2;
	public static final int SCREEN_ENTITIES=3;
	private int currentScreen=SCREEN_LOAD;
////Boxes Shape
	private int x;
	private int y;
	private final int defX=0;
	private final int defY=10;
	private int boxWidth;
	private int boxHeight;
	private int Y_INCREASE;
	public  Font boxFont=new Font("Arial",Font.BOLD,20);
////Boxes Number	
	public final int maxLoad=10;
	public int maxObject;
	public int maxEntity=1;
	public int maxTiles=5;
////Choosing Arrow	
	public int arrowDefY=46;
	public int arrowY=arrowDefY;
	public int arrowX=160;
	public int arcCurve;
	public Font arrowFont=new Font("Arial",Font.BOLD,30);
///TILE
	private int selectedItem=-1;
	private boolean placeTiles;
//======================Constructor==========================	
	public GameUI(GamePanel gp) {
		this.gp=gp;
		setUp();
	}
//===============================G&S===================	
	public void onPlaceTiles() {
		placeTiles=true;
	}
	public void offPlaceTiles() {
		placeTiles=false;
	}
	public boolean getplaceTiles() {
		return placeTiles;
	}
	public int getCurrentScreen() {
		return currentScreen;
	}
	public void setLoadScreen() {
		currentScreen=SCREEN_LOAD;
	}
	public void resetPanelWidth() {
		panelWidth=screenWidth/2;
	}
	public void setSelectedItem(int num) {
		if(selectedItem==num)
			selectedItem=-1;
		else
		selectedItem=num;
	}
	public int getSelectedItem() {
		return selectedItem;
	}
//============================paint====================		
	public void draw(Graphics2D g2d) {
			g2d.setColor(Color.black);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.6f));
			g2d.fillRect(0, 0,panelWidth,screenHeight);
			g2d.setColor(Color.white);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
			g2d.setStroke(new BasicStroke(3));
			g2d.drawRect(0,0,panelWidth,screenHeight);
		//debug
			if(enableText) {
				test=new JTextArea();
				test.setBounds(300,300,300,300);
				test.setBackground(Color.black);
				System.out.println("Added");
				enableText=false;
				gp.add(test);}
		if(currentScreen==SCREEN_LOAD)
			drawLoad(g2d);
		else if(currentScreen==SCREEN_TILES)
			drawTiles(g2d);
		else if(currentScreen==SCREEN_OBJECTS)
			drawObject(g2d);
		else
			drawEntities(g2d);
	}

	private void drawEntities(Graphics2D g2d) {
		int x=defX+10; int y=defY;
		for(int i=0;i<maxEntity;i++) {
			if(i==selectedItem)
				g2d.setColor(Color.yellow);	
			else
				g2d.setColor(Color.white);	
			g2d.setStroke(new BasicStroke(8));
			g2d.drawRoundRect(x, y,gp.getTileSize(),gp.getTileSize(),10,10);
			g2d.drawImage(gp.getEntityImage(i), x, y,null);
							
			y+=60;
		}
		
	}

	private void drawObject(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
	}
//-10
//48
//60	

	private void drawTiles(Graphics2D g2d) {
		int x=defX+10; int y=defY;
		for(int i=0;i<maxTiles;i++) {
			if(i==selectedItem)
				g2d.setColor(Color.yellow);	
			else
				g2d.setColor(Color.white);	
			g2d.setStroke(new BasicStroke(8));
			g2d.drawRoundRect(x, y,gp.getTileSize(),gp.getTileSize(),10,10);
			g2d.drawImage(gp.getTileImage(i), x, y,gp.getTileSize(),gp.getTileSize(),null);
							
			y+=60;
		}
	}
	private void drawLoad(Graphics2D g2d) {
		int x=defX; int y=defY;
		int index=0;
		g2d.setFont(boxFont);
		FontMetrics fm=g2d.getFontMetrics();
		String[] text= {"Tiles","Objects","Entities","Player","ScreenSize","Show Grid","Exit"};
		String drawText="";
		for(int i=0;index<text.length;i++,y+=Y_INCREASE) {
			drawText+=text[index];
			if(index>=4&&index<text.length-1)
				index++;
			else if(index==text.length-1) {
				x=(screenWidth/4)-(boxWidth/2);
				y=screenHeight-boxHeight-10;
				index++;
			}
			else if(i%2==0)
				drawText="Load"+drawText;
			else {
				drawText="Edit"+drawText;
				index++;}
		g2d.setColor(Color.white);	
		g2d.drawRoundRect(x, y,boxWidth,boxHeight,arcCurve,arcCurve);
		//drawing string
		g2d.setColor(Color.black);
		int stringWidth=x+(boxWidth-fm.stringWidth(drawText))/2;
		int stringHeight=y+boxHeight-fm.getAscent();
		g2d.drawString(drawText,stringWidth,stringHeight);
		drawText="";
		}	
		
		g2d.setFont(arrowFont);
		g2d.drawString("<",arrowX,arrowY);
	}
//======================Methods=========================	
	public void moveArrow(char Direction) {
		int max=-1;
		switch(currentScreen) {
		case SCREEN_LOAD:max+=maxLoad;break;
		case SCREEN_TILES:max+=maxTiles;break;
		case SCREEN_ENTITIES:max+=maxEntity;break;
		case SCREEN_OBJECTS:max+=maxObject;break;
		}
		if(Direction=='u') 
			if(arrowY==arrowDefY)
				arrowY=arrowDefY+max*Y_INCREASE;
			else
				arrowY-=Y_INCREASE;
		else 
			if(arrowY==arrowDefY+max*Y_INCREASE)
				arrowY=arrowDefY;
			else
				arrowY+=Y_INCREASE;
	}
	public void chooseBox() {
		int choice=(arrowY-arrowDefY)/Y_INCREASE;
		if(currentScreen==SCREEN_LOAD) {
			switch(choice) {
			case 0:gp.loadTiles();maxTiles=gp.getMaxTiles();break;
			case 1:
				if(gp.isLoaded(choice)) {//edit tiles
					currentScreen=SCREEN_TILES;panelWidth=100;}
				else
					System.out.println("Tiles not Loaded");break;
			case 2:gp.loadObject();break;
			case 3:// edit object
				if(gp.isLoaded(choice)) {}
				else
					System.out.println("Objects not Loaded");break;
			case 4:gp.loadEntity();break;
			case 5:
				if(gp.isLoaded(choice)) {//edit entities
					currentScreen=SCREEN_ENTITIES;panelWidth=100;}
				else
					System.out.println("Entities not Loaded");
				break;
			case 6:gp.loadPlayer();break;
			case 7:	if(gp.isLoaded(choice)) {}//edit player
					else
						System.out.println("Player not Loaded");break;
			case 8:gp.changeScreenSize(11,13);break;
			case 9:gp.toggleGrid();break;
			}
		}
	}

	public void setUp() {
		screenWidth=gp.getScreenWidth();
		screenHeight=gp.getScreenHeight();
		panelWidth=screenWidth/2;
		//box
		boxWidth=screenWidth/5;
		boxHeight=screenHeight/15;
		arcCurve=boxHeight;
		Y_INCREASE=boxHeight+10;
		int boxFontSize=screenWidth/36;
		boxFont=new Font("Arial",Font.BOLD,boxFontSize);
		//arrow
		arrowDefY=boxHeight-(boxHeight/10);
		arrowX=boxWidth+10;
		arrowY=arrowDefY;
		int arrowFontSize=screenWidth/24;
		arrowFont=new Font("Arial",Font.BOLD,arrowFontSize);
	}

	
	
	
}
