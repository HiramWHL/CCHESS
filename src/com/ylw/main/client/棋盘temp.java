package com.ylw.main.client;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class XiangQi extends JFrame implements ActionListener {

    public static final Color bgColor = new Color(245, 250, 160);
    public static final Color focusbg = new Color(242, 242, 242);
    public static final Color focuschar = new Color(96, 95, 91);
    public static final Color color1 = new Color(249, 50, 183);
    public static final Color color2 = Color.white;
    public Image img = null;//定义一个图片属性
    
    JLabel jlHost = new JLabel("主机名");
    JLabel jlPort = new JLabel("端口号");
    JLabel jlNickName = new JLabel("昵    称");
    

    JTextField jtfHost = new JTextField("127.0.0.1");
    JTextField jtfPort = new JTextField("6464");
    JTextField jtfNickName = new JTextField("Play1");
    
    ImageIcon iconconnect = new ImageIcon("./img/connect.png");
    JButton jbConnectWindow = new JButton(iconconnect);
    
    JButton jbConnect = new JButton("连 接");
    JButton jbDisconnect = new JButton("断  开");
    
    ImageIcon iconlose = new ImageIcon("./img/lose.png");
    JButton jbFail = new JButton(iconlose);
    
    
    ImageIcon iconchallenge = new ImageIcon("./img/challenge.png");
    JButton jbChallenge = new JButton(iconchallenge);
    
    JLabel jcbNickListLab = new JLabel("请选择挑战玩家：");
    JComboBox jcbNickList = new JComboBox();
    
    ImageIcon iconaccept = new ImageIcon("./img/accept.png");
    JButton jbYChallenge = new JButton(iconaccept);
    
    ImageIcon iconrefuse = new ImageIcon("./img/refuse.png");
    JButton jbNChallenge = new JButton(iconrefuse);
    
    ImageIcon iconabout = new ImageIcon("./img/about.png");
    JButton jbAboutWindow = new JButton(iconabout);
    
    ImageIcon iconPu = new ImageIcon("./img/pujuefei.jpg");
    JButton pujuefei = new JButton(iconPu);

    int width = 60;

    QiZi[][] qiZi = new QiZi[9][10];
    QiPan jpz = new QiPan(qiZi, width, this);

    JPanel jpy = new JPanel();
    JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jpz, jpy);

    boolean caiPan = false;//可否走棋的标志位
    int color = 0;//0 代表红棋，1代表白棋

    Socket sc;

    ClientAgentThread cat;

    public XiangQi() {

        this.initialComponent();

        this.addListener();

        this.initialState();

        this.initialQiZi();

        this.initialFrame();

    }

    public void initialComponent() {
        //this.setUndecorated(true);
        
        //jpy.setBackground(Color.blue);
        jpy.setLayout(null);

        /*this.jlHost.setBounds(10, 10, 50, 20);
        jpy.add(this.jlHost);

        this.jtfHost.setBounds(70, 10, 80, 20);
        jpy.add(this.jtfHost);

        this.jlPort.setBounds(10, 40, 50, 20);
        jpy.add(this.jlPort);

        this.jtfPort.setBounds(70, 40, 80, 20);
        jpy.add(this.jtfPort);

        this.jbConnect.setBounds(0, 100, 276, 42);
        jpy.add(this.jbConnect);

        this.jbDisconnect.setBounds(100, 100, 80, 20);
        jpy.add(this.jbDisconnect);*/

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

        qiZi[0][0] = new QiZi(color1, "車","rc", 0, 0);
        qiZi[1][0] = new QiZi(color1, "馬","rm", 1, 0);
        qiZi[2][0] = new QiZi(color1, "相","rx", 2, 0);
        qiZi[3][0] = new QiZi(color1, "仕","rs", 3, 0);
        qiZi[4][0] = new QiZi(color1, "帥","rb", 4, 0);
        qiZi[5][0] = new QiZi(color1, "仕","rs", 5, 0);
        qiZi[6][0] = new QiZi(color1, "相","rx", 6, 0);
        qiZi[7][0] = new QiZi(color1, "馬","rm", 7, 0);
        qiZi[8][0] = new QiZi(color1, "車","rc", 8, 0);
        qiZi[1][2] = new QiZi(color1, "砲","rp", 1, 2);
        qiZi[7][2] = new QiZi(color1, "砲","rp", 7, 2);
        qiZi[0][3] = new QiZi(color1, "兵","rz", 0, 3);
        qiZi[2][3] = new QiZi(color1, "兵","rz", 2, 3);
        qiZi[4][3] = new QiZi(color1, "兵","rz", 4, 3);
        qiZi[6][3] = new QiZi(color1, "兵","rz", 6, 3);
        qiZi[8][3] = new QiZi(color1, "兵","rz", 8, 3);

        qiZi[0][9] = new QiZi(color2,"車", "bc", 0, 9);
        qiZi[1][9] = new QiZi(color2,"馬","bm", 1, 9);
        qiZi[2][9] = new QiZi(color2,"象","bx", 2, 9);
        qiZi[3][9] = new QiZi(color2,"士","bs", 3, 9);
        qiZi[4][9] = new QiZi(color2,"將","bb", 4, 9);
        qiZi[5][9] = new QiZi(color2,"士","bs", 5, 9);
        qiZi[6][9] = new QiZi(color2,"象","bx", 6, 9);
        qiZi[7][9] = new QiZi(color2,"馬","bm", 7, 9);
        qiZi[8][9] = new QiZi(color2,"車","bc", 8, 9);
        qiZi[1][7] = new QiZi(color2,"炮","bp", 1, 7);
        qiZi[7][7] = new QiZi(color2,"炮","bp", 7, 7);
        qiZi[0][6] = new QiZi(color2,"卒","bz", 0, 6);
        qiZi[2][6] = new QiZi(color2,"卒","bz", 2, 6);
        qiZi[4][6] = new QiZi(color2,"卒","bz", 4, 6);
        qiZi[6][6] = new QiZi(color2,"卒","bz", 6, 6);
        qiZi[8][6] = new QiZi(color2,"卒","bz", 8, 6);

    }

    public void initialFrame() {

        this.setTitle("CCHESS Client");
        Image image = new ImageIcon("./img/icon.png").getImage();
        this.setIconImage(image);
        this.add(this.jsp);
        
        jsp.setDividerLocation(690);
        jsp.setDividerSize(1);

        this.setBounds(30, 30, 930, 730);
        this.setVisible(true);
        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        if (cat == null)//客户端代理线程为空，直接退出
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
                            cat.flag = false;//终止客户端代理线程
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
        
        JFrame frame = new JFrame("连接象棋服务器");
        frame.setBounds(10, 10, 300, 200);
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
        
        frame.getContentPane().add(jl);
        frame.setVisible(true);
    }
    
    public void jbAboutWindow_event() {
        JFrame frameabout = new JFrame("关于本软件");
        frameabout.setBounds(10, 10, 720, 945);
        JLabel jlabout = new JLabel();
        jlabout.setLayout(null);
        
        
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
            JOptionPane.showMessageDialog(this, "端口号只能是整数", "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (port > 65535 || port < 0) {
            JOptionPane.showMessageDialog(this, "端口号只能是0-65535的整数", "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = this.jtfNickName.getText().trim();

        if (name.length() == 0) {
            JOptionPane.showMessageDialog(this, "玩家姓名不能为空", "错误",
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
            
            JOptionPane.showMessageDialog(this, "已连接到服务器", "提示",
                    JOptionPane.INFORMATION_MESSAGE);
            this.jcbNickList.setBounds(20, 50, 130, 20);
            jpy.add(this.jcbNickList);
            this.jcbNickListLab.setBounds(20, 30, 130, 20);
            jpy.add(this.jcbNickListLab);
            jpy.repaint();
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(this, "连接服务器失败", "错误",
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
            JOptionPane.showMessageDialog(this, "请选择对方名字", "错误",
                    JOptionPane.ERROR_MESSAGE);//当未选中挑战对象，给出错误提示信息
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
                JOptionPane.showMessageDialog(this, "已提出挑战,请等待回复...", "提示",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public void jbYChallenge_event() {

        try {

            this.cat.dout.writeUTF("<#TONG_YI#>" + this.cat.tiaoZhanZhe);
            this.caiPan = false;//将caiPan设为false
            this.color = 1;//将color设为1

            this.jtfHost.setEnabled(false);
            this.jtfPort.setEnabled(false);
            this.jtfNickName.setEnabled(false);
            this.jbConnect.setEnabled(false);
            this.jbDisconnect.setEnabled(!true);
            this.jbChallenge.setEnabled(!true);
            this.jbYChallenge.setEnabled(false);
            this.jbNChallenge.setEnabled(false);
            this.jbFail.setEnabled(!false);

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
        this.repaint();//重绘
    }

    public void paint(Graphics g, int x, int y){
        g.drawImage(img, x, y, 60, 60 , this);
    }

    public  void slowMove(String Name, int x1, int y1, int x2, int y2) {//判断是否马
        img = Toolkit.getDefaultToolkit().createImage(Name);

        int xx = Math.abs(x1 - x2);
        int yy = Math.abs(y1 - y2);
        if(x1 == x2){
            if(y1 > y2){
                try {
                    for(int i = 0; i <= yy; i++) {
                        Thread.sleep(2);
                        paint(this.getGraphics(), x1, y1 - i);
                        this.getIgnoreRepaint();
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    for(int i = 0; i <= yy; i++) {
                        Thread.sleep(2);
                        paint(this.getGraphics(), x1, y1 + i);
                        this.getIgnoreRepaint();
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(y1 == y2){
            if(x1 > x2){
                try {
                    for(int i = 0; i <= xx; i++) {
                        Thread.sleep(2);
                        paint(this.getGraphics(), x1 - i, y1);
                        this.getIgnoreRepaint();
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    for(int i = 0; i <= xx; i++) {
                        Thread.sleep(2);
                        paint(this.getGraphics(), x1 + i, y1);
                        this.getIgnoreRepaint();
                    }
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            if(xx == yy){//相
                if(x1 > x2 && y1 > y2){
                    try {
                        for(int i = 0; i <= xx; i++) {
                            Thread.sleep(2);
                            paint(this.getGraphics(), x1 - i, y1 - i);
                            this.getIgnoreRepaint();
                        }
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(x1 > x2 && y1 < y2){
                    try {
                        for(int i = 0; i <= xx; i++) {
                            Thread.sleep(2);
                            paint(this.getGraphics(), x1 - i, y1 + i);
                            this.getIgnoreRepaint();
                        }
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else if(x1 < x2 && y1 > y2){
                    try {
                        for(int i = 0; i <= xx; i++) {
                            Thread.sleep(2);
                            paint(this.getGraphics(), x1 + i, y1 - i);
                            this.getIgnoreRepaint();
                        }
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        for(int i = 0; i <= xx; i++) {
                            Thread.sleep(2);
                            paint(this.getGraphics(), x1 + i, y1 + i);
                            this.getIgnoreRepaint();
                        }
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else{//马
                if(xx > yy){//横着 x多变于y
                    if(x1 > x2 && y1 > y2){
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 - 2 * i, y1 - i);
                        }
                    }
                    else if(x1 > x2 && y1 < y2){
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 - 2 * i, y1 + i);
                        }
                    }
                    else if(x1 < x2 && y1 > y2){
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 + 2 * i, y1 - i);
                        }
                    }
                    else {
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 + 2 * i, y1 + i);
                        }
                    }
                }
                else{
                    if(x1 > x2 && y1 > y2){
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 - i, y1 - 2 * i);
                        }
                    }
                    else if(x1 > x2 && y1 < y2){
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 - i, y1 + 2 * i);
                        }
                    }
                    else if(x1 < x2 && y1 > y2){
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 + i, y1 - 2 * i);
                        }
                    }
                    else {
                        for(int i = 0; i <= yy; i++) {
                            paint(this.getGraphics(), x1 + i, y1 + 2 * i);
                        }
                    }
                }
            }
        }

    }
    
    public static void main(String args[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            
        }

        new XiangQi();
    }
}