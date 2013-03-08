package es.upm.dit.lprs.buffer;

import java.util.Random;

/**
 * @author jmanas
 * @author jpuente
 * @version 20130228
 */
public class Productor extends Thread {

	private Buffer<String> buffer; 
	private int n;

	private Control control = new Control();
	private LogWindow logWindow;
	private final Random random = new Random();

	public Productor(String id, Buffer<String> buffer, int n0) {
		setName(id);
		this.buffer = buffer;
		n = n0;
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
				String msg = String.valueOf(n++);
				buffer.enviar(msg);  
				logWindow.println("Envía " + msg);
				Thread.sleep(random.nextInt(5) * 1000);
			} 
		} catch (InterruptedException e) {
			logWindow.println(e.toString());
		}
	}
}
