package zwlhw.main.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
/***
 * 音乐播放器类
 * @author lt
 *  time 2016-7-5
 */
 //继承自线程类Thread
public class Music extends Thread{
    Player player;
    File music;
    boolean loop=false;
    //构造方法
    public Music(File file,boolean loop) {
        this.music = file;
        this.loop=loop;
    }
    //重写run方法
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
    //播放方法
    public void play() throws FileNotFoundException, JavaLayerException {

            BufferedInputStream buffer =
                    new BufferedInputStream(new FileInputStream(music));
            player = new Player(buffer);
            player.play();
    }
}