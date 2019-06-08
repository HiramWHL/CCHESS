package zwlhw.main.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
/***
 * ���ֲ�������
 * @author lt
 *  time 2016-7-5
 */
 //�̳����߳���Thread
public class Music extends Thread{
    Player player;
    File music;
    boolean loop=false;
    //���췽��
    public Music(File file,boolean loop) {
        this.music = file;
        this.loop=loop;
    }
    //��дrun����
    @Override
    public void run() {
        super.run();
        try {
            play();   
            if(loop) {
            	Music player=new Music(music,loop);
        		player.start();
            }
        } catch (FileNotFoundException | JavaLayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //���ŷ���
    public void play() throws FileNotFoundException, JavaLayerException {

            BufferedInputStream buffer =
                    new BufferedInputStream(new FileInputStream(music));
            player = new Player(buffer);
            player.play();
    }
}