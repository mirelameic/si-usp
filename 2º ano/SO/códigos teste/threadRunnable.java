public class threadRunnable implements Runnable{
	private String nome;

	public threadRunnable (String nome){
		Thread t = new Thread(this);
		this.nome = nome;
		t.start();
	}

	@Override
	public void run(){
		System.out.println(nome + " Hello World!");
	}
}