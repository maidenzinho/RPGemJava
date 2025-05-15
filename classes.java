import java.util.Scanner;
class Classes{
    public static void main(String[] args) {
        int pv = 0;
        int ataque = 0;
        int defesa = 0;
        int mana = 0;

        System.out.println("Bem-vindo ao jogo!");
        System.out.println("Escolha sua classe:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Paladino");

        Scanner escolhaClasses = new Scanner(System.in);
        int classe = escolhaClasses.nextInt();
        switch (classe) {
            case 1:
                System.out.println("Você escolheu Guerreiro!");
                pv = 100;
                ataque = 20;
                defesa = 15;
                mana = 5;
                System.out.println("PV: " + pv);
                System.out.println("Ataque: " + ataque);
                System.out.println("Defesa: " + defesa);
                System.out.println("Mana: " + mana);
                break;
            case 2:
                System.out.println("Você escolheu Mago!");
                pv = 80;
                ataque = 25;
                defesa = 10;
                mana = 20;
                System.out.println("PV: " + pv);
                System.out.println("Ataque: " + ataque);
                System.out.println("Defesa: " + defesa);
                System.out.println("Mana: " + mana);
                break;
            case 3:
                System.out.println("Você escolheu Arqueiro!");
                pv = 90;
                ataque = 15;
                defesa = 12;
                mana = 10;
                System.out.println("PV: " + pv);
                System.out.println("Ataque: " + ataque);
                System.out.println("Defesa: " + defesa);
                System.out.println("Mana: " + mana);
                break;
            case 4:
                System.out.println("Você escolheu Paladino!");
                pv = 110;
                ataque = 18;
                defesa = 20;
                mana = 8;
                System.out.println("PV: " + pv);
                System.out.println("Ataque: " + ataque);
                System.out.println("Defesa: " + defesa);
                System.out.println("Mana: " + mana);
                break;
            default:
                System.out.println("Classe inválida! Tente novamente.");
        }
    }
}