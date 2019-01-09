

public class AutomaticSongPlayer implements Runnable {

	@Override
	public void run() {
		System.out.println("Starting AutomaticSongPlayer");
		while(true){
			//since this is always looping, might as well do some other irrelevant processing
			
			//System.out.println("AutomaticSongPlayer checking...");
			if(Window.autoPlay){
				
				if(!Window.player.clip.isOpen()){
					
					Window.player.stop();

				}
				if(!Window.player.clip.isRunning()){
					Window.player.stop();
					if(Window.shuffled){
						System.out.println("Shuffled");
						Window.player.setSong(Action3.fileIndex.getRandom());
						
					}
					else{
						
						String next = Action3.fileIndex.getNext();
						System.out.println("Regular " + next);
						Window.player.setSong(next);
						System.out.println();
					}
					Window.player.play();
				}
				
			}
			if(Window.player.streaming) {
				
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// TODO Auto-generated method stub
		
		
	}
	

}
