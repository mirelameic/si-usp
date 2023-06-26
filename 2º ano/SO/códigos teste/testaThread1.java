public class testaThread{
	public static void main(String[] args){
		//Cria as threads
		threadRunnable thread1 = new threadRunnable("Thread 1", 200);
		threadRunnable thread2 = new threadRunnable("Thread 2", 600);
		threadRunnable thread3 = new threadRunnable("Thread 3", 1000);
	}
}