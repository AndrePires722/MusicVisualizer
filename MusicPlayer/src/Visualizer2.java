import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Visualizer2 extends WaveformGenerator {
	int[] energy = new int[1024 + 50];
	int[] average = new int[1024 + 50];
	boolean[] beats = new boolean[1024 + 50];
	int t = 0;
	boolean isBeat = false;
	private int windowsize = 43;
	Color custom;
	public Visualizer2() {
		for (int i = 0; i < beats.length; i++) {
			beats[i] = false;
		}
	}

	@Override
	public void run() {
		while (true)
			this.repaint();

	}

	public void updateEnergy() {
		for (int i = 0; i < energy.length - 1; i++) {
			energy[i] = energy[i + 1];
			average[i] = average[i + 1];
		}
		energy[energy.length - 1] = 0;
		for (int i = 0; i < beats.length - 1; i++) {
			beats[i] = beats[i + 1];
			beats[i] = beats[i + 1];
		}
		beats[beats.length - 1] = false;
		for (int i = 0; i < WaveformGenerator.waveform.length; i++) {
			energy[energy.length - 1] += ((int) WaveformGenerator.waveform[i] * (int) WaveformGenerator.waveform[i]);

		}
		average[average.length - 1] = 0;

		for (int i = 0; i < windowsize; i++) {
			average[average.length - 1] += energy[energy.length - 1 - i];
		}
		average[average.length - 1] /= windowsize;

		// if (scale(energy[energy.length - 1]) > scale(average[average.length - 1]) *
		// 1.25&&isPeak()) {
		if (isBeat()&&isPeak()) {
			beats[beats.length - 1] = true;
		} 
		updateLED();
		repaint();
	}
	public void updateLED() {
		if(!Window.player.clip.isRunning())return;
		int b = 2*(int) (scale(energy[energy.length-1-50])/getHeight()*100);
		int g = b-255;
		if(g<0) g = 0;
		if(g>255)g = 255;
		if(b>=256)b=255;
		int r = 255-b;
		//DecimalFormat f = new DecimalFormat("000");
		//System.out.printf("R:%s,G:%s,B:%s%n",f.format(r),f.format(g),f.format(b));
		Color custom = new Color(r,g,b);
		if(beats[beats.length-1-50]&&b>125)custom = Color.WHITE;
		
	}
	public boolean isPeak() {
		for (int i = 1; i < (20 - ((average[average.length - 1]) / 1000000.0)); i++) {

			if ((energy[energy.length - 1]) < energy[energy.length - (i + 1)])
				return false;
		}
		for (int i = 1; i < (15 - ((average[average.length - 1]) / 1000000.0)); i++) {
			beats[beats.length - 1 - i] = false;
		}
		return true;
	}

	public double getVar() {
		double var = 0;
		for (int i = 0; i < windowsize; i++) {
			var += Math.pow(average[average.length - 1] - energy[energy.length - 1 - i], 2);
		}
		var /= windowsize;
		return var;
	}

	public boolean isBeat() {
		double c = (-0.0000015) * getVar() + 1.5142857;
		return (energy[energy.length - 1] > c * average[average.length-1]);
	}

	public double scale(double d) {
		double x = d;
		x /= 1000000.0;

		return Math.pow(x, 3) * getHeight() / (5 * 5 * 5);
		// return Math.log(x)*getHeight()/Math.log(5);

	}

	@Override
	public void paintComponent(Graphics g) {
		double offset = getWidth() / (double) (energy.length - 1);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 1; i < energy.length - 1; i++) {
			g.setColor(Color.GREEN);
			g.drawLine((int) (offset * (i - 1)), (int) -(scale(energy[i - 1])) + getHeight(), (int) (offset * i),
					(int) -(scale(energy[i])) + getHeight());
			g.setColor(Color.white);
			if (beats[i])
				g.drawLine((int) (offset * i), 0, (int) (offset * i), 10);
		}

		g.setColor(Color.BLUE);

		for (int i = 1; i < energy.length - 1; i++) {
			g.drawLine((int) (offset * (i - 1)), (int) -(scale(average[i - 1])) + getHeight(), (int) (offset * i),
					(int) -(scale(average[i])) + getHeight());
		}

		g.setColor(Color.WHITE);
		g.drawString("Beat Detection", 10, 20);
		if(!Window.player.clip.isRunning())return;
		else g.setColor(custom);
		g.fillRect(100, 75, 50, 50);
	}

}
