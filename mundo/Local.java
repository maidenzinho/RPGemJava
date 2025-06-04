// Local.java
// Classe base para locais do mundo

package mundo;

public class Local {
    protected String nome;

    public Local(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void explorar() {
        System.out.println("Explorando " + nome);
    }
}
