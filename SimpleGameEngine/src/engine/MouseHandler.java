package engine;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputAdapter;

public class MouseHandler extends MouseInputAdapter{
	GamePanel gp;
	GameUI ui;
	public MouseHandler(GamePanel gp,GameUI ui) {
		this.gp=gp;
		this.ui=ui;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(gp.getCurrentState()==gp.TOOL_STATE) {
			toolStateP(e);
		}
		
		
		
	}
	public void toolStateP(MouseEvent e) {
		if(ui.getCurrentScreen()==ui.SCREEN_TILES) {
			int x=e.getX();int y=e.getY();int id=ui.getSelectedItem();
			if(x>=10&&x<=58) {
				for(int i=0;i<4;i++) {
					int upper=10+i*60;
					int lower=upper+48;
					if(y>=upper&&y<=lower) {
						ui.setSelectedItem(i);
						break;}
					
				}}
			else if(id!=-1){
				x-=gp.getMiddleScreenX();
				y-=gp.getMiddleScreenY();
				int spawnCol=x/gp.getTileSize();
				int spawnRow=y/gp.getTileSize();
				if(x<spawnCol*gp.getTileSize())
					spawnCol--;
				if(y<spawnRow*gp.getTileSize())
					spawnRow--;
				gp.spawnNewTile(id,spawnCol,spawnRow);
			}
		
			
			
			
			
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
