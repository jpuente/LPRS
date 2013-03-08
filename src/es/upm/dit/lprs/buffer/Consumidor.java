package es.upm.dit.lprs.buffer;

import java.util.Random;

/**
 * @author jmanas
 * @author jpuente
 * @version 20130228
 */
public class Consumidor extends Thread {

	private Buffer<String> buffer; 

	private Control control = new Control();
    private LogWindow logWindow;
	private final Random random = new Random();

	public Consumidor(String id, Buffer<String> buffer) {
		setName(id);
		this.buffer = buffer;
        logWindow = new LogWindow(this);
	}
	
	public void arrancar() {
		control.arrancar();
	}
	
	public void parar() {
		control.parar();
	}

	public void run() {
		try {
			while (true) { 
				control.esperar();
				String msg = buffer.recibir(); 
				logWindow.println("Recibe " + msg);
                Thread.sleep(random.nextInt(2) * 1000);
			}
		} catch (InterruptedException e) {logWindow.println(e.toString());}
	}
}
