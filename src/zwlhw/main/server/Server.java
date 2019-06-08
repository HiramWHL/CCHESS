package zwlhw.main.server;

import javax.swing.*;

import zwlhw.main.client.StringIndex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.util.Vector;

/**
 * Server provide server in net
 * */

public class Server extends JFrame implements ActionListener {
	
	static StringIndexServer GetStr= new StringIndexServer();

    JLabel jlPort = new JLabel(GetStr.back_Strings(1));
    JTextField jtfPort = new JTextField("6464");
    JButton jbStart = new JButton(GetStr.back_Strings(2));
    JButton jbStop = new JButton(GetStr.back_Strings(3));
    JPanel jps = new JPanel();
    JList jlUserOnline = new JList();

    JScrollPane jspx = new JScrollPane(jlUserOnline);
    JSplitPane jspz = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspx, jps);

    ServerSocket ss;
    ServerThread st;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //��ȡ��Ļ�ĳߴ�
    int screenWidth = screenSize.width;      //��ȡ��Ļ�Ŀ�
    int screenHeight = screenSize.height;       //��ȡ��Ļ�ĸ�

    Vector onlineList = new Vector();//������ŵ�ǰ�����û���Vector����

    public Server() {

        this.initialComponent();

        this.addListener();

        this.initialFrame();
    }

    public static void main(String args[]) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	try {
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	} catch (Exception e) {
    		
    	}
        new Server();
    }

    public void initialComponent() {

        jps.setLayout(null);
        jlPort.setBounds(20, 20, 50, 20);
        jps.add(jlPort);

        this.jtfPort.setBounds(85, 20, 60, 20);
        jps.add(this.jtfPort);

        this.jbStart.setBounds(18, 50, 60, 20);
        jps.add(this.jbStart);

        this.jbStop.setBounds(85, 50, 60, 20);
        jps.add(this.jbStop);

        this.jbStop.setEnabled(false);
    }

    public void addListener() {

        this.jbStart.addActionListener(this);
        this.jbStop.addActionListener(this);
    }

    public void initialFrame() {

        this.setTitle(GetStr.back_Strings(0));
        Image image = new ImageIcon("./img/icon.png").getImage();
        this.setIconImage(image);
        this.add(jspz);

        jspz.setDividerLocation(250);
        jspz.setDividerSize(4);

        this.setBounds((screenWidth - 420)/2, (screenHeight - 320)/2, 420, 320);
        this.setVisible(true);

        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        if (st == null)//���������߳�Ϊ��ʱֱ���˳�
                        {
                            System.exit(0);//�˳�
                            return;
                        }
                        try {
                            Vector v = onlineList;
                            int size = v.size();
                            for (int i = 0; i < size; i++) {
                                //����Ϊ��ʱ���������û�����������Ϣ
                                ServerAgentThread tempSat = (ServerAgentThread) v.get(i);

                                tempSat.dout.writeUTF("<#SERVER_DOWN#>");
                                tempSat.flag = false;//��ֹ�����������߳�
                            }

                            st.flag = false;//��ֹ�������߳�
                            st = null;
                            ss.close();
                            v.clear();
                            refreshList();

                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                        System.exit(0);
                    }
                }
        );
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbStart) {
            this.jbStart_event();
        } else if (e.getSource() == this.jbStop) {
            this.jbStop_event();
        }
    }

    public void jbStart_event() {

        int port = 0;
        try {

            port = Integer.parseInt(this.jtfPort.getText().trim());
        } catch (Exception ee) {
            JOptionPane.showMessageDialog(this, GetStr.back_Strings(4), GetStr.back_Strings(5),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (port > 65535 || port < 0) {
            JOptionPane.showMessageDialog(this, GetStr.back_Strings(6), GetStr.back_Strings(5),
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            this.jbStart.setEnabled(false);
            this.jtfPort.setEnabled(false);
            this.jbStop.setEnabled(true);

            ss = new ServerSocket(port);
            st = new ServerThread(this);
            st.start();

            JOptionPane.showMessageDialog(this, GetStr.back_Strings(7), GetStr.back_Strings(8),
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ee) {

            JOptionPane.showMessageDialog(this, GetStr.back_Strings(9), GetStr.back_Strings(5),
                    JOptionPane.ERROR_MESSAGE);

            this.jbStart.setEnabled(true);
            this.jtfPort.setEnabled(true);
            this.jbStop.setEnabled(false);

        }
    }

    public void jbStop_event() {

        try {

            Vector v = onlineList;
            int size = v.size();
            for (int i = 0; i < size; i++) {

                ServerAgentThread tempSat = (ServerAgentThread) v.get(i);
                tempSat.dout.writeUTF("<#SERVER_DOWN#>");
                tempSat.flag = false;
            }

            st.flag = false;//�رշ������߳�
            st = null;
            ss.close();//�ر�ServerSocket

            v.clear();//�������û��б����
            refreshList();

            this.jbStart.setEnabled(true);
            this.jtfPort.setEnabled(true);
            this.jbStop.setEnabled(false);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void refreshList() {

        Vector v = new Vector();
        int size = this.onlineList.size();
        for (int i = 0; i < size; i++) {
            ServerAgentThread tempSat = (ServerAgentThread) this.onlineList.get(i);
            String temps = tempSat.sc.getInetAddress().toString();
            temps = temps + "|" + tempSat.getName();
            v.add(temps);
        }
        this.jlUserOnline.setListData(v);
    }
}