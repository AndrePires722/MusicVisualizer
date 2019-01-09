
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JPanel;

public class Visualizer1 extends WaveformGenerator {
	@Override
	public void run() {

		while (true) {
			this.repaint();
		}
	}
	public double output(int length) {
		return Math.log10(length);
		//return length;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Complex[] t = new Complex[waveform.length];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Complex(waveform[i], 0);
		}

		t = Complex.fft(t);
		double[] squared = new double[t.length / 2];
		// 10 octaves - 0 to 65.7*2^10
		int[] barRepresentation = new int[10];
		double octave = 65.7;
		
		for (int i = 0; i < t.length / 2; i++) {
			squared[i] = Math.hypot(t[i].re(), t[i].im());
			if(squared[i] < 18);
			else if (squared[i] < octave)
				barRepresentation[0]++;
			else if (squared[i] < octave * Math.pow(2, 1))
				barRepresentation[1]++;
			else if (squared[i] < octave * Math.pow(2, 2))
				barRepresentation[2]++;
			else if (squared[i] < octave * Math.pow(2, 3))
				barRepresentation[3]++;
			else if (squared[i] < octave * Math.pow(2, 4))
				barRepresentation[4]++;
			else if (squared[i] < octave * Math.pow(2, 5))
				barRepresentation[5]++;
			else if (squared[i] < octave * Math.pow(2, 6))
				barRepresentation[6]++;
			else if (squared[i] < octave * Math.pow(2, 7))
				barRepresentation[7]++;
			else if (squared[i] < octave * Math.pow(2, 8))
				barRepresentation[8]++;
			else
				barRepresentation[9]++;
		}
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);
		// System.out.println(max);
		
		double output = output(waveform.length);
		double scalar = getHeight()/output;
	
		for (int i = 0; i < barRepresentation.length; i++) {
			double scalar2 = output(barRepresentation[i])*scalar;
			if (barRepresentation[i] > 1) {
				Color c = new Color(0,0,0);
				c = new Color(
						((int)(c.getRed()+scalar2*256/getHeight()))%256,(int)
						(c.getGreen()+scalar2*256/getHeight())%256,(int)
						(c.getBlue()+scalar2*256/getHeight())%256);
				g.setColor(c);
				g.fillRect(i * getWidth() / barRepresentation.length + 7, getHeight() - (int) (scalar2),
						getWidth() / 10 - 7, (int) (scalar2));
				
			}
		}
		g.setColor(Color.WHITE);
		g.drawString("Fast Fourier Transform Algorithim [FREQ BAR GRAPH]", 10, 20);
	}
}
