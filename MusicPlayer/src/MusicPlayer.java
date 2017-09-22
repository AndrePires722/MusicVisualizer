

import java.io.*;
import javax.sound.sampled.*;

public class MusicPlayer {
	String musicFile;
	AudioInputStream stream;
    AudioFormat format;
    DataLine.Info info;
    long timeStarted;
    static Clip clip;
	public MusicPlayer(String savefile) throws LineUnavailableException{
		musicFile = savefile;
		info = new DataLine.Info(Clip.class, format);
	    clip = (Clip) AudioSystem.getLine(info);
	}
	
	public void play(){
		try {
		    File yourFile = new File(musicFile);
		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    Window.label1.setText("Selected Song: "+musicFile.substring(musicFile.lastIndexOf('\\')+1));
		    clip.open(stream);
		    timeStarted = System.currentTimeMillis();
		    clip.start();
		}
		catch (Exception e) {
		    System.out.println("No song selected");
		}
	}
	public void setSong(String fileName){
		musicFile = fileName;
	}
	public void stop(){
		clip.close();
	}
	public void pause() throws InterruptedException{
		clip.wait();
	}
}