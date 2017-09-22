import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Visualizer2 extends WaveformGenerator {
	int[] energy = new int[waveform.length];
	int[] average = new int[waveform.length];
	
	@Override
	public void run() {
		while (true){
			this.repaint();
		}
	}
	public void updateEnergy() {
		for(int i = 0;i<energy.length-1;i++) {
			energy[i] = energy[i+1];
			average[i] = average[i+1];
		}
		energy[energy.length-1]=0;
		for(int i = 0;i<waveform.length;i++) {
			energy[energy.length-1] += ((int)waveform[i]*(int)waveform[i]);
			
		}
		average[average.length-1]=0;
		for(int i = 0;i<22;i++) {
			average[average.length-1]+=energy[energy.length-1-i];
		}
		average[average.length-1]/=22;
		
		
	}
	public double scale(double d) {
		double x = d;
		x/=1000000.0;
		return Math.pow(x,3)*getHeight()/(5*5*5);
	}
	@Override
	public void paintComponent(Graphics g) {
		double offset = getWidth() / (double) energy.length;
		if(scale(energy[energy.length-1])>scale(average[average.length-1])*1.4)
			g.setColor(Color.WHITE);
		else g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GREEN);
		updateEnergy();
		for (int i = 1; i < energy.length; i++) {
			g.drawLine((int) (offset * (i - 1)), (int) -(scale(energy[i-1])) + getHeight(), (int) (offset * i),
					(int) -(scale(energy[i])) + getHeight());
		}
		g.setColor(Color.BLUE);
		for (int i = 1; i < energy.length; i++) {
			g.drawLine((int) (offset * (i - 1)), (int) -(scale(average[i-1])) + getHeight(), (int) (offset * i),
					(int) -(scale(average[i])) + getHeight());
		}
		g.setColor(Color.WHITE);
		g.drawString("Beat Detection", 10, 20);
	}

}
