
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Action4 implements ActionListener {        
	
	public void actionPerformed (ActionEvent e) {   
		if(Window.player.clip.isOpen())
			Window.player.stop();
		 if(Window.shuffled){
			 Window.player.setSong(Action3.fileIndex.getRandom());
		 }else{
			 Window.player.setSong(Action3.fileIndex.getNext());
		 }
		 Window.player.play();
		 Window.autoPlay = true;
	}
}
