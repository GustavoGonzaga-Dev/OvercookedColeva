package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CozinhaThread extends Thread{
	private Semaphore prato;
	private String nome;
	private int id;
	private int tempoTotal;
	private int tempoCozimento;
	private int progresso;
	Random random = new Random();
	public CozinhaThread(Semaphore prato, int id) {
		this.prato = prato;
		this.id = id;
	}

	@Override
	public void run() {
		Preparando();
	}

	public void Preparando() {
		if(id % 2 == 0) {
			nome = "Lasanha a Bolonhesa";
			tempoTotal = random.nextInt(1200) + 600;
			System.out.println("O prato n:" +id+ " " +nome+ ", Iniciou o preparo.");
			for(tempoCozimento = 0; tempoCozimento < tempoTotal; tempoCozimento += 100) {
				try {
					sleep(100);
					progresso = tempoCozimento * 100 / tempoTotal;
					System.out.println("O prato n:" +id+ " " +nome+ ", está " + progresso+ "% concluido");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("----------O prato n:" +id+ " " +nome+ ", está pronto");
			entrega();
		}else {
			nome = "Sopa de Cebola";
			tempoTotal = random.nextInt(800) + 500;
			System.out.println("O prato n:" +id+ " " +nome+ ", Iniciou o preparo.");
			for(tempoCozimento = 0; tempoCozimento < tempoTotal; tempoCozimento += 100) {
				try {
					sleep(100);
					progresso = tempoCozimento * 100 / tempoTotal;
					System.out.println("O prato n:" +id+ " " +nome+ ", está " + progresso+ "% concluido");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("----------O prato n:" +id+ " " +nome+ ", está pronto");
			entrega();
		}
	}

	public void entrega() {
		try {
			prato.acquire();
			sleep(500);
			System.out.println("|||||O prato n:" +id+ " " +nome+ ", foi entregue :3");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			prato.release();
		}
	}
}
