public class threadRunnable implements Runnable{
	private String nome;
	private int tempo;

	//Construtor
	public threadRunnable (String nome, int tempo){
		//Instanciando cada thread
		Thread t = new Thread(this);
		this.nome = nome;
		this.tempo = tempo;
		//Passa para o estado de runnable
		t.start();
	}

	@Override
	//Passa para o estado de running
	public void run(){
		try {
			//Contador para cada thread
			for (int i=0; i<6; i++){
				System.out.println(nome + " Hello World! " + i);
				//Tempo no estado de wait
				Thread.sleep(tempo);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}