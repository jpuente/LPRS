package es.upm.dit.lprs.buffer;

/**
 * @author jpuente
 * @version 20120320
 */
public class ProductorConsumidor {

	public static void main(String[] args) {
		Buffer<String> buffer = new BufferMultiple<String>(10);
		Productor productor1 = new Productor("p1", buffer, 1000);
		Productor productor2 = new Productor("p2", buffer, 2000);
		Consumidor consumidor = new Consumidor("c", buffer);
		productor1.start();
		productor2.start();
		consumidor.start();
	}
}
