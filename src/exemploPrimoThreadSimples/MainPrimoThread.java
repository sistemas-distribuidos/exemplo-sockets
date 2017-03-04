package exemploPrimoThreadSimples;

import exemploUDPDistribuido.PrimoThread;


public class MainPrimoThread {

	public static void main(String[] args) throws InterruptedException {
	
		int n = new Integer(args[0]); // quantidade de threads
		int x1 = new Integer(args[1]);
		int x2 = new Integer(args[2]);
		
		getTotalPrimos(x1, x2, n);
		
	}
		
	public static int getTotalPrimos(int x1, int x2, int n) throws InterruptedException {	
		
		int intervalo = (x2-x1)/n;
		
		long init = System.currentTimeMillis();
		
		PrimoThread[] threads = new PrimoThread[n];
		
		// inicia todas as Threads
		for (int i = 0; i < n; i++) {
			
			PrimoThread p = new PrimoThread(x1 + intervalo*i , x1  + intervalo*(i+1));	
			p.start();
			threads[i] = p;
		}
		
		int totalPrimos = 0;
		// Espera todas as threads finalizarem
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
			totalPrimos +=  threads[i].getTotalPrimos();
		}
		
		long fim = System.currentTimeMillis() - init;
		
		System.out.println((double)fim/1000 + "s "  +  totalPrimos + " primos");
		
		return totalPrimos;

		
	}
	
}

