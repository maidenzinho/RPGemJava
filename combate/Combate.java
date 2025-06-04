// Combate.java
// Gerencia batalhas

package combate;

import personagem.Personagem;

public class Combate {
    public static void lutar(Personagem jogador, Inimigo inimigo) {
        System.out.println("Combate iniciado entre " + jogador.getNome() + " e " + inimigo.getNome());
        jogador.atacar();
        inimigo.atacar();
    }
}
