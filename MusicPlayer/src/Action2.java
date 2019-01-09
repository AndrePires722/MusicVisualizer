

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

class Action2 implements ActionListener {        
	
	public void actionPerformed (ActionEvent e) {    
		if(Window.player.clip.isOpen())
		Window.player.stop();
		else System.out.println("No active song to stop");
		Window.autoPlay = false;
		Window.player.streaming = false;
	}
}
