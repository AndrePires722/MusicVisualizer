

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Action1 implements ActionListener {        
	public void actionPerformed (ActionEvent e) {   
		if(Window.player.clip.isOpen())
			Window.player.stop();
		 Window.player.setSong(Action3.fileIndex.getFile((String)Window.list.getSelectedValue()));
		 Window.player.play();
		 try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//new DecodeAudioToOutput();
	}
}
