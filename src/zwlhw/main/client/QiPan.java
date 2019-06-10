package zwlhw.main.client;

import java.awt.Graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.*;
import zwlhw.main.client.QiPan.BoardClickListener;
import zwlhw.main.client.QiPan.PieceOnClickListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * To provide board to paint and display. 
 * There are two kinds of types by different person.
 * Just combine these two.
 * */

public class QiPan extends JPanel implements MouseListener {
	
	static StringIndex GetStr= new StringIndex();
	static String root  = URLDecoder.decode(FileLoader.class.getResource("/").getPath());


    private int width;
    boolean focus = false;
    	
    int jiang1_i = 4;
    int jiang1_j = 0;
    int jiang2_i = 4;
    int jiang2_j = 9;
    int startI = -1;
    int startJ = -1;
    int endI = -1;
    int endJ = -1;

    public QiZi qiZi[][];
    XiangQi xq = null;
    GuiZe guiZe;
    public QiPan() {};
    public QiPan(QiZi qiZi[][], int width, XiangQi xq) {

        this.xq = xq;
        this.qiZi = qiZi;
        this.width = width;

        guiZe = new GuiZe(qiZi);

        this.addMouseListener(this);
        this.setBounds(0, 0, 700, 700);
        this.setLayout(null);
    }

    public void paint(Graphics g1) {

        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Color c = g.getColor();
        Image imageQiPan=Toolkit.getDefaultToolkit().getImage(root+"img/pu.jpg");
        g.drawImage(imageQiPan, 0,0 , 1000,1000, this);

        for (int i = 80; i <= 620; i = i + 60) {
            g.drawLine(110, i, 590, i);
        }

        g.drawLine(110, 80, 110, 620);
        g.drawLine(590, 80, 590, 620);

        for (int i = 170; i <= 530; i = i + 60) {
            g.drawLine(i, 80, i, 320);
            g.drawLine(i, 380, i, 620);
        }

        g.drawLine(290, 80, 410, 200);
        g.drawLine(290, 200, 410, 80);
        g.drawLine(290, 500, 410, 620);
        g.drawLine(290, 620, 410, 500);

        this.smallLine(g, 1, 2);
        this.smallLine(g, 7, 2);
        this.smallLine(g, 0, 3);
        this.smallLine(g, 2, 3);
        this.smallLine(g, 4, 3);
        this.smallLine(g, 6, 3);
        this.smallLine(g, 8, 3);
        this.smallLine(g, 0, 6);
        this.smallLine(g, 2, 6);
        this.smallLine(g, 4, 6);
        this.smallLine(g, 6, 6);
        this.smallLine(g, 8, 6);
        this.smallLine(g, 1, 7);
        this.smallLine(g, 7, 7);

        g.setColor(Color.black);

        Font font1 = new Font(GetStr.back_Strings(20), Font.BOLD, 50);
        g.setFont(font1);

        g.drawString(GetStr.back_Strings(21), 170, 365);
        g.drawString(GetStr.back_Strings(22), 400, 365);

        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                if (qiZi[i][j] != null) {

                    if (this.qiZi[i][j].getFocus() != false) {
                    	if(this.xq.color==0) {
                    	Image imageQiZi=Toolkit.getDefaultToolkit().getImage(root+"img/"+qiZi[i][j].getPic()+".png");
                        g.drawImage(imageQiZi, 110 + i * 60 - 35,80 + (9-j) * 60 - 25 , 70,70, this);
                    	}else {
                    		Image imageQiZi=Toolkit.getDefaultToolkit().getImage(root+"img/"+qiZi[i][j].getPic()+".png");
                            g.drawImage(imageQiZi, 110 + i * 60 - 35,80 + j * 60 - 25 , 70,70, this);
                    	}

                    } else {
                    	if(this.xq.color==0) {
                    		Image imageQiZi=Toolkit.getDefaultToolkit().getImage(root+"img/"+qiZi[i][j].getPic()+".png");
                            g.drawImage(imageQiZi, 110 + i * 60 - 30,80 + (9-j) * 60 - 25 , 60,60, this);
                    	}else {
                    	Image imageQiZi=Toolkit.getDefaultToolkit().getImage(root+"img/"+qiZi[i][j].getPic()+".png");
                        g.drawImage(imageQiZi, 110 + i * 60 - 30,80 + j * 60 - 25 , 60,60, this);
                    	}
                    }
                }
            }
        }
        g.setColor(c);
    }

    public void mouseClicked(MouseEvent e) {

        if (this.xq.caiPan==true) {//这里是裁判

            int i = -1, j = -1;
            int[] pos = getPos(e);

            i = pos[0];
            j = pos[1];

            if (i >= 0 && i <= 8 && j >= 0 && j <= 9) {

                if (focus == false) {
                    this.noFocus(i, j);
                } else {

                    if (qiZi[i][j] != null) {
                        if (qiZi[i][j].getColor() == qiZi[startI][startJ].getColor()) {
                            qiZi[startI][startJ].setFocus(false);
                            qiZi[i][j].setFocus(true);
                            startI = i;
                            startJ = j;
                        } else {
                            endI = i;
                            endJ = j;
                            String name = qiZi[startI][startJ].getName();
                            
                            boolean canMove = guiZe.canMove(startI, startJ, endI, endJ, name);
                            if (canMove)
                            {
                                try {
                                    this.xq.cat.dout.writeUTF("<#MOVE#>" +
                                            this.xq.cat.tiaoZhanZhe + startI + startJ + endI + endJ);
                                    this.xq.caiPan = false;
                                    if (qiZi[endI][endJ].getName().equals("shuai") ||
                                            qiZi[endI][endJ].getName().equals("jiang")) {
                                        this.success();
                                    } else {
                                        this.noJiang();
                                    }
                                } catch (Exception ee) {
                                    ee.printStackTrace();
                                }
                            }
                        }
                    } else {
                        endI = i;
                        endJ = j;
                        String name = qiZi[startI][startJ].getName();
                        boolean canMove = guiZe.canMove(startI, startJ, endI, endJ, name);
                        if (canMove) {
                            this.noQiZi();
                        }
                    }
                }
            }
            this.xq.repaint();
        }
    }

    public int[] getPos(MouseEvent e) {

        int[] pos = new int[2];

        pos[0] = -1;
        pos[1] = -1;

        Point p = e.getPoint();

        double x = p.getX();
        double y = p.getY();

        if (Math.abs((x - 110) / 1 % 60) <= 28) {
            pos[0] = Math.round((float) (x - 110)) / 60;
        } else if (Math.abs((x - 110) / 1 % 60) >= 32) {
            pos[0] = Math.round((float) (x - 110)) / 60 + 1;
        }
        if (Math.abs((y - 80) / 1 % 60) <= 28) {
            pos[1] = Math.round((float) (y - 80)) / 60;
        } else if (Math.abs((y - 80) / 1 % 60) >= 32) {
            pos[1] = Math.round((float) (y - 80)) / 60 + 1;
        }
        if(this.xq.color==0) {
        	pos[1]=9-pos[1];
        }
        return pos;
    }

    public void noFocus(int i, int j) {

        if (this.qiZi[i][j] != null)
        {
            if (this.xq.color == 0)
            {
                if (this.qiZi[i][j].getColor().equals(XiangQi.color1))
                {
                    this.qiZi[i][j].setFocus(true);
                    focus = true;
                    startI = i;
                    startJ = j;
                }
            } else
            {
                if (this.qiZi[i][j].getColor().equals(XiangQi.color2))
                {
                    this.qiZi[i][j].setFocus(true);
                    focus = true;
                    startI = i;
                    startJ = j;
                }
            }
        }
    }

    public void success() {

        qiZi[endI][endJ] = qiZi[startI][startJ];
        qiZi[startI][startJ] = null;

        this.xq.repaint();

        JOptionPane.showMessageDialog(this.xq, GetStr.back_Strings(23), GetStr.back_Strings(15),
                JOptionPane.INFORMATION_MESSAGE);

        this.xq.cat.tiaoZhanZhe = null;
        this.xq.color = 0;
        this.xq.caiPan = false;
        this.xq.next();

        this.xq.jtfHost.setEnabled(false);
        this.xq.jtfPort.setEnabled(false);
        this.xq.jtfNickName.setEnabled(false);
        this.xq.jbConnect.setEnabled(false);
        this.xq.jbDisconnect.setEnabled(true);
        this.xq.jbChallenge.setEnabled(true);
        this.xq.jbYChallenge.setEnabled(false);
        this.xq.jbNChallenge.setEnabled(false);
        this.xq.jbFail.setEnabled(false);

        startI = -1;
        startJ = -1;
        endI = -1;
        endJ = -1;
        jiang1_i = 4;
        jiang1_j = 0;
        jiang2_i = 4;
        jiang2_j = 9;
        focus = false;
    }

    public void noJiang() {//Just eat an enemy not the boss.
        this.xq.slowMove(root+"img/"+qiZi[startI][startJ].getPic()+".png", startI, startJ, endI, endJ);
        //Set the sounds and move it slowly.
        
        qiZi[endI][endJ] = qiZi[startI][startJ];
        qiZi[startI][startJ] = null;
        qiZi[endI][endJ].setFocus(false);
        this.xq.repaint();

        if (qiZi[endI][endJ].getName().equals("shuai")) {
            jiang1_i = endI;
            jiang1_j = endJ;
        } else if (qiZi[endI][endJ].getName().equals("jiang")) {//If it's red boss, then game over.
            jiang2_i = endI;
            jiang2_j = endJ;
        }
        if (jiang1_i == jiang2_i) {
            int count = 0;
            for (int jiang_j = jiang1_j + 1; jiang_j < jiang2_j; jiang_j++) {
                if (qiZi[jiang1_i][jiang_j] != null) {
                    count++;
                    break;
                }
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(this.xq, GetStr.back_Strings(24), GetStr.back_Strings(15),
                        JOptionPane.INFORMATION_MESSAGE);

                this.xq.cat.tiaoZhanZhe = null;
                this.xq.color = 0;
                this.xq.caiPan = false;
                this.xq.next();
                this.xq.jtfHost.setEnabled(false);
                this.xq.jtfPort.setEnabled(false);
                this.xq.jtfNickName.setEnabled(false);
                this.xq.jbConnect.setEnabled(false);
                this.xq.jbDisconnect.setEnabled(true);
                this.xq.jbChallenge.setEnabled(true);
                this.xq.jbYChallenge.setEnabled(false);
                this.xq.jbNChallenge.setEnabled(false);
                this.xq.jbFail.setEnabled(false);

                jiang1_i = 4;
                jiang1_j = 0;
                jiang2_i = 4;
                jiang2_j = 9;

            }
        }
        startI = -1;
        startJ = -1;
        endI = -1;
        endJ = -1;
        focus = false;
    }

    public void noQiZi() {//Just move without eating.

        try {

            this.xq.cat.dout.writeUTF("<#MOVE#>" + this.xq.cat.tiaoZhanZhe + startI + startJ + endI + endJ);
            this.xq.caiPan = false;
            //this.xq.Music("peng.mp3");//move sound.
            this.xq.slowMove(root+"img/"+qiZi[startI][startJ].getPic()+".png", startI, startJ, endI, endJ);//show the move picture.

            qiZi[endI][endJ] = qiZi[startI][startJ];
            qiZi[startI][startJ] = null;
            qiZi[endI][endJ].setFocus(false);
           

            this.xq.repaint();
            if (qiZi[endI][endJ].getName().equals("shuai")) {
                jiang1_i = endI;
                jiang1_j = endJ;
            } else if (qiZi[endI][endJ].getName().equals("jiang")) {
                jiang2_i = endI;
                jiang2_j = endJ;
            }
            if (jiang1_i == jiang2_i)
            {
                int count = 0;
                for (int jiang_j = jiang1_j + 1; jiang_j < jiang2_j; jiang_j++) {
                    if (qiZi[jiang1_i][jiang_j] != null) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    JOptionPane.showMessageDialog(this.xq, GetStr.back_Strings(24), GetStr.back_Strings(15),
                            JOptionPane.INFORMATION_MESSAGE);

                    this.xq.cat.tiaoZhanZhe = null;
                    this.xq.color = 0;
                    this.xq.caiPan = false;
                    this.xq.next();

                    this.xq.jtfHost.setEnabled(false);
                    this.xq.jtfPort.setEnabled(false);
                    this.xq.jtfNickName.setEnabled(false);
                    this.xq.jbConnect.setEnabled(false);
                    this.xq.jbDisconnect.setEnabled(true);
                    this.xq.jbChallenge.setEnabled(true);
                    this.xq.jbYChallenge.setEnabled(false);
                    this.xq.jbNChallenge.setEnabled(false);
                    this.xq.jbFail.setEnabled(false);

                    jiang1_i = 4;
                    jiang1_j = 0;
                    jiang2_i = 4;
                    jiang2_j = 9;

                }
            }
            startI = -1;
            startJ = -1;
            endI = -1;
            endJ = -1;
            focus = false;
            

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void move(int startI, int startJ, int endI, int endJ) {
    	this.xq.slowMove(root+"img/"+qiZi[startI][startJ].getPic()+".png",startI, startJ, endI, endJ);
        if (qiZi[endI][endJ] != null && (qiZi[endI][endJ].getName().equals("shuai") ||
                qiZi[endI][endJ].getName().equals("jiang"))) {
            qiZi[endI][endJ] = qiZi[startI][startJ];
            qiZi[startI][startJ] = null;
            this.xq.repaint();

            JOptionPane.showMessageDialog(this.xq, GetStr.back_Strings(24), GetStr.back_Strings(15),
                    JOptionPane.INFORMATION_MESSAGE);

            this.xq.cat.tiaoZhanZhe = null;
            this.xq.color = 0;
            this.xq.caiPan = false;
            this.xq.next();

            this.xq.jtfHost.setEnabled(false);
            this.xq.jtfPort.setEnabled(false);
            this.xq.jtfNickName.setEnabled(false);
            this.xq.jbConnect.setEnabled(false);
            this.xq.jbDisconnect.setEnabled(true);
            this.xq.jbChallenge.setEnabled(true);
            this.xq.jbYChallenge.setEnabled(false);
            this.xq.jbNChallenge.setEnabled(false);
            this.xq.jbFail.setEnabled(false);

            jiang1_i = 4;
            jiang1_j = 0;
            jiang2_i = 4;
            jiang2_j = 9;

        } else {

            qiZi[endI][endJ] = qiZi[startI][startJ];
            qiZi[startI][startJ] = null;
            this.xq.repaint();

            if (qiZi[endI][endJ].getName().equals("shuai")) {
                jiang1_i = endI;
                jiang1_j = endJ;
            } else if (qiZi[endI][endJ].getName().equals("jiang")) {
                jiang2_i = endI;
                jiang2_j = endJ;
            }
            if (jiang1_i == jiang2_i) {
                int count = 0;
                for (int jiang_j = jiang1_j + 1; jiang_j < jiang2_j; jiang_j++) {
                    if (qiZi[jiang1_i][jiang_j] != null) {
                        count++;
                        break;
                    }
                }
                this.xq.caiPan = true;
                if (count == 0) {
                    JOptionPane.showMessageDialog(this.xq, GetStr.back_Strings(23),
                    		GetStr.back_Strings(15), JOptionPane.INFORMATION_MESSAGE);
                    this.xq.cat.tiaoZhanZhe = null;

                    this.xq.color = 0;
                    this.xq.caiPan = false;
                    this.xq.next();

                    this.xq.jtfHost.setEnabled(false);
                    this.xq.jtfPort.setEnabled(false);
                    this.xq.jtfNickName.setEnabled(false);
                    this.xq.jbConnect.setEnabled(false);
                    this.xq.jbDisconnect.setEnabled(true);
                    this.xq.jbChallenge.setEnabled(true);
                    this.xq.jbYChallenge.setEnabled(false);
                    this.xq.jbNChallenge.setEnabled(false);
                    this.xq.jbFail.setEnabled(false);

                    jiang1_i = 4;
                    jiang1_j = 0;
                    jiang2_i = 4;
                    jiang2_j = 9;
                }
            }
        }
        
        this.xq.repaint();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void smallLine(Graphics2D g, int i, int j) {

        int x = 110 + 60 * i;
        int y = 80 + 60 * j;

        if (i > 0) {
            g.drawLine(x - 3, y - 3, x - 20, y - 3);
            g.drawLine(x - 3, y - 3, x - 3, y - 20);
        }
        if (i < 8) {
            g.drawLine(x + 3, y - 3, x + 20, y - 3);
            g.drawLine(x + 3, y - 3, x + 3, y - 20);
        }
        if (i > 0) {
            g.drawLine(x - 3, y + 3, x - 20, y + 3);
            g.drawLine(x - 3, y + 3, x - 3, y + 20);
        }
        if (i < 8) {
            g.drawLine(x + 3, y + 3, x + 20, y + 3);
            g.drawLine(x + 3, y + 3, x + 3, y + 20);
        }
    }
    
    public final int BOARD_WIDTH = 9, BOARD_HEIGHT = 10;
    public Map<String, Piece> pieces;
    public char player = 'r';
    private Piece[][] cells = new Piece[BOARD_HEIGHT][BOARD_WIDTH];//Make a board.

    public boolean isInside(int[] position) {
        return isInside(position[0], position[1]);
    }

    public boolean isInside(int x, int y) {
        return !(x < 0 || x >= BOARD_HEIGHT
                || y < 0 || y >= BOARD_WIDTH);
    }

    public boolean isEmpty(int[] position) {
        return isEmpty(position[0], position[1]);
    }

    public boolean isEmpty(int x, int y) {
        return isInside(x, y) && cells[x][y] == null;
    }


    public boolean update(Piece piece) {//Update the view of board.
        int[] pos = piece.position;
        cells[pos[0]][pos[1]] = piece;
        return true;
    }

    public Piece updatePiece(String key, int[] newPos) {//Update the basic element.
        Piece orig = pieces.get(key);//A piece's name(key),get the basic element. Key include color, character, and index. 
        //棋子名字(键)获取基本元(值) key其实就是name包含颜色种类和哪一个(也是键)
        Piece inNewPos = getPiece(newPos);//Get the new pos's basic element. 
        //获得新位置的棋盘中的位置的基本元
        /* If the new slot has been taken by another piece, then it will be killed.*/
        if (inNewPos != null)//not empty, eaten!
            pieces.remove(inNewPos.key);
        /* Clear original slot and updatePiece new slot.*/
        int[] origPos = orig.position;
        cells[origPos[0]][origPos[1]] = null;
        cells[newPos[0]][newPos[1]] = orig;
        orig.position = newPos;
        player = (player == 'r') ? 'b' : 'r';
        return inNewPos;//Just show the position of a piece is changed, but the true position is in original Pos. 
        //只是把棋盘显示的某个棋子的位置变了，当前再查询此棋子还在原来的位置
    }

    public boolean backPiece(String key) {//Because this is imitating a match, so when we get the best, we should get it back.
    	//把某个棋子认为自己在哪里的更新到棋盘显示 绘制回去 因为在模拟走棋
        int[] origPos = pieces.get(key).position;
        cells[origPos[0]][origPos[1]] = pieces.get(key);
        return true;
    }

    public Piece getPiece(int[] pos) {
        return getPiece(pos[0], pos[1]);
    }

    public Piece getPiece(int x, int y) {
        return cells[x][y];
    }
        private static final int VIEW_WIDTH = 700, VIEW_HEIGHT = 712;
        private static final int PIECE_WIDTH = 67, PIECE_HEIGHT = 67;
        private static final int SY_COE = 68, SX_COE = 68;
        private static final int SX_OFFSET = 50, SY_OFFSET = 15;
        private Map<String, JLabel> pieceObjects = new HashMap<String, JLabel>();
        private QiPan board;
        private String selectedPieceKey;
        private JFrame frame;
        private JLayeredPane pane;
        private GameController controller;
        private JLabel lblPlayer;

        public QiPan(GameController gameController) {
            this.controller = gameController;
        }

        public void init(final QiPan board) {
            this.board = board;
            frame = new JFrame(GetStr.back_Strings(0));
            frame.setIconImage(new ImageIcon("res/img/icon.png").getImage());
            frame.setSize(VIEW_WIDTH, VIEW_HEIGHT + 40);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            pane = new JLayeredPane();
            frame.add(pane);

            /* Initialize chess board and listeners on each slot.*/
            JLabel bgBoard = new JLabel(new ImageIcon("res/img/board.png"));
            bgBoard.setLocation(0, 0);
            bgBoard.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            bgBoard.addMouseListener(new BoardClickListener());
            pane.add(bgBoard, 1);

            /* Initialize player image.*/
            lblPlayer = new JLabel(new ImageIcon("res/img/r.png"));
            lblPlayer.setLocation(10, 320);
            lblPlayer.setSize(PIECE_WIDTH, PIECE_HEIGHT);
            pane.add(lblPlayer, 0);

            /* Initialize chess pieces and listeners on each piece.*/
            Map<String, Piece> pieces = board.pieces;
            for (Map.Entry<String, Piece> stringPieceEntry : pieces.entrySet()) {
                String key = stringPieceEntry.getKey();
                int[] pos = stringPieceEntry.getValue().position;
                int[] sPos = modelToViewConverter(pos);
                JLabel lblPiece = new JLabel(new ImageIcon("res/img/" + key.substring(0, 2) + ".png"));

                lblPiece.setLocation(sPos[0], sPos[1]);
                lblPiece.setSize(PIECE_WIDTH, PIECE_HEIGHT);
                lblPiece.addMouseListener(new PieceOnClickListener(key));
                pieceObjects.put(stringPieceEntry.getKey(), lblPiece);
                pane.add(lblPiece, 0);
            }
            frame.setVisible(true);
        }


        public void movePieceFromModel(String pieceKey, int[] to) {
            JLabel pieceObject = pieceObjects.get(pieceKey);
            int[] sPos = modelToViewConverter(to);
            pieceObject.setLocation(sPos[0], sPos[1]);

            /* Clear 'from' and 'to' info on the board */
            selectedPieceKey = null;
        }

        public void movePieceFromAI(String pieceKey, int[] to) {
            Piece inNewPos = board.getPiece(to);
            if (inNewPos != null) {
                pane.remove(pieceObjects.get(inNewPos.key));
                pieceObjects.remove(inNewPos.key);
            }

            JLabel pieceObject = pieceObjects.get(pieceKey);
            int[] sPos = modelToViewConverter(to);
            pieceObject.setLocation(sPos[0], sPos[1]);

            /* Clear 'from' and 'to' info on the board */
            selectedPieceKey = null;
        }

        private int[] modelToViewConverter(int pos[]) {
            int sx = pos[1] * SX_COE + SX_OFFSET, sy = pos[0] * SY_COE + SY_OFFSET;
            return new int[]{sx, sy};
        }

        private int[] viewToModelConverter(int sPos[]) {
            /* To make things right, I have to put an 'additional sy offset'. God knows why. */
            int ADDITIONAL_SY_OFFSET = 25;
            int y = (sPos[0] - SX_OFFSET) / SX_COE, x = (sPos[1] - SY_OFFSET - ADDITIONAL_SY_OFFSET) / SY_COE;
            return new int[]{x, y};
        }

        public void showPlayer(char player) {
            lblPlayer.setIcon(new ImageIcon("res/img/" + player + ".png"));
            //frame.setVisible(true);
        }

        public void showWinner(char player) {
            JOptionPane.showMessageDialog(null, (player == 'r') ? GetStr.back_Strings(23) : GetStr.back_Strings(25), GetStr.back_Strings(26), JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        class PieceOnClickListener extends MouseAdapter {
            private String key;

            PieceOnClickListener(String key) {
                this.key = key;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (selectedPieceKey != null && key.charAt(0) != board.player) {
                    int[] pos = board.pieces.get(key).position;
                    int[] selectedPiecePos = board.pieces.get(selectedPieceKey).position;
                    /* If an enemy piece already has been selected.*/
                    for (int[] each : GuiZe.getNextMove(selectedPieceKey, selectedPiecePos, board)) {
                        if (Arrays.equals(each, pos)) {
                            // Kill self and move that piece.
                            pane.remove(pieceObjects.get(key));
                            pieceObjects.remove(key);
                            controller.moveChess(selectedPieceKey, pos, board);
                            movePieceFromModel(selectedPieceKey, pos);
                            break;
                        }
                    }
                } else if (key.charAt(0) == board.player) {
                    /* Select the piece.*/
                    selectedPieceKey = key;
                }
            }
        }

        class BoardClickListener extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                if (selectedPieceKey != null) {
                    int[] sPos = new int[]{e.getXOnScreen() - frame.getX(), e.getYOnScreen() - frame.getY()};
                    int[] pos = viewToModelConverter(sPos);
                    int[] selectedPiecePos = board.pieces.get(selectedPieceKey).position;
                    for (int[] each : GuiZe.getNextMove(selectedPieceKey, selectedPiecePos, board)) {
                        if (Arrays.equals(each, pos)) {
                            controller.moveChess(selectedPieceKey, pos, board);
                            movePieceFromModel(selectedPieceKey, pos);
                            break;
                        }
                    }
                }
            }
     }
}
class Piece implements Cloneable {
	public String key;
	public char color;
	public char character;
	public char index;
	public int[] position;
		
	public Piece(String name, int[] position) {//Every point's basic element.
	    this.key = name;
	    this.color = name.charAt(0);//Color means player.
	    this.character = name.charAt(1);//Character 
	    this.index = name.charAt(2);//Which one
	    this.position = position;//position
	}
}