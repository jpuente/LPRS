package es.upm.dit.lprs.buffer;

/**
 * @author jpuente
 * @version 20120320
 */
public class Control {
	private boolean parado = true;

	public synchronized void parar() {
		parado = true;
		notifyAll();
	}

	public synchronized void arrancar() {
		parado = false;
		notifyAll();
	}
	
	public synchronized void esperar() 
			throws InterruptedException {
		while (parado) wait();
	}

}
