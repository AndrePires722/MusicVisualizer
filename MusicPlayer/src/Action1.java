
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;

class Action1 implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (Window.player.clip.isOpen())
			Window.player.stop();
		if (((String) Window.list.getSelectedValue()).equals("~Test:#j9034h9wg")) {
			Window.player.stream();

		} else {
			Window.player.setSong(Action3.fileIndex.getFile((String) Window.list.getSelectedValue()));
			Window.player.play();
		}

		// new DecodeAudioToOutput();
	}
}
