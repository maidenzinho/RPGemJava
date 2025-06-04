// GravadorDados.java
// Salva progresso do jogador

package dados;

import java.io.*;
import personagem.Personagem;

public class GravadorDados {
    public static void salvar(Personagem jogador) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("save.txt"))) {
            bw.write(jogador.getNome() + "," + jogador.getVida());
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
}
