// Floresta.java
// Subclasse de Local

package mundo;

public class Floresta extends Local {
    public Floresta(String nome) {
        super(nome);
    }

    @Override
    public void explorar() {
        System.out.println("Você entra na floresta escura de " + nome);
    }
}
