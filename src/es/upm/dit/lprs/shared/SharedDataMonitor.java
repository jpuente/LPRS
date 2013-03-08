package es.upm.dit.lprs.shared;

/**
 * Example showing concurrent access to shared data with mutual exclusion
 * implemented by a monitor.
 * @author jpuente
 */public class SharedDataMonitor {


	 /**
	  * Shared data field is encapsulated in a monitor.
	  */
	 private static class Count {
		 long count = 0;

		 public synchronized void Increment (){
			 count++;
		 }

		 public synchronized long Value() {
			 return count;
		 }
	 }


	 /**
	  * Counters increment a count a number of times.
	  * @author jpuente
	  */
	 private static class Counter extends Thread {
		 Count c;
		 long n; 

		 public Counter(Count c, long n) {
			 this.c = c;
			 this.n = n;
		 }

		 public void run() {
			 for (long i = 0; i<n; i++)
				 c.Increment();
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
		 
		 Count count  = new Count();
		 Counter[] counterThread = new Counter[nThreads];
		 for (int id = 0; id < nThreads; id++) {
			 counterThread[id] = new Counter(count, counting);
			 counterThread[id].start();
		 }
		 for (int id = 0; id < nThreads; id++) {
			 counterThread[id].join();
		 }
		 System.out.print("Value of counter = " + count.Value());
		 System.out.println(" --- should be " + nThreads*counting);
	 }

 }
