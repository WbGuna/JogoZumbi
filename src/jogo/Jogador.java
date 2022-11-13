package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Jogador extends Ator {
	
	ControleTiros tiros = new ControleTiros();		
	static double energia = 10000;

	public Jogador(int x, int y) {
		super(URL.sprite("jogador.png"), 20);
		this.x = x;
		this.y = y;
		this.setTotalDuration(8000);
	}
	
	public void atirar(Window janela, Scene cena, Keyboard teclado, Ator inimigo) {
		if(teclado.keyDown(KeyEvent.VK_A)) {
			tiros.adicionaTiro(x + 5, y + 12, direcao, cena);
		}
		tiros.run(inimigo);
	}
	
	public void mover(Window janela, Keyboard teclado) {
				
		if(teclado.keyDown(Keyboard.LEFT_KEY)) {			
			if(this.x > 0) {
				this.x -= velocidade;
			}
			 
			if(direcao != 1) {
				setSequence(4, 8);
				direcao = 1;				
			}			
			movendo = true;
			
		} else if(teclado.keyDown(Keyboard.RIGHT_KEY)) {			
			if(this.x < janela.getWidth() - 60) {
				this.x += velocidade;
			}			
			if(direcao != 2) {
				setSequence(8, 12);
				direcao = 2;				
			}			
			movendo = true;
			
		} else if(teclado.keyDown(Keyboard.UP_KEY)) {			
			if(this.y > 0) {
				this.y -= velocidade;
			}			
			if(direcao != 4) {
				setSequence(12, 16);
				direcao = 4;				
			}			
			movendo = true;
			
		} else if(teclado.keyDown(Keyboard.DOWN_KEY)) {			
			if(this.y < janela.getHeight() - 60) {
				this.y += velocidade;
			}			
			if(direcao != 5) {
				setSequence(0, 4);
				direcao = 5;				
			}			
			movendo = true;
		}		
		if(movendo) {
			update();
			movendo = false;
		}
	}

	Font f = new Font("arial", Font.BOLD, 30);
	
	public void energia(Window janela) {
		janela.drawText("Energia: " + Jogador.energia, 30, 30, Color.green, f);
	}
}
