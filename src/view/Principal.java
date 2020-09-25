package view;

import java.util.concurrent.Semaphore;

import controller.CozinhaThread;

public class Principal {

	public static void main(String[] args) {
		int permissao = 1, ID = 1;
		Semaphore Prato = new Semaphore(permissao);
		for(int i = 0; i<5; i++) {
			Thread cozinha= new CozinhaThread(Prato, ID);
			cozinha.start();
			ID ++;
		}
	}
}
