
import java.io.*;
import javax.sound.sampled.*;
import javax.sound.sampled.Line.Info;

public class MusicPlayer {
	String musicFile;
	AudioInputStream stream;
	AudioFormat format;
	DataLine.Info info;
	long timeStarted;
	static Clip clip;
	boolean streaming = false;
	public static TargetDataLine dataline;
	public static Line inputLine;

	public MusicPlayer(String savefile) throws LineUnavailableException {
		musicFile = savefile;
		info = new DataLine.Info(Clip.class, format);
		clip = (Clip) AudioSystem.getLine(info);
	}

	public void play() {
		try {
			File yourFile = new File(musicFile);
			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			Window.label1.setText("Selected Song: " + musicFile.substring(musicFile.lastIndexOf('\\') + 1));
			clip.open(stream);
			
			timeStarted = System.currentTimeMillis();
			clip.start();
			System.out.println("Started");
		} catch (Exception e) {
			System.out.println("No song selected");
		}
	}
	public void stream() {
		try {
			musicFile = Action3.fileIndex.getFile("breathe");
			stream = AudioSystem.getAudioInputStream(new File(musicFile));
			format = stream.getFormat();
			Mixer.Info[] infolist = AudioSystem.getMixerInfo();
			Mixer thisone = null;
			for(Mixer.Info fff : infolist) {
				System.out.println(fff.getName());
			}
			for(int i = 0; i<infolist.length;i++) {
				if(infolist[i].getName().toLowerCase().contains("microphone")) {
					System.out.println("chose "+i);
					thisone = AudioSystem.getMixer(infolist[i]);
					i = infolist.length;
				}
			}
			if(thisone == null)return;
			
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			dataline = (TargetDataLine) thisone.getLine(info);
			dataline.open(format,1024);
			dataline.start();
			stream = new AudioInputStream(dataline);
			streaming = true;
			//clip.open(stream);
			//System.out.println("its been an honor");
			//clip.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSong(String fileName) {
		musicFile = fileName;
	}

	public void stop() {
		clip.close();
	}

	public void pause() throws InterruptedException {
		clip.wait();
	}
}