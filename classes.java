import java.util.Scanner;
class Classes{
    public static void main(String[] args) {
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
                break;
            case 2:
                System.out.println("Você escolheu Mago!");
                break;
            case 3:
                System.out.println("Você escolheu Arqueiro!");
                break;
            case 4:
                System.out.println("Você escolheu Paladino!");
                break;
            default:
                System.out.println("Classe inválida! Tente novamente.");
        }
    }
}