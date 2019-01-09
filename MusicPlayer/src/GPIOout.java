import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GPIOout {
	GpioController gpio = GpioFactory.getInstance();
	GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "PinLED", PinState.HIGH);
	public GPIOout() throws InterruptedException {
		
		System.out.println("I am setup!");
		 // get a handle to the GPIO controller
    	
        
        // creating the pin with parameter PinState.HIGH
        // will instantly power up the pin
        
        
        
        // release the GPIO controller resources
        // gpio.shutdown();
	}
	public void pulse(int dur, boolean idk) {
		pin.pulse(dur,idk);
	}
	public void test() {
		for(int i = 0;i<100	;i++) {
			pulse(10,true);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
