package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario1 extends Cenario {

	private Window janela;
	private Scene cena;
	private Jogador jogador;
	private Keyboard teclado;
	private Zumbi zumbi;
	
	public Cenario1(Window window) {
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario1.scn"));
		jogador = new Jogador(640, 350);
		teclado = janela.getKeyboard();
		zumbi = new Zumbi(330, 310);
		
//		Som.play("darkforce_battle.mid");
		run();
	}
	
	private void run() {
		
		while(true) {
			jogador.mover(janela, teclado);
			jogador.caminho(cena);			
			zumbi.caminho(cena);
			zumbi.perseguir(jogador.x, jogador.y);
			cena.moveScene(jogador);
			
			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();
			
			zumbi.x += cena.getXOffset();
			zumbi.y += cena.getYOffset();
			
			jogador.draw();
			zumbi.draw();
			jogador.atirar(janela, cena, teclado, zumbi);
			zumbi.morrer();
			zumbi.atacar(jogador);
			jogador.energia(janela);
			
			janela.update();
			janela.delay(5);
			
			mudarCenario();
		}
	}
	
	private void mudarCenario() {
		if(tileCollision(3, jogador, cena) == true) {
			new Cenario2(janela);
		}
	}
}
