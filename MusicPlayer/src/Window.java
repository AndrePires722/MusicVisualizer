
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Window {
	public static MusicPlayer player;
	public static JLabel label1;
	public static JList list;
	public static String[] listData;
	public static boolean shuffled;
	public static boolean autoPlay;

	public static void main(String[] args) throws LineUnavailableException {
		player = new MusicPlayer("");
		System.out.println("yo");
		shuffled = false;
		autoPlay = false;
		JFrame w = new JFrame("Music Player");
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setResizable(true);
		w.setLayout(new GridLayout(0, 1));
		w.setSize(1000, 600);
		JPanel panel = new JPanel();

		JButton button = new JButton("Play");
		panel.add(button);
		button.addActionListener(new Action1());
		JButton button2 = new JButton("Stop");
		panel.add(button2);
		button2.addActionListener(new Action2());
		JButton button3 = new JButton("Shuffle");
		panel.add(button3);
		button3.addActionListener(new Action3());

		label1 = new JLabel("Welcome");
		panel.add(label1);

		// Create a new listbox control
		list = new JList(listData);
		panel.add(list);

		if (true)
			w.add(panel);
		else
			button3.doClick();

		(new Thread(new AutomaticSongPlayer())).start();
		w.add(new WaveformGenerator());
		// w.pack();
		w.add(new Visualizer1());
		w.add(new Visualizer2());
		w.setExtendedState(JFrame.MAXIMIZED_BOTH);
		w.setUndecorated(true);
		w.setVisible(true);

	}

}
