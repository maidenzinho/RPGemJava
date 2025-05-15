import java.util.random.*;
class Dados{
    private int valor;
    private int lados;

    public Dados(int lados){
        this.lados = lados;
        this.valor = 0;
    }

    public void rolar(){
        this.valor = (int)(Math.random() * lados) + 1;
    }

    public int getValor(){
        return this.valor;
    }
    public int getLados(){
        return this.lados;
    }
    public void setLados(int lados){
        this.lados = lados;
    }
    public void setValor(int valor){
        this.valor = valor;
    }
    public String toString(){
        return "Valor: " + this.valor + ", Lados: " + this.lados;
    }
    public boolean equals(Dados d){
        return this.valor == d.getValor() && this.lados == d.getLados();
    }
    public boolean equals(Object obj){
        if (obj instanceof Dados) {
            Dados d = (Dados) obj;
            return this.valor == d.getValor() && this.lados == d.getLados();
        }
        return false;
    }
    public int hashCode(){
        return this.valor * 31 + this.lados;
    }
}