import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JPanel;

import javax.swing.Timer;

public class WaveformGenerator extends JPanel implements ActionListener,Runnable {
	private Thread animator;
	static byte[] waveform;
	Scanner scanner;
	int index = 0;
	Scanner sc;
	Timer t;
	int max = 0,min = 0;
	public WaveformGenerator() {

		waveform = new byte[128];
		for (int i = 0; i < waveform.length; i++) {
			waveform[i] = 0;
		}
		//setSize(1200, 300);

		setVisible(true);
		repaint();
	}
	@Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
	@Override
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Window.player.clip.isActive()) {
			int totalFramesRead = 0;
			File fileIn = new File(Window.player.musicFile);
		
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
				int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
				if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
					// some audio formats may have unspecified frame size
					// in that case we may read any amount of bytes
					bytesPerFrame = 1;
				}
				int persecond = (int) Window.player.clip.getFormat().getSampleRate();
				//int numBytes = (int)(persecond * (5/1000.0) * bytesPerFrame)
				
				int numBytes = 2048;
				
				waveform = new byte[numBytes];
				
				try {
					int numBytesRead = 0;
					int numFramesRead = 0;
					// Try to read numBytes bytes from the file.
					while ((numBytesRead = audioInputStream.read(waveform)) != -1) {
						// Calculate the number of frames actually read.
						numFramesRead = numBytesRead / bytesPerFrame;
						totalFramesRead += numFramesRead;
						//apply transformation to data
						/*if((numBytes*indexes.size())!=0)
						System.out.println(persecond*indexes.size()/(numBytes));*/
						
						repaint();
						if(!Window.player.clip.isOpen())break;
						while(totalFramesRead>Window.player.clip.getFramePosition()) {
							//System.out.printf("%d %d%n", Window.player.clip.getMicrosecondPosition(),totalFramesRead);
						}
					}
					System.out.println(audioInputStream.getFormat().getChannels());
				} catch (Exception ex) {
					// Handle the error...
					ex.printStackTrace();
				}
			} catch (Exception ep) {
				// Handle the error...
				ep.printStackTrace();
			}
			/*
			 * System.out.println(Window.player.stream.getFrameLength());
			 * System.out.println(Window.player.stream.getFormat().getSampleRate());
			 */
			repaint();
		}}
	}
	@Override
	public void paintComponent(Graphics g) {
		double offset = getWidth() / (double) waveform.length;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);
		
		for (int i = 1; i < waveform.length; i++) {
			g.drawLine((int) (offset * (i - 1)), (int) (waveform[i - 1]*getHeight()/256.0) + getHeight() / 2, (int) (offset * i),
					(int) (waveform[i]*getHeight()/256.0) + getHeight() / 2);
		}
		
		//g.setColor(Color.WHITE);
		//g.drawString("Waveform", 10, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		run();

	}
}
