// LeitorDados.java
// Lê dados de CSV ou TXT

package dados;

import java.io.*;
import java.util.ArrayList;

public class LeitorDados {
    public static ArrayList<String> lerArquivo(String caminho) {
        ArrayList<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return linhas;
    }
}
