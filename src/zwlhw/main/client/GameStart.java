package zwlhw.main.client;

import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import zwlhw.main.client.XiangQi;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameStart extends JFrame implements ActionListener {
	
	private int i=0;
	
	private QiPan board;
	private GameController controller;
	private QiPan view;
	
	JButton RenRenButton = new JButton("人人对战");
	JButton RenJiButton = new JButton("人机对战");
	JPanel Panel = new JPanel();
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    int screenWidth = screenSize.width;      //get the screen's width
    int screenHeight = screenSize.height;       //get the screen's height
	

	public GameStart() {//set a start menu
		this.setTitle("CCHESS Client @神奇五鱼");
        Image image = new ImageIcon("./img/icon.png").getImage();
        this.setIconImage(image);
		this.add(this.Panel);
		Panel.setLayout(null);
		
		this.RenRenButton.setBounds(130, 40, 176, 50);
		this.RenJiButton.setBounds(130, 140, 176, 50);
		Panel.add(this.RenRenButton);
		Panel.add(this.RenJiButton);     
		this.setBounds((screenWidth - 450)/2, (screenHeight - 300)/2, 450, 300);//up-left x,y wide and hight is x,y. let it in center.
		this.setVisible(true);
        this.RenRenButton.addActionListener(this);
        this.RenJiButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.RenRenButton) {
        	RenRenButton_event();
        } else if (e.getSource() == this.RenJiButton) {
            	RenJiButton_event();
        }
    }
	
	public void RenRenButton_event() {
		this.dispose();
		new XiangQi(screenWidth, screenHeight);
	}
	
	public void RenJiButton_event() {
		this.dispose();
		this.i=1;
	}
	
	    public void init() {
	    	while(1==1) {
	    		try {
            		Thread.sleep(1000);
            		} catch (InterruptedException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
	    		if(this.i==1) {
	    			break;
	    		}
	    	}
	    	controller = new GameController();
	        board = controller.playChess();
	        view = new QiPan(controller);
	        view.init(board);
	        this.run(); 
	    }

	    public void run()  {
	       while (controller.hasWin(board) == 'x') {
	            view.showPlayer('r');
	            /* User in. */
	            while (board.player == 'r')
	            	try {
	            		Thread.sleep(1000);
	            		} catch (InterruptedException e) {
	            		// TODO Auto-generated catch block
	            		e.printStackTrace();
	            	}
	            if (controller.hasWin(board) != 'x')
	                view.showWinner('r');
	            view.showPlayer('b');
	            /* AI in. */
	            controller.responseMoveChess(board, view);
	        }
	        view.showWinner('b');
	    }
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	    	JFrame.setDefaultLookAndFeelDecorated(true);
	    	JDialog.setDefaultLookAndFeelDecorated(true);
	    	try {
	    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    	} catch (Exception e) {
	    		
	    	}
	    	GameStart game = new GameStart();
    		game.init();
			//ChessGame.main(null);
	    	
	}
}
