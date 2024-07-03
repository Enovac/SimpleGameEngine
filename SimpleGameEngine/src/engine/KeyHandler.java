package engine;

import java.awt.event.*;
public class KeyHandler extends KeyAdapter{
	public boolean upPressed,downPressed,leftPressed,rightPressed;
	private GamePanel gp;
	private GameUI ui;
	public KeyHandler(GamePanel gp,GameUI ui) {
		this.gp=gp;
		this.ui=ui;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int currentState=gp.getCurrentState();
		if(e.getKeyCode()==KeyEvent.VK_1) {
			if(gp.debug)
				gp.debug=false;
			else
				gp.debug=true;
		}
		else if(currentState==gp.PLAY_STATE) {
			playStateP(e);
		}
		else if(currentState==gp.EDIT_STATE) {
			editStateP(e);
		}
		else {
			toolState(e);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int currentState=gp.getCurrentState();
		if(currentState==gp.PLAY_STATE) {
			playStateR(e);
		}

			
	}
	
	
	
	public void editStateP(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_P)
			gp.setState(gp.PLAY_STATE);
		else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			gp.setState(gp.TOOL_STATE);
	}
	public void playStateP(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:upPressed=true;break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:downPressed=true;break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:leftPressed=true;break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:rightPressed=true;break;
		case KeyEvent.VK_P:gp.setState(gp.EDIT_STATE);break;
		case KeyEvent.VK_ESCAPE:gp.setState(gp.TOOL_STATE);break;
		}	
	}
	public void playStateR(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:upPressed=false;break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:downPressed=false;break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:leftPressed=false;break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:rightPressed=false;break;
		}
		
	}
	public void toolState(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) 
			ui.moveArrow('u');
		else if(e.getKeyCode()==KeyEvent.VK_S) 
			ui.moveArrow('d');
		else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			ui.chooseBox();
		}
		else if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			if(ui.getCurrentScreen()==GameUI.SCREEN_LOAD)
				gp.setState(gp.EDIT_STATE);
				ui.setLoadScreen();
				ui.resetPanelWidth();
				}
		else if(e.getKeyCode()==KeyEvent.VK_P) {
			ui.setLoadScreen();
			ui.setSelectedItem(-1);
			gp.setState(gp.PLAY_STATE);
		}
		else if(e.getKeyCode()==KeyEvent.VK_M)
			ui.enableText=true;
	}
	
	
	
	


}
