import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
/**
 * never actually used
 * 
 * exists if I need to decode a .wav file to its PDW values
 * @author piresa2
 *
 */
public class DecodeAudioToOutput implements Runnable {
	public DecodeAudioToOutput() {
		this.run();
	}
	
	@Override
	public void run() {
		int totalFramesRead = 0;
		File fileIn = new File(Window.player.musicFile);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter("C:\\Users\\piresa2\\Music\\data.txt"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// somePathName is a pre-existing string whose value was
		// based on a user selection.
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileIn);
			int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
			if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
				// some audio formats may have unspecified frame size
				// in that case we may read any amount of bytes
				bytesPerFrame = 1;
			}
			// Set an arbitrary buffer size of 1024 frames.
			int numBytes = 1024 * bytesPerFrame;
			byte[] audioBytes = new byte[numBytes];

			try {
				int numBytesRead = 0;
				int numFramesRead = 0;
				// Try to read numBytes bytes from the file.
				long temp = System.currentTimeMillis();
				while ((numBytesRead = audioInputStream.read(audioBytes)) != -1) {
					// Calculate the number of frames actually read.
					numFramesRead = numBytesRead / bytesPerFrame;
					totalFramesRead += numFramesRead;
					// Here, do something useful with the audio data that's
					// now in the audioBytes array...

					for (int i = 0; i < audioBytes.length; i++) {
						out.write(audioBytes[i]);
					}
				}
				long time = System.currentTimeMillis() - temp;
				System.out.println("done! took " + time + " milliseconds");
			} catch (Exception ex) {
				// Handle the error...
			}
		} catch (Exception ep) {
			// Handle the error...
		}
	}

}
