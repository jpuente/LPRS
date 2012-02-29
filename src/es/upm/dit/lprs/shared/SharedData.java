package es.upm.dit.lprs.shared;


/**
 * Example showing race condition in concurrent access to shared data.
 * @author jpuente
 */
public class SharedData {
	
	/**
	 * Shared data field.
	 */
	static long count = 0;
	
	/**
	 * Counters increment count a number of times.
	 * @author jpuente
	 */
	private static class Counter extends Thread {
		long n; 
		
		public Counter(long n) {
			this.n = n;
		}
		
		public void run() {
			for (long i = 0; i<n; i++)
				count++;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	  throws InterruptedException {
		int nThreads = Integer.parseInt(args[0]);
		long counting = Integer.parseInt(args[1]);
		System.out.println(nThreads + " threads counting " 
				+ counting +" times each" );
		Counter[] counterThread = new Counter[nThreads];
		for (int id = 0; id < nThreads; id++) {
			counterThread[id] = new Counter(counting);
			counterThread[id].start();
		}
		for (int id = 0; id < nThreads; id++) {
			counterThread[id].join();
		}
		System.out.print("Value of counter = " + count);
		System.out.println(" --- should be " + nThreads*counting);
	}

}
