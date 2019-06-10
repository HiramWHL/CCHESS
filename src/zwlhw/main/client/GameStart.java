package zwlhw.main.client;

import java.util.Scanner;
import zwlhw.main.client.StringIndex;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import zwlhw.main.client.XiangQi;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameStart extends JFrame implements ActionListener {
	
	static StringIndex GetStr= new StringIndex();
	
	private int i=0;
	
	private QiPan board;
	private GameController controller;
	private QiPan view;
	BackgroundPanel bgp;

	
	JButton RenRenButton = new JButton(GetStr.back_Strings(1));
	JButton RenJiButton = new JButton(GetStr.back_Strings(2));
	Container Panel = new Container();
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    int screenWidth = screenSize.width;      //get the screen's width
    int screenHeight = screenSize.height;       //get the screen's height
	
    static String root  = URLDecoder.decode(FileLoader.class.getResource("/").getPath());


	public GameStart() {//set a start menu
		this.setUndecorated(true);//去掉标题栏
    	this.Music("bgm.mp3",true);//bgm, with loop.
		this.setTitle(GetStr.back_Strings(0));
		bgp=new BackgroundPanel((new ImageIcon(root+"img/bg.png")).getImage());
		bgp.setBounds(0,0,515,811);
		Panel.add(bgp);
        Image image = new ImageIcon(root+"img/icon.png").getImage();
        this.setIconImage(image);
		this.add(this.Panel);
		Panel.setLayout(null);
		this.RenRenButton.setBounds(2, 560, 495, 71);
		this.RenRenButton.setOpaque(false);
		this.RenJiButton.setBounds(2, 650, 495, 71);
		this.RenJiButton.setOpaque(false);
		Panel.add(this.RenRenButton);
		Panel.add(this.RenJiButton);     
		this.setBounds((screenWidth - 515)/2, (screenHeight - 811)/2, 515, 811);//up-left x,y wide and height is x,y. let it in center.
		this.setVisible(true);
        this.RenRenButton.addActionListener(this);
        this.RenJiButton.addActionListener(this);
        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
	}

	
    public void Music(String name,boolean loop) {              // files are in the src/music
    	try {      
    		File f = new File(root+"music/" + name); 
    		Music player=new Music(f,loop);
    		player.start();
    	    
        } catch (Exception e) 
        { 
            e.printStackTrace();
        } 
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
	}
}

class BackgroundPanel extends JPanel
{
	Image im;
	public BackgroundPanel(Image im)
	{
		this.im=im;
		this.setOpaque(true);
	}
	//Draw the back ground.
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
		
	}
}