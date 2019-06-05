package com.ylw.main.client;
import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import com.ylw.main.client.XiangQi;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameStart extends JFrame implements ActionListener {
	
	JButton RenRenButton = new JButton("人人对战");
	JButton RenJiButton = new JButton("人机对战");
	JPanel Panel = new JPanel();
	
	private int i=0;
	
	 private QiPan board;
	   private GameController controller;
	    private QiPan view;

	public GameStart() {
		this.setTitle("请选择游戏模式");
		this.add(this.Panel);
		Panel.setLayout(null);
		this.RenRenButton.setBounds(10, 20, 176, 42);
		this.RenJiButton.setBounds(10, 100, 176, 42);
		Panel.add(this.RenRenButton);
		Panel.add(this.RenJiButton);     
	    this.setSize(300, 300);
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
		new XiangQi();
	}
	
	public void RenJiButton_event() {
		this.dispose();
		this.i=1;
	}
	
	    public void init() {
	    	while(1==1) {
    			System.out.println("1");
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
