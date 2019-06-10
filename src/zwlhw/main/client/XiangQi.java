package zwlhw.main.client;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.*; 

import zwlhw.main.client.Music;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.player.Player;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Scanner;
import java.util.*;


/**
 * Most functions are based in this class. To deal with many, it a little messy.
 * */

public class XiangQi extends JFrame implements ActionListener {

    public static final Color bgColor = new Color(245, 250, 160);
    public static final Color focusbg = new Color(242, 242, 242);
    public static final Color focuschar = new Color(96, 95, 91);
    public static final Color color1 = new Color(249, 50, 183);
    public static final Color color2 = Color.white;
    public Image img = null;//define a img.
    
    static StringIndex GetStr= new StringIndex();
    static String root  = URLDecoder.decode(FileLoader.class.getResource("/").getPath());

    
    int playid=0;
    
    JFrame frame = new JFrame(GetStr.back_Strings(9));
    
    JLabel jlHost = new JLabel(GetStr.back_Strings(3));
    JLabel jlPort = new JLabel(GetStr.back_Strings(4));
    JLabel jlNickName = new JLabel(GetStr.back_Strings(5));
    
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
    int screenWidth = screenSize.width;      //get the screen's width
    int screenHeight = screenSize.height;       //get the screen's height
    

    JTextField jtfHost = new JTextField("127.0.0.1");
    JTextField jtfPort = new JTextField("6464");
    JTextField jtfNickName = new JTextField("6464");
    
    ImageIcon iconconnect = new ImageIcon(root+"img/connect.png");
    JButton jbConnectWindow = new JButton(iconconnect);
    
    JButton jbConnect = new JButton(GetStr.back_Strings(6));
    JButton jbDisconnect = new JButton(GetStr.back_Strings(7));
    
    ImageIcon iconlose = new ImageIcon(root+"img/lose.png");
    JButton jbFail = new JButton(iconlose);
    
    
    ImageIcon iconchallenge = new ImageIcon(root+"img/challenge.png");
    JButton jbChallenge = new JButton(iconchallenge);
    
    JLabel jcbNickListLab = new JLabel(GetStr.back_Strings(8));
    JComboBox jcbNickList = new JComboBox();
    
    ImageIcon iconaccept = new ImageIcon(root+"img/accept.png");
    JButton jbYChallenge = new JButton(iconaccept);
    
    ImageIcon iconrefuse = new ImageIcon(root+"img/refuse.png");
    JButton jbNChallenge = new JButton(iconrefuse);
    
    ImageIcon iconabout = new ImageIcon(root+"img/about.png");
    JButton jbAboutWindow = new JButton(iconabout);
    
    ImageIcon iconPu = new ImageIcon(root+"img/pujuefei.jpg");
    JButton pujuefei = new JButton(iconPu);
    
    int width = 60;

    QiZi[][] qiZi = new QiZi[9][10];
    QiPan jpz = new QiPan(qiZi, width, this);

    JPanel jpy = new JPanel();
    JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpz, jpy);

    boolean caiPan = false;//Sign of can move.
    int color = 0;//0 -> red，1 -> white. 

    Socket sc;

    ClientAgentThread cat;

    public XiangQi(int w, int h) {
    	
        this.initialComponent();

        this.addListener();

        this.initialState();

        this.initialQiZi();

        this.initialFrame(w, h);
        
        Random random=new Random();
        
		this.playid=random.nextInt(10000)+10000;
		this.jtfNickName.setText("Player"+playid);

    }

    public void initialComponent() {
    	
        jpy.setLayout(null);

        this.jbConnectWindow.setBounds(0, 100, 276, 42);
        jpy.add(this.jbConnectWindow);

        this.jbChallenge.setBounds(0, 180, 276, 42);
        jpy.add(this.jbChallenge);

        this.jbFail.setBounds(0, 240, 276, 42);
        jpy.add(this.jbFail);

        this.jbYChallenge.setBounds(0, 300, 276, 42);
        jpy.add(this.jbYChallenge);

        this.jbNChallenge.setBounds(0, 360, 276, 42);
        jpy.add(this.jbNChallenge);
        
        this.jbAboutWindow.setBounds(0, 480, 276, 42);
        jpy.add(this.jbAboutWindow);

        jpz.setLayout(null);
        jpz.setBounds(0, 0, 700, 700);
    }

    public void addListener() {
    	this.jbConnectWindow.addActionListener(this);
        this.jbConnect.addActionListener(this);
        this.jbDisconnect.addActionListener(this);
        this.jbChallenge.addActionListener(this);
        this.jbFail.addActionListener(this);
        this.jbYChallenge.addActionListener(this);
        this.jbNChallenge.addActionListener(this);
        this.jbAboutWindow.addActionListener(this);
    }

    public void initialState() {

        this.jbDisconnect.setEnabled(false);
        this.jbChallenge.setEnabled(false);
        this.jbYChallenge.setEnabled(false);
        this.jbNChallenge.setEnabled(false);
        this.jbFail.setEnabled(false);
    }

    public void initialQiZi() {

        qiZi[0][0] = new QiZi(color1, "che","rc", 0, 0);
        qiZi[1][0] = new QiZi(color1, "ma","rm", 1, 0);
        qiZi[2][0] = new QiZi(color1, "xiang","rx", 2, 0);
        qiZi[3][0] = new QiZi(color1, "shi","rs", 3, 0);
        qiZi[4][0] = new QiZi(color1, "shuai","rb", 4, 0);
        qiZi[5][0] = new QiZi(color1, "shi","rs", 5, 0);
        qiZi[6][0] = new QiZi(color1, "xiang","rx", 6, 0);
        qiZi[7][0] = new QiZi(color1, "ma","rm", 7, 0);
        qiZi[8][0] = new QiZi(color1, "che","rc", 8, 0);
        qiZi[1][2] = new QiZi(color1, "pao","rp", 1, 2);
        qiZi[7][2] = new QiZi(color1, "pao","rp", 7, 2);
        qiZi[0][3] = new QiZi(color1, "bin","rz", 0, 3);
        qiZi[2][3] = new QiZi(color1, "bin","rz", 2, 3);
        qiZi[4][3] = new QiZi(color1, "bin","rz", 4, 3);
        qiZi[6][3] = new QiZi(color1, "bin","rz", 6, 3);
        qiZi[8][3] = new QiZi(color1, "bin","rz", 8, 3);

        qiZi[0][9] = new QiZi(color2,"che", "bc", 0, 9);
        qiZi[1][9] = new QiZi(color2,"ma","bm", 1, 9);
        qiZi[2][9] = new QiZi(color2,"heixiang","bx", 2, 9);
        qiZi[3][9] = new QiZi(color2,"heishi","bs", 3, 9);
        qiZi[4][9] = new QiZi(color2,"jiang","bb", 4, 9);
        qiZi[5][9] = new QiZi(color2,"heishi","bs", 5, 9);
        qiZi[6][9] = new QiZi(color2,"heixiang","bx", 6, 9);
        qiZi[7][9] = new QiZi(color2,"ma","bm", 7, 9);
        qiZi[8][9] = new QiZi(color2,"che","bc", 8, 9);
        qiZi[1][7] = new QiZi(color2,"heipao","bp", 1, 7);
        qiZi[7][7] = new QiZi(color2,"heipao","bp", 7, 7);
        qiZi[0][6] = new QiZi(color2,"zu","bz", 0, 6);
        qiZi[2][6] = new QiZi(color2,"zu","bz", 2, 6);
        qiZi[4][6] = new QiZi(color2,"zu","bz", 4, 6);
        qiZi[6][6] = new QiZi(color2,"zu","bz", 6, 6);
        qiZi[8][6] = new QiZi(color2,"zu","bz", 8, 6);

    }

    public void initialFrame(int screenWidth, int screenHeight) {

        this.setTitle(GetStr.back_Strings(0));
        Image image = new ImageIcon(root+"img/icon.png").getImage();
        this.setIconImage(image);
        this.add(this.jsp);
        
        jsp.setDividerLocation(690);
        jsp.setDividerSize(1);
        
        this.setTitle(GetStr.back_Strings(0));
        this.setBounds((screenWidth - 930)/2, (screenHeight - 730)/2, 930, 730);//左上角的位置x,y 和框的宽和高x,y  使窗口居中
        this.setVisible(true);
        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        if (cat == null)//客户端代理线程为空，直接退出 client is empty, exit.
                        {
                            System.exit(0);
                            return;
                        }
                        try {
                            if (cat.tiaoZhanZhe != null)
                            {
                                try {

                                    cat.dout.writeUTF("<#RENSHU#>" + cat.tiaoZhanZhe);
                                } catch (Exception ee) {
                                    ee.printStackTrace();
                                }
                            }
                            cat.dout.writeUTF("<#CLIENT_LEAVE#>");
                            cat.flag = false;//终止客户端代理线程 stop client
                            cat = null;

                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        System.exit(0);
                    }

                }
        );
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.jbConnectWindow) {
            this.jbConnectWindow_event();
        } else if (e.getSource() == this.jbConnect) {
            this.jbConnect_event();}
        else if (e.getSource() == this.jbDisconnect) {
            this.jbDisconnect_event();
        } else if (e.getSource() == this.jbChallenge) {
            this.jbChallenge_event();
        } else if (e.getSource() == this.jbYChallenge) {
            this.jbYChallenge_event();
        } else if (e.getSource() == this.jbNChallenge) {
            this.jbNChallenge_event();
        } else if (e.getSource() == this.jbFail) {
            this.jbFail_event();
        }else if (e.getSource() == this.jbAboutWindow) {
            this.jbAboutWindow_event();
        }
    }

    public void jbConnectWindow_event() {
    	
        
        frame.setBounds((screenWidth - 300)/2, (screenHeight - 200)/2, 300, 200);
        JLabel jl = new JLabel();
        jl.setLayout(null);
        
        this.jlHost.setBounds(10, 10, 50, 20);
        jl.add(this.jlHost);

        this.jtfHost.setBounds(70, 10, 80, 20);
        jl.add(this.jtfHost);

        this.jlPort.setBounds(10, 40, 50, 20);
        jl.add(this.jlPort);

        this.jtfPort.setBounds(70, 40, 80, 20);
        jl.add(this.jtfPort);
        
        this.jlNickName.setBounds(10, 70, 50, 20);
        jl.add(this.jlNickName);

        this.jtfNickName.setBounds(70, 70, 80, 20);
        jl.add(this.jtfNickName);
        
        this.jbConnect.setBounds(10, 120, 80, 20);
        jl.add(this.jbConnect);

        this.jbDisconnect.setBounds(150, 120, 80, 20);
        jl.add(this.jbDisconnect);
        Image image = new ImageIcon(root+"img/icon.png").getImage();
        frame.setIconImage(image);
        frame.getContentPane().add(jl);
        frame.setVisible(true);
    }
    
    public void jbAboutWindow_event() {
    	JFrame frameabout = new JFrame(GetStr.back_Strings(10));
    	frameabout.setBounds(10, 10, 720, 945);
        JLabel jlabout = new JLabel();
        jlabout.setLayout(null);
        Image image = new ImageIcon(root+"img/icon.png").getImage();
        frameabout.setIconImage(image);
        
        
        this.pujuefei.setBounds(0, 0, 720, 945);
        jlabout.add(this.pujuefei);
        
        frameabout.getContentPane().add(jlabout);
        frameabout.setVisible(true);
    }
    
    public void jbConnect_event() {
    	int port = 0;

        try {
            port = Integer.parseInt(this.jtfPort.getText().trim());
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(this, GetStr.back_Strings(12), GetStr.back_Strings(11),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (port > 65535 || port < 0) {
            JOptionPane.showMessageDialog(this, GetStr.back_Strings(13), GetStr.back_Strings(11),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = this.jtfNickName.getText().trim();

        if (name.length() == 0) {
            JOptionPane.showMessageDialog(this, GetStr.back_Strings(14), GetStr.back_Strings(11),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            sc = new Socket(this.jtfHost.getText().trim(), port);
            cat = new ClientAgentThread(this);
            cat.start();

            this.jtfHost.setEnabled(false);
            this.jtfPort.setEnabled(false);
            this.jtfNickName.setEnabled(false);
            this.jbConnect.setEnabled(false);
            this.jbDisconnect.setEnabled(true);
            this.jbChallenge.setEnabled(true);
            this.jbYChallenge.setEnabled(false);
            this.jbNChallenge.setEnabled(false);
            this.jbFail.setEnabled(false);
            frame.dispose();
            

            this.jcbNickList.setBounds(20, 50, 130, 20);
            jpy.add(this.jcbNickList);
            this.jcbNickListLab.setBounds(20, 30, 130, 20);
            jpy.add(this.jcbNickListLab);
            jpy.repaint();
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(this, GetStr.back_Strings(17), GetStr.back_Strings(11),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public void jbDisconnect_event() {
        try {
            this.cat.dout.writeUTF("<#CLIENT_LEAVE#>");
            this.cat.flag = false;
            this.cat = null;
            this.jtfHost.setEnabled(!false);
            this.jtfPort.setEnabled(!false);
            this.jtfNickName.setEnabled(!false);
            this.jbConnect.setEnabled(!false);
            this.jbDisconnect.setEnabled(!true);
            this.jbChallenge.setEnabled(!true);
            this.jbYChallenge.setEnabled(false);
            this.jbNChallenge.setEnabled(false);
            this.jbFail.setEnabled(false);

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void jbChallenge_event() {

        Object o = this.jcbNickList.getSelectedItem();

        if (o == null || ((String) o).equals("")) {
            JOptionPane.showMessageDialog(this, GetStr.back_Strings(18), GetStr.back_Strings(11),
                    JOptionPane.ERROR_MESSAGE);//without choosing a object, point out the wrong information.
            //当未选中挑战对heixiang，给出错误提示信息 without
        } else {

            String name2 = (String) this.jcbNickList.getSelectedItem();

            try {
                this.jtfHost.setEnabled(false);
                this.jtfPort.setEnabled(false);
                this.jtfNickName.setEnabled(false);
                this.jbConnect.setEnabled(false);
                this.jbDisconnect.setEnabled(!true);
                this.jbChallenge.setEnabled(!true);
                this.jbYChallenge.setEnabled(false);
                this.jbNChallenge.setEnabled(false);
                this.jbFail.setEnabled(false);

                this.cat.tiaoZhanZhe = name2;
                this.caiPan = true;
                this.color = 0;

                this.cat.dout.writeUTF("<#TIAO_ZHAN#>" + name2);
                JOptionPane.showMessageDialog(this, GetStr.back_Strings(19), GetStr.back_Strings(15),
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public void jbYChallenge_event() {

        try {

            this.cat.dout.writeUTF("<#TONG_YI#>" + this.cat.tiaoZhanZhe);
            this.caiPan = false;//caiPan -> false
            this.color = 1;//color -> 1
            

            this.jtfHost.setEnabled(false);
            this.jtfPort.setEnabled(false);
            this.jtfNickName.setEnabled(false);
            this.jbConnect.setEnabled(false);
            this.jbDisconnect.setEnabled(!true);
            this.jbChallenge.setEnabled(!true);
            this.jbYChallenge.setEnabled(false);
            this.jbNChallenge.setEnabled(false);
            this.jbFail.setEnabled(!false);
            this.repaint();

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void jbNChallenge_event() {

        try {

            this.cat.dout.writeUTF("<#BUTONG_YI#>" + this.cat.tiaoZhanZhe);
            this.cat.tiaoZhanZhe = null;
            this.jtfHost.setEnabled(false);
            this.jtfPort.setEnabled(false);
            this.jtfNickName.setEnabled(false);
            this.jbConnect.setEnabled(false);
            this.jbDisconnect.setEnabled(true);
            this.jbChallenge.setEnabled(true);
            this.jbYChallenge.setEnabled(false);
            this.jbNChallenge.setEnabled(false);
            this.jbFail.setEnabled(false);

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void jbFail_event() {

        try {

            this.cat.dout.writeUTF("<#RENSHU#>" + this.cat.tiaoZhanZhe);
            this.cat.tiaoZhanZhe = null;
            this.color = 0;
            this.caiPan = false;

            this.next();

            this.jtfHost.setEnabled(false);
            this.jtfPort.setEnabled(false);
            this.jtfNickName.setEnabled(false);
            this.jbConnect.setEnabled(false);
            this.jbDisconnect.setEnabled(true);
            this.jbChallenge.setEnabled(true);
            this.jbYChallenge.setEnabled(false);
            this.jbNChallenge.setEnabled(false);
            this.jbFail.setEnabled(false);

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void next() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                this.qiZi[i][j] = null;
            }
        }
        this.caiPan = false;
        this.initialQiZi();
        this.repaint();//repaint
    }
    
    public void Music(String name,boolean loop) {              //Music. files are in the src/music
    	try {     
    		File f = new File(root+"music/" + name); 
    		Music player=new Music(f,loop);
    		player.start();
    	    
        } catch (Exception e) 
        { 
            e.printStackTrace();
        } 
    }

    public void paint(Graphics g, int x, int y){//Make a slow move.
        g.drawImage(img, x, y, 60, 60 , this);
    }

    public void move(int n, int x, int y, int ratex, int ratey){//设置倍率和正负 进行残影循环 set the rate and plus-minus and loop to paint.
    	if(this.caiPan==false) {
    		return;//x y both are subscript
    	}
    	try{
    		for(int i = 0; i <= n; i+=10){
    			Thread.sleep(1);
    			paint(this.getGraphics(), x += ratex, y += ratey);
    		}
    	}
    	catch (InterruptedException e) {
			e.printStackTrace();
		} 
    }

    public void slowMove(String Name, int x1, int y1, int x2, int y2) {
    	
    	if(this.caiPan==false) {
    		return;//x y both are subscript
    	}
    	
    	if(qiZi[x2][y2]!=null) {
    		this.Music("chi.mp3",false);
    	}else {
    		this.Music("peng.mp3",false);
    	}
    	
        img = Toolkit.getDefaultToolkit().createImage(Name);

        
        int xx1 = x1 *60 + 85;//through subscript to calculate the pixel.
        int xx2 = x2 * 60 + 85;
        int yy1 = y1 * 60 + 95;
        int yy2 = y2 * 60 + 95;
        int xx = Math.abs(xx1 - xx2);//起点和终点的像素差 calculate the pixel D-value.
        int yy = Math.abs(yy1 - yy2);
        if(x1 == x2){//Search for direction and kinds to make slow move.
        	//以下为枚举判断各种棋子极其方位，进行残影绘制的参数，使之看起来无异常。
            if(y1 > y2){
            	move(yy, xx1, yy1, 0, -1);
            }
            else{
                move(yy, xx1, yy1, 0, 1);
            }
        }
        else if(y1 == y2){
            if(x1 > x2){
                move(xx, xx1, yy1, -1, 0);
            }
            else{
                move(xx, xx1, yy1, 1, 0);
            }
        }
        else{
            if(xx == yy){//xiang
                if(x1 > x2 && y1 > y2){
                    move(xx, xx1, yy1, -1, -1);
                }
                else if(x1 > x2 && y1 < y2){
                    move(xx, xx1, yy1, -1, 1);
                }
                else if(x1 < x2 && y1 > y2){
                    move(xx, xx1, yy1, 1, -1);
                }
                else {
                    move(xx, xx1, yy1, 1, 1);
                }
            }
            else{//马
                if(xx > yy){// x is more than y
                    if(x1 > x2 && y1 > y2){
                        move(yy, xx1, yy1, -2, -1);
                    }
                    else if(x1 > x2 && y1 < y2){
                        move(yy, xx1, yy1, -2, 1);
                    }
                    else if(x1 < x2 && y1 > y2){
                        move(yy, xx1, yy1, 2, -1);
                    }
                    else {
                        move(yy, xx1, yy1, 2, 1);
                    }
                }
                else{
                    if(x1 > x2 && y1 > y2){
                        move(xx, xx1, yy1, -1, -2);
                    }
                    else if(x1 > x2 && y1 < y2){
                        move(xx, xx1, yy1, -1, 2);
                    }
                    else if(x1 < x2 && y1 > y2){
                        move(xx, xx1, yy1, 1, -2);
                    }
                    else {
                        move(xx, xx1, yy1, 1, 2);
                    }
                }
            }
        }
        this.caiPan=false;
    }
}