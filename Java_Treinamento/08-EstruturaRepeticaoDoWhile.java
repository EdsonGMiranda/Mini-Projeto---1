// Estrutura de Repetição Do While

import java.util.Scanner;

class EstruturaRepeticaoDoWhile {

    public static void main(String[] args) {

        int idade;

        /*
            O loop Do-While sempre executa o bloco de repetição ao menos 1 vez pois o teste está no FINAL

            No caso da estrutura de repetição WHILE o teste está no início, portanto
            o bloco de repetição pode não ser executado dependendo da condição
        */

        Scanner reader = new Scanner(System.in);

        do {
            System.out.println("Qual a sua idade?");
            idade = reader.nextInt();

            if (idade < 0)
                System.out.println("Por favor digite uma idade com valor positivo!");
        }
        while(idade < 0);

        System.out.println("Faltam " + (100 - idade) + " para você chegar aos 100 anos!");

        reader.close();
    }
}
