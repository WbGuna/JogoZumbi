package jogo;

import java.util.Iterator;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario2 extends Cenario {

	private Window janela;
	private Scene cena;
	private Jogador jogador;
	private Keyboard teclado;
	private Zumbi zumbi[];
	
	public Cenario2(Window window) {
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario2.scn"));
		jogador = new Jogador(640, 350);
		teclado = janela.getKeyboard();
		zumbi = new Zumbi[20];
		
//		Som.play("darkforce_battle.mid");
		run();
	}
	
	private void run() {
		
		for (int i = 0; i < zumbi.length; i++) {
			zumbi[i] = new Zumbi(100 * i, 100 * i);
		}
		
		while(true) {
			jogador.mover(janela, teclado);
			jogador.caminho(cena);			
			cena.moveScene(jogador);
			
			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();
			jogador.draw();
			jogador.energia(janela);
			
			for (int i = 0; i < zumbi.length; i++) {
				zumbi[i].x += cena.getXOffset();
				zumbi[i].y += cena.getYOffset();
				zumbi[i].caminho(cena);
				zumbi[i].perseguir(jogador.x, jogador.y);			
				zumbi[i].draw();
				jogador.atirar(janela, cena, teclado, zumbi[i]);
				zumbi[i].morrer();
				zumbi[i].atacar(jogador);				
			}
			
			janela.update();
			janela.delay(5);
			
			mudarCenario();
		}
	}
	
	private void mudarCenario() {
		if(tileCollision(3, jogador, cena) == true) {
			new Cenario1(janela);
		}
	}
}
