package cluedo_game;

import javax.sound.sampled.*;
import java.io.File;
import java.net.URL;

public class LoopSound implements Runnable {
    static boolean playSong = true;
    static SourceDataLine line = null;
    static Thread t;

    public LoopSound() {}

    public void play() {
        t = new Thread(this);
        t.start();
    }

    public void restartMusic() {
        try {
            t.join();
        } catch (Exception e) { e.printStackTrace(); }
    }

//    public static void turnMusicOff() {
//        //	line.stop();
//		playSong = false;
//		t.interrupt();
//    }

    public static void turnMusicOff() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                line.stop();
            }
        });
        t.start();
        playSong = false;
        GameLogic.music = false;
    }

    public static void turnMusicOn() {
        GameLogic.playMusic(true);
        playSong = true;
    }

    @Override
    public void run() {
        String fileLocation = "/music/start.wav";
        try {
            while (playSong) {
                playSound(fileLocation);
                assert playSong;
            }
        } catch (Exception e) { e.printStackTrace(); }

    }

    protected void playSound(String fileName) throws Exception {
        URL streamURL = this.getClass().getResource(fileName);
        AudioInputStream stream = AudioSystem.getAudioInputStream(streamURL);

        AudioFormat format = stream.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);

        line.start();
        int size = 0;
        byte[] bytes = new byte[131072];
        while (size != -1) {
            size = stream.read(bytes, 0, bytes.length);

            if (size >= 0)
                line.write(bytes, 0, size);
        }

        line.drain();
        line.close();
    }
}
