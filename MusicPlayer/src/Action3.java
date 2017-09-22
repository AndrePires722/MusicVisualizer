
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Action3 implements ActionListener {        
	public static FileIndexer fileIndex = new FileIndexer();
	public void actionPerformed (ActionEvent e) {     
		
		if(Window.player.clip.isOpen())
			Window.player.stop();
		Window.shuffled = true;
		Window.player.setSong(fileIndex.getRandom());
		Window.player.play();
		Window.autoPlay = true;
		
	}
}
