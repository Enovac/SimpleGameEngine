package engine;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameFrame extends JFrame{
	public static GameFrame frame;
	public GamePanel gamepanel;
	public static void main(String[]args) {
		frame=new GameFrame();
		frame.run();
	}
	public void run() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamepanel=new GamePanel(this);
		JMenuBar menuBar=new JMenuBar();
		JMenu fileBar=new JMenu("File");
		JMenuItem save=new JMenuItem("Save Game");
		save.addActionListener(new SaveMenuListener());
		JMenuItem load=new JMenuItem("Load Game");
		load.addActionListener(new LoadMenuListener());
		fileBar.add(save);
		fileBar.add(load);
		menuBar.add(fileBar);
		frame.setJMenuBar(menuBar);
		add(gamepanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private class SaveMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser=new JFileChooser();
			chooser.showSaveDialog(gamepanel);
			File file=chooser.getSelectedFile();
			try {
				BufferedWriter writer=new BufferedWriter(new FileWriter(file));
				writer.close();
			}catch(IOException ex) {
				System.out.println("Couldnt Save");ex.printStackTrace();
			}
		}
		
	}
	private class LoadMenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser=new JFileChooser();
			chooser.showSaveDialog(gamepanel);
			File file=chooser.getSelectedFile();
		}
		
	}

	

}
